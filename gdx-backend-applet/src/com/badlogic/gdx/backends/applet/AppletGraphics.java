/*
 *  This file is part of Libgdx by Mario Zechner (badlogicgames@gmail.com)
 *
 *  Libgdx is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Libgdx is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with libgdx.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.badlogic.gdx.backends.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.RenderListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Font;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Font.FontStyle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.WindowedMean;

/**
 * An implementation of the {@link Graphics} interface based on Jogl.
 * @author mzechner
 *
 */
public final class AppletGraphics implements Graphics, RenderListener
{	
	/** the graphic panel **/	
	protected final AppletPanel graphicPanel;
	
	/** the render listener **/
	private RenderListener listener;	
	
	/** GL10 instance **/
	private GL10 gl10;
	
	/** GL11 instance **/
	private GL11 gl11;
	
	/** GL20 instance **/
	private GL20 gl20;
	
	/** wheter to use opengl 2 **/
	private final boolean useGL2;
		
	/** the last frame time **/
	private long lastFrameTime = System.nanoTime();
		
	/** the deltaTime **/
	private float deltaTime = 0;
	
	/** the deltaTime mean **/
	private WindowedMean mean = new WindowedMean( 5 );
	
	AppletGraphics( Application application, Applet applet, boolean useGL2IfAvailable )
	{				
		graphicPanel = new AppletPanel( application );            
        applet.setLayout( new BorderLayout() );
        applet.add( graphicPanel, BorderLayout.CENTER );
        useGL2 = useGL2IfAvailable;
        graphicPanel.addGraphicListener( this );
        graphicPanel.requestFocusInWindow();
	}

	@Override
	public GL10 getGL10() 
	{	
		return gl10;
	}

	@Override
	public GL11 getGL11() 
	{	
		return gl11;
	}

	@Override
	public GL20 getGL20() 
	{	
		return gl20;
	}

	@Override
	public int getHeight() 
	{	
		return graphicPanel.getHeight();
	}

	@Override
	public int getWidth() 
	{	
		return graphicPanel.getWidth();
	}

	@Override
	public boolean isGL11Available() 
	{	
		return gl11 != null;
	}

	@Override
	public boolean isGL20Available() 
	{	
		return gl20 != null;
	}

	@Override
	public Font newFont(String fontName, int size, FontStyle style, boolean managed ) 
	{	
		return new AppletFont( this, fontName, size, style, managed );
	}

	@Override
	public Font newFont(FileHandle file, int size, FontStyle style, boolean managed) 
	{					
		AppletFileHandle jHandle = (AppletFileHandle)file;
		InputStream in;
		try {
			in = new FileInputStream( jHandle.getFile() );
			AppletFont font = new AppletFont(this, in, size, style, managed);			
			in.close();
			
			return font;
		} catch (Exception e) 
		{		
			return null;
		}		
	}

	@Override
	public Pixmap newPixmap(int width, int height, Format format) 
	{	
		return new AppletPixmap( width, height, format );
	}

	@Override
	public Pixmap newPixmap(InputStream in) 
	{	
		try
		{
			BufferedImage img = (BufferedImage)ImageIO.read( in );
			return new AppletPixmap( img );
		}
		catch( Exception ex )
		{
			return null;
		}		
	}
	
	@Override
	public Pixmap newPixmap(FileHandle file) 
	{	
		try
		{
			BufferedImage img = (BufferedImage)ImageIO.read( ((AppletFileHandle)file).getFile() );
			return new AppletPixmap( img );
		}
		catch( Exception ex )
		{
			return null;
		}		
	}

	@Override
	public Pixmap newPixmap(Object nativePixmap) 
	{	
		return new AppletPixmap( (BufferedImage)nativePixmap );
	}

	@Override
	public Texture newTexture(int width, int height, Pixmap.Format format, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap, boolean managed) 
	{	
		if( format == Format.Alpha )
			return new AppletTexture( width, height, BufferedImage.TYPE_BYTE_GRAY, minFilter, magFilter, uWrap, vWrap, managed );
		else
			return new AppletTexture( width, height, BufferedImage.TYPE_4BYTE_ABGR, minFilter, magFilter, uWrap, vWrap, managed );
	}

	@Override
	public Texture newTexture(Pixmap pixmap, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap, boolean managed) 
	{
		return new AppletTexture( (BufferedImage)pixmap.getNativePixmap(), minFilter, magFilter, uWrap, vWrap, managed );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRenderListener( RenderListener listener) 
	{	
		if( this.listener != null )
			graphicPanel.removeGraphicListener( this.listener );
		graphicPanel.addGraphicListener(listener);
		this.listener = listener;
	}

	@Override
	public void dispose(Application app) 
	{	
		
	}

	@Override
	public void render(Application app) 
	{			
		// calculate delta time
		deltaTime = ( System.nanoTime() - lastFrameTime ) / 1000000000.0f;
		lastFrameTime = System.nanoTime();
		mean.addValue( deltaTime );			
	}

	@Override
	public void surfaceCreated(Application app) 
	{
		String version = graphicPanel.getGL().glGetString( GL.GL_VERSION );
		int major = Integer.parseInt("" + version.charAt(0));
		int minor = Integer.parseInt("" + version.charAt(2));
		
		if( useGL2 && major >= 2 )		
		{
			// FIXME add check wheter gl 2.0 is supported
			gl20 = new AppletGL20( graphicPanel.getGL() );
		}
		else
		{
			if( major == 1 && minor < 5 )
			{
				gl10 = new AppletGL10( graphicPanel.getGL() );
			}
			else
			{
				gl11 = new AppletGL11( graphicPanel.getGL() );
				gl10 = gl11;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getDeltaTime() 
	{
		return mean.getMean() == 0?deltaTime:mean.getMean();
	}

	@Override
	public void surfaceChanged(Application app, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GraphicsType getType() 
	{	
		return GraphicsType.JoglGL;
	}
}

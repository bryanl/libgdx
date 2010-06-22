package com.badlogic.gdxinvaders.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Font;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Font.FontStyle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.Matrix;

/**
 * The main menu screen showing a background, the logo of the game
 * and a label telling the user to touch the screen to start the game.
 * Waits for the touch and returns isDone() == true when it's done so that
 * the ochestrating GdxInvaders class can switch to the next screen. 
 * @author mzechner
 *
 */
public class MainMenu implements Screen 
{
	/** the SpriteBatch used to draw the background, logo and text **/
	private final SpriteBatch spriteBatch;
	/** the background texture **/
	private final Texture background;
	/** the logo texture **/
	private final Texture logo;
	/** the font **/
	private final Font font;
	/** is done flag **/
	private boolean isDone = false;
	/** view & transform matrix **/
	private final Matrix viewMatrix = new Matrix( );
	private final Matrix transformMatrix = new Matrix( );	
	
	public MainMenu( Application app )
	{	
		spriteBatch = new SpriteBatch(app.getGraphics());
		Pixmap backgroundPixmap = app.getGraphics().newPixmap( app.getFiles().getFileHandle( "data/planet.jpg", FileType.Internal ) );
		background = app.getGraphics().newTexture( backgroundPixmap, TextureFilter.Linear, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge, true );
		backgroundPixmap.dispose();
		
		Pixmap logoPixmap = app.getGraphics().newPixmap( app.getFiles().getFileHandle( "data/title.png", FileType.Internal ) );
		logo = app.getGraphics().newTexture( logoPixmap, TextureFilter.Linear, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge, true );
		logoPixmap.dispose();
		
		font = app.getGraphics().newFont( app.getFiles().getFileHandle( "data/font.ttf", FileType.Internal), 16, FontStyle.Plain, true );		
	}	

	@Override
	public void render(Application app) 
	{
		app.getGraphics().getGL10().glClear( GL10.GL_COLOR_BUFFER_BIT );
		
		viewMatrix.setToOrtho2D(0, 0, 480, 320 );
		spriteBatch.begin(viewMatrix, transformMatrix );
		spriteBatch.disableBlending();
		spriteBatch.draw( background, 0, 320, 480, 320, 0, 0, 512, 512, Color.WHITE, false, false );
		spriteBatch.enableBlending();
		spriteBatch.draw( logo, 0, 280, 480, 128, 0, 0, 512, 256, Color.WHITE, false, false );
		String text = "Touch screen to start!";
		float width = font.getStringWidth( text );
		spriteBatch.drawText( font, text, 240 - width / 2, 128, Color.WHITE );
		spriteBatch.end();
	}

	@Override
	public void update(Application app) 
	{
		isDone = app.getInput().isTouched();			
	}

	@Override
	public boolean isDone() 
	{	
		return isDone;
	}
	
	@Override
	public void dispose() 
	{	
		//spriteBatch.dispose();
		background.dispose();
		logo.dispose();
		font.dispose();
	}
}

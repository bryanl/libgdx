/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.badlogic.gdx.backends.jogl;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLContext;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

/**
 * An implementation of {@link Texture} based on Jogl
 * 
 * @author badlogicgames@gmail.com
 * 
 */
final class JoglTexture implements Texture {
	/** height in pixels of texture **/
	private int texHeight;
	/** width in pixels of texture **/
	private int texWidth;
	/** texture wrapper **/
	com.sun.opengl.util.texture.Texture texture;
	/** whether this textures i managed or not **/
	private final boolean isManaged;

	/** global number of textures **/
	public static int textures = 0;

	/**
	 * Create a new texture
	 */
	JoglTexture (InputStream in, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap,
		boolean managed) {
		this.isManaged = managed;
		try {
			BufferedImage image = ImageIO.read(in);
			texture = com.sun.opengl.util.texture.TextureIO.newTexture(image, TextureFilter.isMipMap(minFilter) ? true : false);
		} catch (Exception ex) {
			throw new GdxRuntimeException("Couldn load Texture", ex);
		}
		GL gl = GLContext.getCurrent().getGL();

		bind();
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, getTextureFilter(minFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, getTextureFilter(magFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, getTextureWrap(uWrap));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, getTextureWrap(vWrap));
		this.texWidth = texture.getWidth();
		this.texHeight = texture.getHeight();
		textures++;
	}

	JoglTexture (BufferedImage image, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap,
		boolean managed) {
		this.isManaged = managed;
		texture = com.sun.opengl.util.texture.TextureIO.newTexture(image, TextureFilter.isMipMap(minFilter) ? true : false);
		GL gl = GLContext.getCurrent().getGL();

		bind();
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, getTextureFilter(minFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, getTextureFilter(magFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, getTextureWrap(uWrap));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, getTextureWrap(vWrap));
		this.texWidth = texture.getWidth();
		this.texHeight = texture.getHeight();
		textures++;
	}

	/**
	 * Create a new texture
	 */
	JoglTexture (int width, int height, int format, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap,
		TextureWrap vWrap, boolean managed) {
		this.isManaged = managed;
		BufferedImage image = new BufferedImage(width, height, format);
		texture = com.sun.opengl.util.texture.TextureIO.newTexture(image, TextureFilter.isMipMap(minFilter) ? true : false);

		GL gl = GLContext.getCurrent().getGL();

		bind();
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, getTextureFilter(minFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, getTextureFilter(magFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, getTextureWrap(uWrap));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, getTextureWrap(vWrap));
		this.texWidth = texture.getWidth();
		this.texHeight = texture.getHeight();
		textures++;
	}

	public JoglTexture (com.badlogic.gdx.graphics.TextureData textureData, TextureFilter minFilter, TextureFilter magFilter,
		TextureWrap uWrap, TextureWrap vWrap) {
		this.isManaged = false;
		GL gl = GLContext.getCurrent().getGL();
		
		ByteBuffer buffer = ByteBuffer.allocateDirect(4);
		buffer.order(ByteOrder.nativeOrder());
		IntBuffer intBuffer = buffer.asIntBuffer();
		gl.glGenTextures(1, intBuffer);
		texture = com.sun.opengl.util.texture.TextureIO.newTexture(intBuffer.get(0));

		bind();
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, getTextureFilter(minFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, getTextureFilter(magFilter));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, getTextureWrap(uWrap));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, getTextureWrap(vWrap));

		textureData.load();
		texWidth = textureData.getWidth();
		texHeight = textureData.getHeight();

		textures++;
	}

	private int getTextureFilter (TextureFilter filter) {
		if (filter == TextureFilter.Linear)
			return GL.GL_LINEAR;
		else if (filter == TextureFilter.Nearest)
			return GL.GL_NEAREST;
		else if (filter == TextureFilter.MipMap)
			return GL.GL_LINEAR_MIPMAP_LINEAR;
		else if (filter == TextureFilter.MipMapNearestNearest)
			return GL.GL_NEAREST_MIPMAP_NEAREST;
		else if (filter == TextureFilter.MipMapNearestLinear)
			return GL.GL_NEAREST_MIPMAP_LINEAR;
		else if (filter == TextureFilter.MipMapLinearNearest)
			return GL.GL_LINEAR_MIPMAP_NEAREST;
		else if (filter == TextureFilter.MipMapLinearLinear)
			return GL.GL_LINEAR_MIPMAP_LINEAR;
		else
			return GL.GL_LINEAR_MIPMAP_LINEAR;
	}

	private int getTextureWrap (TextureWrap wrap) {
		if (wrap == TextureWrap.ClampToEdge)
			return GL.GL_CLAMP_TO_EDGE;
		else
			return GL.GL_REPEAT;
	}

	public boolean isManaged () {
		return isManaged;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void bind () {
		texture.bind();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void dispose () {
		texture.dispose();
		textures--;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int getHeight () {
		return texHeight;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int getWidth () {
		return texWidth;
	}

	/**
	 * {@inheritDoc}
	 */
	public void draw (Pixmap pixmap, int x, int y) {
		if (isManaged) throw new GdxRuntimeException("Can't draw to a managed texture");
		TextureData data = TextureIO.newTextureData((BufferedImage)pixmap.getNativePixmap(), true);
		texture.bind();
		texture.updateSubImage(data, 0, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int getTextureObjectHandle () {
		return texture.getTextureObject();
	}
}

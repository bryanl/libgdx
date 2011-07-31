/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.badlogic.gdx;

import java.nio.ByteBuffer;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.GLU;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.IndexBufferObject;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.badlogic.gdx.graphics.glutils.VertexBufferObject;

/**
 * <p>
 * This interface encapsulates the communication with the graphics processor. It
 * allows to retrieve {@link GL10}, {@link GL11} and {@link GL20} instances
 * depending on the available hardware and configuration of the
 * {@link Application}. Additionally it features methods to generate
 * {@link Pixmap}s and {@link Texture}s.
 * </p>
 * 
 * <p>
 * {@link Texture}s can be either managed or not managed. Managed and Textures
 * will be restored when the OpenGL context is lost. An OpenGL ES context loss
 * happens when a user pauses the Application (
 * {@link ApplicationListener#pause()}) and switches to another application on
 * Android. On the desktop there is no concept of context loss for OpenGL.
 * </p>
 * 
 * <p>
 * There are many more utility classes that are not directly generated by the
 * {@link Graphics} interfaces. See {@link VertexArray},
 * {@link VertexBufferObject}, {@link IndexBufferObject}, {@link Mesh},
 * {@link ShaderProgram} and {@link FrameBuffer}, {@link BitmapFont},
 * {@link SpriteBatch} and so on. All these classes are managed, meaning they
 * don't need to be reloaded on a context loss. Explore the
 * com.badlogic.gdx.graphics package for more classes that might come in handy.
 * </p>
 * 
 * <p>
 * All graphical resources, be the generated by the {@link Graphics} interface
 * or via a constructor <b>must</b> be disposed when no longer used!
 * </p>
 * 
 * @author mzechner
 * 
 */
public interface Graphics {
	/**
	 * Enumeration describing different types of {@link Graphics}
	 * implementations.
	 * 
	 * @author mzechner
	 * 
	 */
	public enum GraphicsType {
		AndroidGL, JoglGL, LWJGL, Angle, WebGL
	}

	/**
	 * Class describing a fullscreen display mode
	 * 
	 * @author mzechner
	 * 
	 */
	public class DisplayMode {
		public final int width;
		public final int height;
		public final int refreshRate;
		public final int bitsPerPixel;

		protected DisplayMode(int width, int height, int refreshRate,
				int bitsPerPixel) {
			this.width = width;
			this.height = height;
			this.refreshRate = refreshRate;
			this.bitsPerPixel = bitsPerPixel;
		}

		public String toString() {
			return width + "x" + height + ", bpp: " + bitsPerPixel + ", hz: "
					+ refreshRate;
		}
	}

	/**
	 * Class describing the bits per pixel, depth buffer precision, stencil
	 * precision and number of MSAA samples.
	 */
	public static class BufferFormat {
		/** number of bits per color channel **/
		public final int r, g, b, a;
		/** number of bits for depth and stencil buffer **/
		public final int depth, stencil;
		/** number of samples for MSAA **/
		public final int samples;
		/**
		 * whether coverage sampling anti-aliasing is used. in that case you
		 * have to clear the coverage buffer as well!
		 */
		public final boolean coverageSampling;

		public BufferFormat(int r, int g, int b, int a, int depth, int stencil,
				int samples, boolean coverageSampling) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a;
			this.depth = depth;
			this.stencil = stencil;
			this.samples = samples;
			this.coverageSampling = coverageSampling;
		}

		public String toString() {
			return "r: " + r + ", g: " + g + ", b: " + b + ", a: " + a
					+ ", depth: " + depth + ", stencil: " + stencil
					+ ", num samples: " + samples + ", coverage sampling: "
					+ coverageSampling;
		}
	}

	/**
	 * Returns whether OpenGL ES 1.1 is available. If it is you can get an
	 * instance of {@link GL11} via {@link #getGL11()} to access OpenGL ES 1.1
	 * functionality. This also implies that {@link #getGL10()} will return an
	 * instance.
	 * 
	 * @return whether OpenGL ES 1.1 is available
	 */
	public boolean isGL11Available();

	/**
	 * Returns whether OpenGL ES 2.0 is available. If it is you can get an
	 * instance of {@link GL20} via {@link #getGL20()} to access OpenGL ES 2.0
	 * functionality. Note that this functionality will only be available if you
	 * instructed the {@link Application} instance to use OpenGL ES 2.0!
	 * 
	 * @return whether OpenGL ES 2.0 is available
	 */
	public boolean isGL20Available();

	/**
	 * @return a {@link GLCommon} instance
	 */
	public GLCommon getGLCommon();

	/**
	 * @return the {@link GL10} instance or null if not supported
	 */
	public GL10 getGL10();

	/**
	 * @return the {@link GL11} instance or null if not supported
	 */
	public GL11 getGL11();

	/**
	 * @return the {@link GL20} instance or null if not supported
	 */
	public GL20 getGL20();

	/**
	 * @return the {@link GLU} instance
	 */
	public GLU getGLU();

	/**
	 * @return the width in pixels of the display surface
	 */
	public int getWidth();

	/**
	 * @return the height in pixels of the display surface
	 */
	public int getHeight();

	/**
	 * @return the time span between the current frame and the last frame in
	 *         seconds
	 */
	public float getDeltaTime();

	/**
	 * @return the average number of frames per second
	 */
	public int getFramesPerSecond();

	/**
	 * @return the {@link GraphicsType} of this Graphics instance
	 */
	public GraphicsType getType();

	/**
	 * @return the pixels per inch on the x-axis
	 */
	public float getPpiX();

	/**
	 * @return the pixels per inch on the y-axis
	 */
	public float getPpiY();

	/**
	 * @return the pixels per centimeter on the x-axis
	 */
	public float getPpcX();

	/**
	 * @return the pixels per centimeter on the y-axis.
	 */
	public float getPpcY();

	/**
	 * This is a scaling factor for the Density Independent Pixel unit,
	 * following the same conventions as android.util.DisplayMetrics#density,
	 * where one DIP is one pixel on an approximately 160 dpi screen. Thus on a
	 * 160dpi screen this density value will be 1; on a 120 dpi screen it would
	 * be .75; etc.
	 * 
	 * @return the logical density of the Display.
	 */
	public float getDensity();

	/**
	 * Whether the given backend supports a display mode change via calling
	 * {@link Graphics#setDisplayMode(DisplayMode)}
	 * 
	 * @return whether display mode changes are supported or not.
	 */
	public boolean supportsDisplayModeChange();

	/**
	 * @return the supported fullscreen {@link DisplayMode}.
	 */
	public DisplayMode[] getDisplayModes();

	/**
	 * @return the display mode of the primary graphics adapter.
	 */
	public DisplayMode getDesktopDisplayMode();

	/**
	 * Sets the current {@link DisplayMode}. Returns false in case the operation
	 * failed. Not all backends support this methods. See
	 * {@link Graphics#supportsDisplayModeChange()}.
	 * 
	 * @param displayMode
	 *            the display mode.
	 * @return whether the operation succeeded.
	 */
	public boolean setDisplayMode(DisplayMode displayMode);

	/**
	 * Tries to set the display mode width the given width and height in pixels.
	 * Will always succeed if fullscreen is set to false, in which case the
	 * application will be run in windowed mode. Use
	 * {@link Graphics#getDisplayModes()} to get a list of supported fullscreen
	 * modes.
	 * 
	 * @param width
	 *            the width in pixels
	 * @param height
	 *            the height in pixels
	 * @param fullscreen
	 *            whether to use fullscreen rendering or not
	 */
	public boolean setDisplayMode(int width, int height, boolean fullscreen);

	/**
	 * Sets the title of the window. Ignored on Android.
	 * 
	 * @param title
	 *            the title.
	 */
	public void setTitle(String title);

	/**
	 * Sets the icon of the window. Ignored on Android.
	 * 
	 * @param pixmap
	 */
	public void setIcon(Pixmap pixmap);

	/**
	 * Enable/Disable vsynching. This is a best-effort attempt which might not
	 * work on all platforms.
	 * 
	 * @param vsync
	 *            vsync enabled or not.
	 */
	public void setVSync(boolean vsync);

	/**
	 * @return the format of the color, depth and stencil buffer in a
	 *         {@link BufferFormat} instance
	 */
	public BufferFormat getBufferFormat();

	/**
	 * @param extension
	 *            the extension name
	 * @return whether the extension is supported
	 */
	public boolean supportsExtension(String extension);

	// /**
	// * Opens the first back facing video camera. Only one camera
	// * can be opened at any given time.
	// * @param width the width of the image to be taken in pixels.
	// * @param height the height of the image to be taken in pixels.
	// * @param portrait whether the camera should be opened in portrait mode or
	// not (landscape otherwise)
	// * @return true if this succeeded, false otherwise.
	// */
	// public boolean openCamera(int width, int height, boolean portrait);
	//
	// /**
	// * @return true in case a new camera frame arrived since the last call to
	// {@link #getCameraFrame()}.
	// */
	// public boolean hasNewCameraFrame();
	//
	// /**
	// * Returns a {@link TextureRegion} containing the latest frame of the
	// currently opened camera. Will
	// * throw a GdxRuntimeException in case the camera is not opened.
	// * @return a TextureRegion containing the camera snapshot.
	// */
	// public TextureRegion getCameraFrame();
	//
	// /**
	// * Saves the latest frame of the currently opened camera to the given
	// {@link ByteBuffer}. The pixels are stored
	// * in RGB565 format. The provided ByteBuffer must be able to store 2 *
	// cameraWidth * cameraHeight bytes. The ByteBuffer <b>must</b>
	// * be a direct ByteBuffer. The method will write pixels starting from the
	// ByteBuffer's current position.
	// * @param pixels the direct ByteBuffer to store the pixels in.
	// */
	// public void getCameraFrame(ByteBuffer pixels);
	//
	// /**
	// * Closes the camera. Has no effect in case the camera has not been
	// opened.
	// */
	// public void closeCamera();
}

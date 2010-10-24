/*******************************************************************************
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.backends.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.badlogic.gdx.graphics.Pixmap;

/**
 * An implementation of {@link Pixmap} for Android.
 * 
 * @author badlogicgames@gmail.com
 * 
 */
final class AndroidPixmap implements Pixmap {
	Canvas canvas;
	Bitmap pixmap;
	Paint paint;
	int r, g, b, a;

	AndroidPixmap (int width, int height, Pixmap.Format format) {
		Bitmap.Config config = getInternalFormat(format);
		pixmap = Bitmap.createBitmap(width, height, config);
		canvas = new Canvas(pixmap);
		paint = new Paint();
		paint.setStyle(Style.FILL);

		paint.setAntiAlias(true);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
	}

	/**
	 * {@inheritDoc}
	 */
	public AndroidPixmap (Bitmap bitmap) {
		pixmap = bitmap;

		if (pixmap.isMutable()) canvas = new Canvas(pixmap);
		paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
	}

	protected static Bitmap.Config getInternalFormat (Pixmap.Format format) {
		if (format == Pixmap.Format.Alpha) return Bitmap.Config.ALPHA_8;
		if (format == Pixmap.Format.RGBA4444) return Bitmap.Config.ARGB_4444;
		if (format == Pixmap.Format.RGB565) return Bitmap.Config.RGB_565;
		return Bitmap.Config.ARGB_8888;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void drawCircle (int x, int y, int radius) {
		if (canvas == null) return;
		paint.setStyle(Style.STROKE);
		canvas.drawCircle(x + radius, y + radius, radius, paint);
		paint.setStyle(Style.FILL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void drawLine (int x, int y, int x2, int y2) {
		if (canvas == null) return;
		canvas.drawLine(x, y, x2, y2, paint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void drawRectangle (int x, int y, int width, int height) {
		if (canvas == null) return;
		paint.setStyle(Style.STROKE);
		canvas.drawRect(x, y, x + width, y + height, paint);
		paint.setStyle(Style.FILL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void fill () {
		if (canvas == null) return;
		fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void fillCircle (int x, int y, int radius) {
		if (canvas == null) return;
		canvas.drawCircle(x + radius, y + radius, radius, paint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void fillRectangle (int x, int y, int width, int height) {
		if (canvas == null) return;
		canvas.drawRect(x, y, x + width, y + height, paint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public Object getNativePixmap () {
		return pixmap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void setColor (float r, float g, float b, float a) {
		this.r = (int)(255 * r);
		this.g = (int)(255 * g);
		this.b = (int)(255 * b);
		this.a = (int)(255 * a);

		paint.setColor(this.a << 24 | this.r << 16 | this.g << 8 | this.b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void setStrokeWidth (int width) {
		paint.setStrokeWidth(width);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int getPixel (int x, int y) {
		if (x < 0 || x >= pixmap.getWidth()) return 0;
		if (y < 0 || y >= pixmap.getHeight()) return 0;
		return pixmap.getPixel(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int getHeight () {
		return pixmap.getHeight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public int getWidth () {
		return pixmap.getWidth();
	}

	Rect src = new Rect();
	RectF dst = new RectF();

	/**
	 * {@inheritDoc}
	 */
	@Override public void drawPixmap (Pixmap pixmap, int x, int y, int srcx, int srcy, int width, int height) {
		if (canvas == null) return;

		dst.set(x, y, x + width, y + height);
		src.set(srcx, srcy, srcx + width, srcy + height);

		canvas.drawBitmap((Bitmap)pixmap.getNativePixmap(), src, dst, null);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void dispose () {
		pixmap.recycle();
		pixmap = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void getPixelRow (int[] pixels, int y) {
		pixmap.getPixels(pixels, 0, pixmap.getWidth(), 0, y, pixmap.getWidth(), 1);
	}

}

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

package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** A text label, with optional word wrapping.
 * <p>
 * The preferred size of the label is determined by the actual text bounds, unless word wrap is enabled (see
 * {@link #setWrap(boolean)} and {@link #setWrapWidth(float)}).
 * @author Nathan Sweet */
public class Label extends Widget {
	private LabelStyle style;
	private final TextBounds bounds = new TextBounds();
	private String text;
	private BitmapFontCache cache;
	private float prefWidth, prefHeight;
	private int labelAlign = Align.LEFT;
	private HAlignment wrapAlign = HAlignment.LEFT;
	private boolean wrap;
	private float wrapWidth;

	public Label (Skin skin) {
		this("", skin);
	}

	public Label (String text, Skin skin) {
		this(text, skin.getStyle(LabelStyle.class), null);
	}

	/** Creates a label, using a {@link LabelStyle} that has a BitmapFont with the specified name from the skin and the specified
	 * color. */
	public Label (String text, String fontName, Color color, Skin skin) {
		this(text, new LabelStyle(skin.getResource(fontName, BitmapFont.class), color), null);
	}

	/** Creates a label, using a {@link LabelStyle} that has a BitmapFont with the specified name and the specified color from the
	 * skin. */
	public Label (String text, String fontName, String colorName, Skin skin) {
		this(text, new LabelStyle(skin.getResource(fontName, BitmapFont.class), skin.getResource(colorName, Color.class)), null);
	}

	public Label (String text, LabelStyle style) {
		this(text, style, null);
	}

	public Label (String text, LabelStyle style, String name) {
		super(name);
		if (text == null) throw new IllegalArgumentException("text cannot be null.");
		this.text = text;
		setStyle(style);
		touchable = false;
	}

	public void setStyle (LabelStyle style) {
		if (style == null) throw new IllegalArgumentException("style cannot be null.");
		if (style.font == null) throw new IllegalArgumentException("Missing LabelStyle font.");
		this.style = style;
		cache = new BitmapFontCache(style.font);
		if (style.fontColor != null) cache.setColor(style.fontColor);
		invalidateHierarchy();
	}

	/** Returns the label's style. Modifying the returned style may not have an effect until {@link #setStyle(LabelStyle)} is
	 * called. */
	public LabelStyle getStyle () {
		return style;
	}

	public void setText (String text) {
		if (text == null) throw new IllegalArgumentException("text cannot be null.");
		if (text.equals(this.text)) return;
		this.text = text;
		invalidateHierarchy();
	}

	public String getText () {
		return text;
	}

	public TextBounds getTextBounds () {
		return bounds;
	}

	/** If false, the text will only wrap where it contains newlines (\n). The preferred size of the label will be the text bounds.
	 * If true, the text will word wrap using the width of the label. The preferred size of the label will be 100x100, so usually
	 * the size of the label should be set explicitly without relying on the preferred size. Default is false.
	 * @see #setWrapWidth(float) */
	public void setWrap (boolean wrap) {
		this.wrap = wrap;
		invalidateHierarchy();
	}

	/** Enables word wrap and sets the width that is used to word wrap. The preferred width of the label will be the wrap width and
	 * the preferred height will be the actual height of the wrapped text.
	 * @param wrapWidth Set to zero to disable wrap. */
	public void setWrapWidth (float wrapWidth) {
		setWrap(wrapWidth != 0);
		this.wrapWidth = wrapWidth;
		invalidateHierarchy();
	}

	/** @param wrapAlign Aligns each line of text horizontally and all the text vertically.
	 * @see Align */
	public void setAlignment (int wrapAlign) {
		setAlignment(wrapAlign, wrapAlign);
	}

	/** @param labelAlign Aligns all the text with the label widget.
	 * @param wrapAlign Aligns each line of text (left, right, or center).
	 * @see Align */
	public void setAlignment (int labelAlign, int wrapAlign) {
		this.labelAlign = labelAlign;

		if ((wrapAlign & Align.LEFT) != 0)
			this.wrapAlign = HAlignment.LEFT;
		else if ((wrapAlign & Align.RIGHT) != 0)
			this.wrapAlign = HAlignment.RIGHT;
		else
			this.wrapAlign = HAlignment.CENTER;

		invalidate();
	}

	public void setColor (float color) {
		cache.setColor(color);
	}

	public void setColor (Color tint) {
		cache.setColor(tint);
	}

	public void setColor (float r, float g, float b, float a) {
		cache.setColor(r, g, b, a);
	}

	public Color getColor () {
		return cache.getColor();
	}

	@Override
	public void layout () {
		float wrapWidth = this.wrapWidth != 0 ? this.wrapWidth : width;
		if (wrap)
			bounds.set(cache.getFont().getWrappedBounds(text, wrapWidth));
		else
			bounds.set(cache.getFont().getMultiLineBounds(text));

		float y;
		if ((labelAlign & Align.TOP) != 0) {
			y = cache.getFont().isFlipped() ? 0 : height - bounds.height;
			y += style.font.getDescent();
		} else if ((labelAlign & Align.BOTTOM) != 0) {
			y = cache.getFont().isFlipped() ? height - bounds.height : 0;
			y -= style.font.getDescent();
		} else
			y = (height - bounds.height) / 2;
		if (!cache.getFont().isFlipped()) y += bounds.height;

		float x;
		if ((labelAlign & Align.LEFT) != 0)
			x = 0;
		else if ((labelAlign & Align.RIGHT) != 0) {
			x = width - bounds.width;
		} else
			x = (width - bounds.width) / 2;

		if (wrap)
			cache.setWrappedText(text, x, y, wrapWidth, wrapAlign);
		else
			cache.setMultiLineText(text, x, y, width, wrapAlign);
	}

	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		validate();
		cache.setPosition(x, y);
		cache.draw(batch, parentAlpha);
	}

	public float getPrefWidth () {
		if (wrap) return wrapWidth != 0 ? wrapWidth : 100;
		validate();
		return bounds.width;
	}

	public float getPrefHeight () {
		if (wrap && wrapWidth == 0) return 100;
		validate();
		return bounds.height - style.font.getDescent() * 2;
	}

	/** The style for a label, see {@link Label}.
	 * @author Nathan Sweet */
	static public class LabelStyle {
		public BitmapFont font;
		/** Optional. */
		public Color fontColor;

		public LabelStyle () {
		}

		public LabelStyle (BitmapFont font, Color fontColor) {
			this.font = font;
			this.fontColor = fontColor;
		}
	}
}

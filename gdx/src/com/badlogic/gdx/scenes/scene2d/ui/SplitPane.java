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

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Layout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.scenes.scene2d.ui.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;

/** A special container holding two children and allowing to define the space used by each.
 * 
 * <h2>Functionality</h2> A SplitPane can embedd to {@link Actor} instances (or {@link Widget} or {@link Table} instances for that
 * matter), separated by a split handle, either vertically or horizontally. Both widgets will be sized so that they take up their
 * respective space within the SplitPane. The handle can be moved via dragging to vary the size available to each widget.</p>
 * 
 * The amount of available space for the first Actor is given between 0 and 1, 0 meaning no space, 1 meaning all the space. The
 * amount of space available for the second Actor is computed as 1 minus the amount available to the second Actor. One can set the
 * value for the first widget via {@link #setSplitAmount(float)} manually, the amount for the second Actor is derrived
 * automatically. The range of the split amount can be defined via {@link #setMinSplitAmount(float)} and
 * {@link #setMaxSplitAmount(float)}.
 * 
 * The SplitPane will employ scissoring (clipping) to make sure none of the two Actors can render outside of their allocated
 * space.</p>
 * 
 * <b>Note: do not use any of the {@link #addActor(Actor)} or {@link #removeActor(Actor)} methods with this class! The embedded
 * Actors are specified at construction time or via #set</b>
 * 
 * The embedded Actors will always be resized to fill their entire space within the SplitPane</p>
 * 
 * <h2>Layout</h2> The (preferred) width and height of a split pane is determined by the size passed to its constructor. The
 * contained Actor instances size will be set to their respective available area within the split pane.</p>
 * 
 * <h2>Style</h2> A SplitPane is a {@link Group} displaying two Actor instances either left and right or top and bottom, depending
 * on whether the SplitPane is a horizontal split pane or a vertical split pane. Additionally a {@link NinePatch} is used to
 * render the SplitPane handle, either a horizontal or vertical strip. In case the SplitPane is a horizontal one the NinePatch
 * will be stretched vertically, and its width will be the value returned by {@link NinePatch#getTotalWidth()}. In case the
 * SplitPane is a vertical one it will be stretched horizontally and its height will be the value returned by
 * {@link NinePatch#getTotalHeight()}.</p>
 * 
 * A SplitPane's style definition in a skin XML file should look like this:
 * 
 * <pre>
 * {@code 
 * <splitpane name="styleName" 
 *            handle="handlePatch"/>
 * }
 * </pre>
 * 
 * <ul>
 * <li>The <code>name</code> attribute defines the name of the style which you can later use with .</li>
 * <li>The <code>handle</code> attribute references a {@link NinePatch} by name, to be used as the split pane's handle</li>
 * </ul>
 * @author mzechner */
public class SplitPane extends WidgetGroup {
	protected SplitPaneStyle style;

	protected boolean vertical;
	protected float splitAmount = 0.5f;
	protected float minAmount = 0;
	protected float maxAmount = 1;
	protected float oldSplitAmount = 0;
	protected Stage stage;
	protected Actor firstWidget;
	protected Actor secondWidget;
	protected Rectangle firstWidgetBounds = new Rectangle();
	protected Rectangle secondWidgetBounds = new Rectangle();
	protected Rectangle handleBounds = new Rectangle();
	protected Rectangle[] scissors = new Rectangle[] {new Rectangle(), new Rectangle()};
	protected boolean touchDrag = false;

	public SplitPane (Stage stage, Skin skin) {
		this(null, null, false, stage, skin);
	}

	public SplitPane (Actor firstWidget, Actor secondWidget, boolean vertical, Stage stage, Skin skin) {
		this(firstWidget, secondWidget, vertical, stage, skin.getStyle("default-horizontal", SplitPaneStyle.class), null);
	}

	public SplitPane (Actor firstWidget, Actor secondWidget, boolean vertical, Stage stage, SplitPaneStyle style) {
		this(firstWidget, secondWidget, vertical, stage, style, null);
	}

	/** Creates a new SplitPane. It's width and height is determined by the prefWidth and prefHeight parameters.
	 * @param firstWidget the first {@link Actor}
	 * @param secondWidget the second Actor
	 * @param vertical whether this is a vertical SplitPane or not (horizontal)
	 * @param stage the stage, used for clipping
	 * @param style the {@link SplitPaneStyle}
	 * @param name the name */
	public SplitPane (Actor firstWidget, Actor secondWidget, boolean vertical, Stage stage, SplitPaneStyle style, String name) {
		super(name);
		this.stage = stage;
		setStyle(style);
		this.firstWidget = firstWidget;
		this.secondWidget = secondWidget;
		this.vertical = vertical;

		if (firstWidget != null) addActor(firstWidget);
		if (secondWidget != null) addActor(secondWidget);
		width = 150;
		height = 150;
	}

	/** Sets the style of this widget.
	 * @param style */
	public void setStyle (SplitPaneStyle style) {
		this.style = style;
		invalidateHierarchy();
	}

	@Override
	public void layout () {
		if (firstWidget instanceof Layout) ((Layout)firstWidget).pack();
		if (secondWidget instanceof Layout) ((Layout)secondWidget).pack();
	}

	@Override
	public float getPrefWidth () {
		return 150;
	}

	@Override
	public float getPrefHeight () {
		return 150;
	}

	public float getMinWidth () {
		return 0;
	}

	public float getMinHeight () {
		return 0;
	}

	// BOZO - Use layout.
	private void calculateBoundsAndPositions (Matrix4 transform) {
		if (oldSplitAmount != splitAmount) {
			oldSplitAmount = splitAmount;
			invalidate();
		}

		if (!vertical)
			calculateHorizBoundsAndPositions();
		else
			calculateVertBoundsAndPositions();

		if (firstWidget != null) {
			boolean layout = firstWidget.width != firstWidgetBounds.width || firstWidget.height != firstWidgetBounds.height;
			firstWidget.x = firstWidgetBounds.x;
			firstWidget.y = firstWidgetBounds.y;
			firstWidget.width = firstWidgetBounds.width;
			firstWidget.height = firstWidgetBounds.height;
			if (layout && firstWidget instanceof Layout) ((Layout)firstWidget).invalidate();
		}
		if (secondWidget != null) {
			boolean layout = secondWidget.width != secondWidgetBounds.width || secondWidget.height != secondWidgetBounds.height;
			secondWidget.x = secondWidgetBounds.x;
			secondWidget.y = secondWidgetBounds.y;
			secondWidget.width = secondWidgetBounds.width;
			secondWidget.height = secondWidgetBounds.height;
			if (layout && secondWidget instanceof Layout) ((Layout)secondWidget).invalidate();
		}

		ScissorStack.calculateScissors(stage.getCamera(), transform, firstWidgetBounds, scissors[0]);
		ScissorStack.calculateScissors(stage.getCamera(), transform, secondWidgetBounds, scissors[1]);
	}

	private void calculateHorizBoundsAndPositions () {
		NinePatch handle = style.handle;

		float availWidth = width - handle.getTotalWidth();
		float leftAreaWidth = (int)(availWidth * splitAmount);
		float rightAreaWidth = (availWidth - leftAreaWidth);
		float handleWidth = (handle.getTotalWidth());

		firstWidgetBounds.set(0, 0, leftAreaWidth, height);
		secondWidgetBounds.set(leftAreaWidth + handleWidth, 0, rightAreaWidth, height);
		handleBounds.set(leftAreaWidth, 0, handleWidth, height);
	}

	private void calculateVertBoundsAndPositions () {
		NinePatch handle = style.handle;

		float availHeight = height - handle.getTotalHeight();
		float topAreaHeight = (int)(availHeight * splitAmount);
		float bottomAreaHeight = (availHeight - topAreaHeight);
		float handleHeight = handle.getTotalHeight();

		firstWidgetBounds.set(0, height - topAreaHeight, width, topAreaHeight);
		secondWidgetBounds.set(0, 0, width, bottomAreaHeight);
		handleBounds.set(0, bottomAreaHeight, width, handleHeight);
	}

	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		validate();

		NinePatch handle = style.handle;
		applyTransform(batch);
		calculateBoundsAndPositions(batch.getTransformMatrix());
		for (int i = 0; i < children.size(); i++) {
			if (ScissorStack.pushScissors(scissors[i])) {
				drawChild(children.get(i), batch, parentAlpha);
				ScissorStack.popScissors();
			}
		}
		batch.setColor(color.r, color.g, color.b, color.a);
		handle.draw(batch, handleBounds.x, handleBounds.y, handleBounds.width, handleBounds.height);
		resetTransform(batch);
	}

	Vector2 lastPoint = new Vector2();
	Vector2 handlePos = new Vector2();

	@Override
	public boolean touchDown (float x, float y, int pointer) {
		if (pointer != 0) return false;
		if (handleBounds.contains(x, y)) {
			touchDrag = true;
			lastPoint.set(x, y);
			handlePos.set(handleBounds.x, handleBounds.y);
			return true;
		}
		return super.touchDown(x, y, pointer);
	}

	@Override
	public void touchUp (float x, float y, int pointer) {
		if (touchDrag)
			touchDrag = false;
		else
			super.touchUp(x, y, pointer);
	}

	@Override
	public void touchDragged (float x, float y, int pointer) {
		NinePatch handle = style.handle;

		if (touchDrag) {
			if (!vertical) {
				float delta = x - lastPoint.x;
				float availWidth = width - handle.getTotalWidth();
				float dragX = handlePos.x + delta;
				handlePos.x = dragX;
				dragX = Math.max(0, dragX);
				dragX = Math.min(availWidth, dragX);
				splitAmount = dragX / availWidth;
				if (splitAmount < minAmount) splitAmount = minAmount;
				if (splitAmount > maxAmount) splitAmount = maxAmount;
				lastPoint.set(x, y);
			} else {
				float delta = y - lastPoint.y;
				float availHeight = height - handle.getTotalHeight();
				float dragY = handlePos.y + delta;
				handlePos.y = dragY;
				dragY = Math.max(0, dragY);
				dragY = Math.min(availHeight, dragY);
				splitAmount = 1 - (dragY / availHeight);
				if (splitAmount < minAmount) splitAmount = minAmount;
				if (splitAmount > maxAmount) splitAmount = maxAmount;
				lastPoint.set(x, y);
			}
			invalidate();
		} else
			super.touchDragged(x, y, pointer);
	}

	/** Sets the split amount
	 * @param split the split amount between 0 and 1 */
	public void setSplitAmount (float split) {
		this.splitAmount = Math.max(Math.min(maxAmount, split), minAmount);
		invalidate();
	}

	/** @return the split amount */
	public float getSplit () {
		return splitAmount;
	}

	/** Sets the minimum split amount
	 * @param minAmount the minimum split amount */
	public void setMinSplitAmount (float minAmount) {
		if (minAmount < 0) throw new GdxRuntimeException("minAmount has to be >= 0");
		if (minAmount >= maxAmount) throw new GdxRuntimeException("minAmount has to be < maxAmount");
		this.minAmount = minAmount;
	}

	/** Sets the maximum split amount
	 * @param maxAmount the maximum split amount */
	public void setMaxSplitAmount (float maxAmount) {
		if (maxAmount > 1) throw new GdxRuntimeException("maxAmount has to be >= 0");
		if (maxAmount <= minAmount) throw new GdxRuntimeException("maxAmount has to be > minAmount");
		this.maxAmount = maxAmount;
	}

	/** Sets the {@link Actor} instances embedded in this scroll pane. Invalidates the new Actor instances if they derrive from
	 * {@link Widget}
	 * @param firstWidget the first Actor
	 * @params secondtWidget the second Actor */
	public void setWidgets (Actor firstWidget, Actor secondWidget) {
		if (firstWidget == null) throw new IllegalArgumentException("firstWidget must not be null");
		if (secondWidget == null) throw new IllegalArgumentException("secondWidget must not be null");
		this.removeActor(this.firstWidget);
		this.removeActor(this.secondWidget);
		this.firstWidget = firstWidget;
		this.secondWidget = secondWidget;
		this.addActor(firstWidget);
		this.addActor(secondWidget);
		invalidate();
	}

	/** Defines the style of a split pane, see {@link SplitPane}
	 * @author mzechner */
	static public class SplitPaneStyle {
		public NinePatch handle;

		public SplitPaneStyle () {
		}

		public SplitPaneStyle (NinePatch handle) {
			this.handle = handle;
		}
	}
}

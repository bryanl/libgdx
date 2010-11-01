
package com.badlogic.gdx.twl.renderer;

import com.badlogic.gdx.InputProcessor;

import de.matthiasmann.twl.GUI;

/**
 * @author Nathan Sweet <misc@n4te.com>
 */
public class TwlInputListener implements InputProcessor {
	private final GUI gui;

	public TwlInputListener (GUI gui) {
		this.gui = gui;
	}

	public boolean keyDown (int keycode) {
		return gui.handleKey(keycode, (char)0, true);
	}

	public boolean keyUp (int keycode) {
		return gui.handleKey(keycode, (char)0, false);
	}

	public boolean keyTyped (char character) {
		boolean handled = gui.handleKey(0, character, true);
		return gui.handleKey(0, character, false) || handled;
	}

	public boolean touchDown (int x, int y, int pointer) {
		return gui.handleMouse(x, y, pointer, true);
	}

	public boolean touchUp (int x, int y, int pointer) {
		return gui.handleMouse(x, y, pointer, false);
	}

	public boolean touchDragged (int x, int y, int pointer) {
		return gui.handleMouse(x, y, -1, true);
	}
}

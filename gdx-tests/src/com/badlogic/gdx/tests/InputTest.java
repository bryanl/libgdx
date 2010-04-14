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
package com.badlogic.gdx.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.InputListener;
import com.badlogic.gdx.RenderListener;

public class InputTest implements RenderListener, InputListener
{
	Application app;
	
	@Override
	public void dispose(Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Application app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(Application app, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(Application app) {
		this.app = app;
		app.getInput().addInputListener( this );
		
	}

	@Override
	public boolean keyDown(int keycode) 
	{
		app.log( "Input Test", "key down: " + keycode );
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		app.log( "Input Test", "key typed: '" + character + "'" );
		return false;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		app.log( "Input Test", "key up: " + keycode );
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer) 
	{
		app.log( "Input Test", "touch down: " + x + ", " + y );
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		app.log( "Input Test", "touch dragged: " + x + ", " + y );
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer) {
		app.log( "Input Test", "touch up: " + x + ", " + y );
		return false;
	}


}

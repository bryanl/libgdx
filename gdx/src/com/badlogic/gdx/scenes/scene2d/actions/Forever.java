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

package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.TemporalAction;

public class Forever extends TemporalAction {
	static final ActionResetingPool<Forever> pool = new ActionResetingPool<Forever>(4, 100) {
		@Override
		protected Forever newObject () {
			return new Forever();
		}
	};

	public static Forever $ (Action action) {
		Forever forever = pool.obtain();
		forever.action = action;
		return forever;
	}

	@Override
	public void setTarget (Actor actor) {
		action.setTarget(actor);
		target = actor;
	}

	@Override
	public void act (float delta) {
		action.act(delta);
		if (action.isDone()) {
			Action oldAction = action;
			action = action.copy();
			oldAction.finish();
			action.setTarget(target);
		}
	}

	@Override
	public boolean isDone () {
		return false;
	}

	@Override
	public void finish () {
		pool.free(this);
		action.finish();
		super.finish();
	}

	@Override
	public Action copy () {
		return $(action.copy());
	}

	@Override
	public Actor getTarget () {
		return target;
	}
}

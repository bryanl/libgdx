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

package com.badlogic.gdx.tests;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.BufferUtils;

public class ManualBindTest extends GdxTest {
	int vboHandle;
	int iboHandle;
	ShortBuffer indices;
	FloatBuffer vertices1;
	FloatBuffer vertices2;

	@Override
	public boolean needsGL20 () {
		return false;
	}

	@Override
	public void render () {
		GL11 gl = Gdx.gl11;
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, vboHandle);
		gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, iboHandle);
		gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL11.GL_FLOAT, 5 * 4, 0);

		gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, indices.limit() * 2, null, GL11.GL_DYNAMIC_DRAW);
		gl.glBufferData(GL11.GL_ELEMENT_ARRAY_BUFFER, indices.limit() * 2, indices, GL11.GL_DYNAMIC_DRAW);
		gl.glBufferData(GL11.GL_ARRAY_BUFFER, vertices1.limit() * 4, null, GL11.GL_DYNAMIC_DRAW);
		gl.glBufferData(GL11.GL_ARRAY_BUFFER, vertices1.limit() * 4, vertices1, GL11.GL_DYNAMIC_DRAW);
		gl.glDrawElements(GL11.GL_TRIANGLES, 12, GL11.GL_UNSIGNED_SHORT, 0);

		gl.glBufferData(GL11.GL_ARRAY_BUFFER, vertices2.limit() * 4, null, GL11.GL_DYNAMIC_DRAW);
		gl.glBufferData(GL11.GL_ARRAY_BUFFER, vertices2.limit() * 4, vertices2, GL11.GL_DYNAMIC_DRAW);
		gl.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_SHORT, 0);

		gl.glBindBuffer(GL11.GL_ARRAY_BUFFER, 0);
		gl.glBindBuffer(GL11.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	@Override
	public void create () {
		GL11 gl = Gdx.graphics.getGL11();
		IntBuffer handleBuf = BufferUtils.newIntBuffer(2);
		gl.glGenBuffers(2, handleBuf);
		vboHandle = handleBuf.get(0);
		iboHandle = handleBuf.get(1);

		indices = BufferUtils.newShortBuffer(12);
		indices.put(new short[] {0, 1, 2, 2, 3, 0, 4, 5, 6, 6, 7, 4});
		indices.flip();

		float c = Color.toFloatBits(1f, 1f, 1f, 1f);
		vertices1 = BufferUtils.newFloatBuffer(40);
		vertices1.put(new float[] {-1, -1, c, 0, 0, -1, -0.5f, c, 0, 1, -0.5f, -0.5f, c, 1, 1, -0.5f, -1f, c, 1, 0, 0, -1, c, 0, 0,
			0, -0.5f, c, 0, 1, 0.5f, -0.5f, c, 1, 1, 0.5f, -1f, c, 1, 0});
		vertices1.flip();
		vertices2 = BufferUtils.newFloatBuffer(20);
		vertices2.put(new float[] {-1, 0, c, 0, 0, -1, 0.5f, c, 0, 1, -0.5f, 0.5f, c, 1, 1, -0.5f, 0, c, 1, 0});
		vertices2.flip();
	}
}

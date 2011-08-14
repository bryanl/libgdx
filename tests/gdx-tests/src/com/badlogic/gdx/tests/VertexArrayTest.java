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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.tests.utils.GdxTest;

public class VertexArrayTest extends GdxTest {
	FloatBuffer vertices;
	ShortBuffer indices;
	final int BYTES_PER_VERTEX = (3 + 4) * 4;

	@Override
	public void render () {
		GL10 gl = Gdx.graphics.getGL10();
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		vertices.position(3);
		gl.glColorPointer(4, GL10.GL_FLOAT, BYTES_PER_VERTEX, vertices);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		vertices.position(0);
		gl.glVertexPointer(3, GL10.GL_FLOAT, BYTES_PER_VERTEX, vertices);

		gl.glDrawElements(GL10.GL_TRIANGLES, 3, GL10.GL_UNSIGNED_SHORT, indices);
	}

	@Override
	public void create () {
		ByteBuffer buffer = ByteBuffer.allocateDirect(BYTES_PER_VERTEX * 3);
		buffer.order(ByteOrder.nativeOrder());
		vertices = buffer.asFloatBuffer();

		float[] verts = {-0.5f, -0.5f, 0, 1, 0, 0, 0,

		0.5f, -0.5f, 0, 0, 1, 0, 0,

		0, 0.5f, 0, 0, 0, 1, 0};
		vertices.put(verts);
		vertices.flip();

		buffer = ByteBuffer.allocateDirect(3 * 2);
		buffer.order(ByteOrder.nativeOrder());
		indices = buffer.asShortBuffer();
		indices.put(new short[] {0, 1, 2});
		indices.flip();
	}

	@Override
	public boolean needsGL20 () {
		return false;
	}
}

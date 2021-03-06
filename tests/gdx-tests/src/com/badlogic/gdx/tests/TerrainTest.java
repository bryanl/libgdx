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

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.TimeUtils;

public class TerrainTest extends GdxTest {
	ImmediateModeRenderer10 renderer;
	TerrainChunk chunk;
	Mesh mesh;
	PerspectiveCamera camera;
	Vector3 intersection = new Vector3();
	boolean intersected = false;
	long lastTime = TimeUtils.nanoTime();

	@Override
	public void create () {
		renderer = new ImmediateModeRenderer10();

		chunk = new TerrainChunk(32, 32, 4);

		Random rand = new Random();
		int len = chunk.vertices.length;
		for (int i = 3; i < len; i += 4)
			chunk.vertices[i] = Color.toFloatBits(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 255);

		mesh = new Mesh(true, chunk.vertices.length / 3, chunk.indices.length, new VertexAttribute(VertexAttributes.Usage.Position,
			3, "a_position"), new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, "a_color"));

		mesh.setVertices(chunk.vertices);
		mesh.setIndices(chunk.indices);

		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 5, 5);
		camera.direction.set(0, 0, 0).sub(camera.position).nor();
		camera.near = 0.5f;
		camera.far = 300;
	}

	@Override
	public void render () {
		GL10 gl = Gdx.graphics.getGL10();
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		camera.update();
		camera.apply(gl);
		gl.glColor4f(1, 1, 1, 1);
		mesh.render(GL10.GL_TRIANGLES);

		if (intersected) {
			gl.glPointSize(10);
			renderer.begin(GL10.GL_POINTS);
			renderer.color(1, 0, 0, 1);
			renderer.vertex(intersection.x, intersection.y, intersection.z);
			renderer.end();
		}

		handleInput(Gdx.input, Gdx.graphics.getDeltaTime());

		if (TimeUtils.nanoTime() - lastTime > 1000000000) {
			Gdx.app.log("TerrainTest", "fps: " + Gdx.graphics.getFramesPerSecond());
			lastTime = TimeUtils.nanoTime();
		}
	}

	private void handleInput (Input input, float delta) {
		if (input.isTouched()) {
			Ray ray = camera.getPickRay(input.getX(), input.getY());
			if (Intersector.intersectRayTriangles(ray, chunk.vertices, chunk.indices, 4, intersection)) intersected = true;
		} else {
			intersected = false;
		}

		if (input.isKeyPressed(Keys.W)) camera.position.z -= delta;
		if (input.isKeyPressed(Keys.S)) camera.position.z += delta;
		if (input.isKeyPressed(Keys.A)) camera.position.x -= delta;
		if (input.isKeyPressed(Keys.D)) camera.position.x += delta;
		if (input.isKeyPressed(Keys.Q)) camera.position.y += delta;
		if (input.isKeyPressed(Keys.E)) camera.position.y -= delta;
	}

	final static class TerrainChunk {
		public final byte[] heightMap;
		public final short width;
		public final short height;
		public final float[] vertices;
		public final short[] indices;
		public final int vertexSize;

		public TerrainChunk (int width, int height, int vertexSize) {
			if ((width + 1) * (height + 1) > Short.MAX_VALUE)
				throw new IllegalArgumentException("Chunk size too big, (width + 1)*(height+1) must be <= 32767");

			this.heightMap = new byte[(width + 1) * (height + 1)];
			this.width = (short)width;
			this.height = (short)height;
			this.vertices = new float[heightMap.length * vertexSize];
			this.indices = new short[width * height * 6];
			this.vertexSize = vertexSize;

			buildIndices();
			buildVertices();
		}

		public void buildVertices () {
			int heightPitch = height + 1;
			int widthPitch = width + 1;

			int idx = 0;
			int hIdx = 0;
			int inc = vertexSize - 3;

			for (int z = 0; z < heightPitch; z++) {
				for (int x = 0; x < widthPitch; x++) {
					vertices[idx++] = x;
					vertices[idx++] = heightMap[hIdx++];
					vertices[idx++] = z;
					idx += inc;
				}
			}
		}

		private void buildIndices () {
			int idx = 0;
			short pitch = (short)(width + 1);
			short i1 = 0;
			short i2 = 1;
			short i3 = (short)(1 + pitch);
			short i4 = pitch;

			short row = 0;

			for (int z = 0; z < height; z++) {
				for (int x = 0; x < width; x++) {
					indices[idx++] = i1;
					indices[idx++] = i2;
					indices[idx++] = i3;

					indices[idx++] = i3;
					indices[idx++] = i4;
					indices[idx++] = i1;

					i1++;
					i2++;
					i3++;
					i4++;
				}

				row += pitch;
				i1 = row;
				i2 = (short)(row + 1);
				i3 = (short)(i2 + pitch);
				i4 = (short)(row + pitch);
			}
		}
	}

	@Override
	public boolean needsGL20 () {
		return false;
	}
}

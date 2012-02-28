
package com.badlogic.gdx.graphics.g3d.experimental;

import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.backends.jogl.JoglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.LightManager;
import com.badlogic.gdx.graphics.g3d.lights.PointLight;
import com.badlogic.gdx.graphics.g3d.loaders.obj.ObjLoader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class HybridLightTest implements ApplicationListener {

	static final int LIGHTS_NUM = 4;
	static final float LIGHT_INTESITY = 1f;

	LightManager lightManager;

	PerspectiveCamController camController;
	PerspectiveCamera cam;

	Mesh mesh;
	Mesh mesh2;
	private Texture texture;
	private Texture texture2;

	FPSLogger logger = new FPSLogger();
	ShaderProgram lightShader;
	private Matrix4 modelMatrix = new Matrix4();
	private Matrix4 modelMatrix2 = new Matrix4();

	float timer;
	public void render () {

		logger.log();		

		final float delta = Gdx.graphics.getDeltaTime();
		camController.update(delta);
		
		timer+=delta;
		for (int i = 0; i < lightManager.pointLights.size; i++) {
			Vector3 v = lightManager.pointLights.get(i).position;
			v.x += MathUtils.sin(timer)*0.01f;
			v.z += MathUtils.cos(timer)*0.01f;
		}

		Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		Gdx.gl.glCullFace(GL10.GL_BACK);
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl.glDepthMask(true);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		
		texture.bind(0);

		lightShader.begin();
		
		lightManager.applyGlobalLights(lightShader);
		
		lightShader.setUniformMatrix("u_modelMatrix", modelMatrix2, false);

		lightShader.setUniformf("camPos", cam.position.x, cam.position.y, cam.position.z);
		lightShader.setUniformMatrix("u_projectionViewMatrix", cam.combined);
		lightShader.setUniformi("u_texture0", 0);
		lightShader.setUniformi("u_texture1", 1);
		lightManager.calculateLights(0, 2, -8);
		lightManager.applyLights(lightShader);

		mesh.render(lightShader, GL10.GL_TRIANGLES);

		texture2.bind(0);
		lightShader.setUniformMatrix("u_modelMatrix", modelMatrix, false);
		lightManager.calculateLights(0, 0, 0);
		lightManager.applyLights(lightShader);
		mesh2.render(lightShader, GL10.GL_TRIANGLES);

		lightShader.end();

	}

	public void create () {

		modelMatrix2.translate(0, 2, -8);
		lightShader = ShaderLoader.createShader("vertexpath", "vertexpath");
		//lightShader = ShaderLoader.createShader("light", "light");
		
		lightManager = new LightManager(LIGHTS_NUM);
		for (int i = 0; i < 4; i++) {
			PointLight l = new PointLight();
			l.position.set(MathUtils.random(16) - 8, MathUtils.random(6) - 2, -MathUtils.random(16) + 2);
			l.color.r = MathUtils.random();
			l.color.b = MathUtils.random();
			l.color.g = MathUtils.random();
			l.intensity = LIGHT_INTESITY;
			lightManager.addLigth(l);
		}
		lightManager.dirLight = new DirectionalLight();
		lightManager.dirLight.color.set(0.078f,0.09f,0.09f,0);
		lightManager.dirLight.direction.set(-.1f,-1,0.03f).nor();
		
		lightManager.ambientLight.set(0.02f,0.02f,0.02f,0f);
		
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 0.1f;
		cam.far = 64f;
		cam.position.set(0, 0.5f, -2f);
		cam.update();

		camController = new PerspectiveCamController(cam);
		Gdx.input.setInputProcessor(camController);

		texture = new Texture(Gdx.files.internal("data/multipleuvs_1.png"), null, true);
		texture.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);

		texture2 = new Texture(Gdx.files.internal("data/wall.png"), null, true);
		texture2.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Linear);


		try {
			InputStream in = Gdx.files.internal("data/models/sphere.obj").read();
			mesh = ObjLoader.loadObj(in);
			in.close();
			in = Gdx.files.internal("data/models/basicscene.obj").read();
			mesh2 = ObjLoader.loadObj(in);
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mesh2.scale(1.25f, 1.25f, 1.25f);

		mesh.getVertexAttribute(Usage.Position).alias = "a_position";
		mesh.getVertexAttribute(Usage.Normal).alias = "a_normal";
		// mesh.getVertexAttribute(Usage.TextureCoordinates).alias =
		// "a_texCoord0";

		mesh2.getVertexAttribute(Usage.Position).alias = "a_position";
		mesh2.getVertexAttribute(Usage.Normal).alias = "a_normal";
		// mesh2.getVertexAttribute(Usage.TextureCoordinates).alias =
		// "a_texCoord0";

	}

	public void resize (int width, int height) {
	}

	public void pause () {
	}

	public void dispose () {
		mesh.dispose();
		mesh2.dispose();
		texture.dispose();
		texture2.dispose();
		lightShader.dispose();

	}

	public void resume () {
	}

	public static void main (String[] argv) {
		JoglApplicationConfiguration config = new JoglApplicationConfiguration();
		config.title = "Hybrid Light";
		config.width = 800;
		config.height = 480;
		config.samples = 8;
		config.vSyncEnabled = true;
		config.useGL20 = true;
		new JoglApplication(new HybridLightTest(), config);
	}
}


package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.tests.utils.GdxTest;

public class FreeTypeTest extends GdxTest {
	BitmapFont font;
	SpriteBatch batch;
	BitmapFont ftFont;

	@Override
	public void create () {
		boolean flip = true;
		batch = new SpriteBatch();
		if(flip) {
			OrthographicCamera cam = new OrthographicCamera();
			cam.setToOrtho(flip);
			cam.update();
			batch.setProjectionMatrix(cam.combined);
		}
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), flip);
		FileHandle fontFile = Gdx.files.internal("data/arial.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
		FreeTypeBitmapFontData fontData = generator.generateData(15, FreeTypeFontGenerator.DEFAULT_CHARS, flip);
		generator.dispose();
		ftFont = new BitmapFont(fontData, fontData.getTextureRegion(), false);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.setColor(Color.RED);
		font.drawMultiLine(batch, "This is a test\nAnd another line", 100, 112);
		ftFont.drawMultiLine(batch, "This is a test\nAnd another line", 100, 112);
		batch.end();
	}

	@Override
	public boolean needsGL20 () {
		return true;
	}
}

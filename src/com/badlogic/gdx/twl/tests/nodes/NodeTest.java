package com.badlogic.gdx.twl.tests.nodes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.twl.TWL;

import de.matthiasmann.twl.ScrollPane;

public class NodeTest implements ApplicationListener, InputProcessor
{
	private TWL twl;
	private InputMultiplexer input = new InputMultiplexer();
	
	@Override
	public void create()
	{
		NodeArea nodeArea = new NodeArea();
        ScrollPane scrollPane = new ScrollPane(nodeArea);
        scrollPane.setExpandContentSize(true);
		
        SpriteBatch batch = new SpriteBatch();
		twl = new TWL(batch, "data/nodes.xml", FileType.Internal, scrollPane);
		input.addProcessor(twl);
		input.addProcessor(this);
		Gdx.input.setInputProcessor(twl);
		
		Node nodeSource = nodeArea.addNode("Source");
        nodeSource.setPosition(50, 50);
        Pad nodeSourceColor = nodeSource.addPad("Color", false);
        Pad nodeSourceAlpha = nodeSource.addPad("Alpha", false);

        Node nodeSink = nodeArea.addNode("Sink");
        nodeSink.setPosition(350, 200);
        Pad nodeSinkColor = nodeSink.addPad("Color", true);

        nodeArea.addConnection(nodeSourceColor, nodeSinkColor);
	}
	@Override
	public void resume()
	{
	}
	@Override
	public void render()
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		twl.render();
	}
	@Override
	public void resize(int width, int height)
	{
	}
	@Override
	public void pause()
	{
	}
	@Override
	public void dispose()
	{
		twl.dispose();
	}
	public boolean keyDown (int keycode) {
		return false;
	}

	public boolean keyUp (int keycode) {
		return false;
	}

	public boolean keyTyped (char character) {
		return false;
	}

	public boolean touchDown (int x, int y, int pointer, int button) {
		System.out.println("This touch made it through and was not handled by TWL.");
		return false;
	}

	public boolean touchUp (int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchDragged (int x, int y, int pointer) {
		return false;
	}

	@Override public boolean touchMoved (int x, int y) {
		return false;
	}

	@Override public boolean scrolled (int amount) {
		return false;
	}
}

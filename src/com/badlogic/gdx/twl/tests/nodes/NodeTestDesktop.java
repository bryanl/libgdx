package com.badlogic.gdx.twl.tests.nodes;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class NodeTestDesktop
{
	public static void main (String[] argv) 
	{
		new LwjglApplication(new NodeTest(), "Node Test", 1024, 600, false);
	}
}

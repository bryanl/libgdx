package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;

/**
 *  A rope joint enforces a maximum distance between two points
 * on two bodies. It has no other effect.
 * Warning: if you attempt to change the maximum length during
 * the simulation you will get some non-physical behavior.
 * A model that would allow you to dynamically modify the length
 * would have some sponginess, so I chose not to implement it
 * that way. See b2DistanceJoint if you want to dynamically
 * control length.
*/
public class RopeJoint extends Joint {
	public RopeJoint (World world, long addr) {
		super(world, addr);
	}

	/**
	 * Get the maximum length of the rope.
	 */
	public float getMaxLength() {
		return jniGetMaxLength(addr);
	}
	
	private native float jniGetMaxLength(long addr);
}

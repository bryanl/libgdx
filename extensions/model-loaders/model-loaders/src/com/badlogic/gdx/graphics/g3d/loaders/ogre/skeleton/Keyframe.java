
package com.badlogic.gdx.graphics.g3d.loaders.ogre.skeleton;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"translate", "rotate", "scale"})
@XmlRootElement(name = "keyframe")
public class Keyframe {

	@XmlAttribute(required = true)
	public float time;
	public Translate translate;
	public Rotate rotate;
	public Scale scale;
}

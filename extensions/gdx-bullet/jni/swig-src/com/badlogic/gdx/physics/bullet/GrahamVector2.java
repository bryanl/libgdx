/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;

public class GrahamVector2 extends btVector3 {
  private long swigCPtr;

  protected GrahamVector2(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.GrahamVector2_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(GrahamVector2 obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_GrahamVector2(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public GrahamVector2(Vector3 org, int orgIndex) {
    this(gdxBulletJNI.new_GrahamVector2(org, orgIndex), true);
  }

  public void setM_angle(float value) {
    gdxBulletJNI.GrahamVector2_m_angle_set(swigCPtr, this, value);
  }

  public float getM_angle() {
    return gdxBulletJNI.GrahamVector2_m_angle_get(swigCPtr, this);
  }

  public void setM_orgIndex(int value) {
    gdxBulletJNI.GrahamVector2_m_orgIndex_set(swigCPtr, this, value);
  }

  public int getM_orgIndex() {
    return gdxBulletJNI.GrahamVector2_m_orgIndex_get(swigCPtr, this);
  }

}

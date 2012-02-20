/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;

public class btPlane {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected btPlane(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btPlane obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btPlane(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public void setNormal(btVector3 value) {
    gdxBulletJNI.btPlane_normal_set(swigCPtr, this, btVector3.getCPtr(value), value);
  }

  public btVector3 getNormal() {
    long cPtr = gdxBulletJNI.btPlane_normal_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3(cPtr, false);
  }

  public void setDist(float value) {
    gdxBulletJNI.btPlane_dist_set(swigCPtr, this, value);
  }

  public float getDist() {
    return gdxBulletJNI.btPlane_dist_get(swigCPtr, this);
  }

  public btPlane(Vector3 n, float d) {
    this(gdxBulletJNI.new_btPlane__SWIG_0(n, d), true);
  }

  public btPlane() {
    this(gdxBulletJNI.new_btPlane__SWIG_1(), true);
  }

}

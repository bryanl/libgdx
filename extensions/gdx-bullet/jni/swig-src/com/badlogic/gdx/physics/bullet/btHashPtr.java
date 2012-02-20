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

public class btHashPtr {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected btHashPtr(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btHashPtr obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btHashPtr(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btHashPtr(SWIGTYPE_p_void ptr) {
    this(gdxBulletJNI.new_btHashPtr(SWIGTYPE_p_void.getCPtr(ptr)), true);
  }

  public SWIGTYPE_p_void getPointer() {
    long cPtr = gdxBulletJNI.btHashPtr_getPointer(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public boolean equals(btHashPtr other) {
    return gdxBulletJNI.btHashPtr_equals(swigCPtr, this, btHashPtr.getCPtr(other), other);
  }

  public long getHash() {
    return gdxBulletJNI.btHashPtr_getHash(swigCPtr, this);
  }

}

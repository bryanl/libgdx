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

public class btCompoundCollisionAlgorithm extends btActivatingCollisionAlgorithm {
  private long swigCPtr;

  protected btCompoundCollisionAlgorithm(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btCompoundCollisionAlgorithm_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btCompoundCollisionAlgorithm obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btCompoundCollisionAlgorithm(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btCompoundCollisionAlgorithm(btCollisionAlgorithmConstructionInfo ci, btCollisionObject body0, btCollisionObject body1, boolean isSwapped) {
    this(gdxBulletJNI.new_btCompoundCollisionAlgorithm(btCollisionAlgorithmConstructionInfo.getCPtr(ci), ci, btCollisionObject.getCPtr(body0), body0, btCollisionObject.getCPtr(body1), body1, isSwapped), true);
  }

}

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

public class btConvex2dConvex2dAlgorithm extends btActivatingCollisionAlgorithm {
  private long swigCPtr;

  protected btConvex2dConvex2dAlgorithm(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btConvex2dConvex2dAlgorithm_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btConvex2dConvex2dAlgorithm obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btConvex2dConvex2dAlgorithm(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btConvex2dConvex2dAlgorithm(btPersistentManifold mf, btCollisionAlgorithmConstructionInfo ci, btCollisionObject body0, btCollisionObject body1, SWIGTYPE_p_btSimplexSolverInterface simplexSolver, btConvexPenetrationDepthSolver pdSolver, int numPerturbationIterations, int minimumPointsPerturbationThreshold) {
    this(gdxBulletJNI.new_btConvex2dConvex2dAlgorithm(btPersistentManifold.getCPtr(mf), mf, btCollisionAlgorithmConstructionInfo.getCPtr(ci), ci, btCollisionObject.getCPtr(body0), body0, btCollisionObject.getCPtr(body1), body1, SWIGTYPE_p_btSimplexSolverInterface.getCPtr(simplexSolver), btConvexPenetrationDepthSolver.getCPtr(pdSolver), pdSolver, numPerturbationIterations, minimumPointsPerturbationThreshold), true);
  }

  public void setLowLevelOfDetail(boolean useLowLevel) {
    gdxBulletJNI.btConvex2dConvex2dAlgorithm_setLowLevelOfDetail(swigCPtr, this, useLowLevel);
  }

  public btPersistentManifold getManifold() {
    long cPtr = gdxBulletJNI.btConvex2dConvex2dAlgorithm_getManifold(swigCPtr, this);
    return (cPtr == 0) ? null : new btPersistentManifold(cPtr, false);
  }

}

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

public class btDefaultCollisionConfiguration extends btCollisionConfiguration {
  private long swigCPtr;

  protected btDefaultCollisionConfiguration(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btDefaultCollisionConfiguration_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btDefaultCollisionConfiguration obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btDefaultCollisionConfiguration(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btDefaultCollisionConfiguration(btDefaultCollisionConstructionInfo constructionInfo) {
    this(gdxBulletJNI.new_btDefaultCollisionConfiguration__SWIG_0(btDefaultCollisionConstructionInfo.getCPtr(constructionInfo), constructionInfo), true);
  }

  public btDefaultCollisionConfiguration() {
    this(gdxBulletJNI.new_btDefaultCollisionConfiguration__SWIG_1(), true);
  }

  public btVoronoiSimplexSolver getSimplexSolver() {
    long cPtr = gdxBulletJNI.btDefaultCollisionConfiguration_getSimplexSolver(swigCPtr, this);
    return (cPtr == 0) ? null : new btVoronoiSimplexSolver(cPtr, false);
  }

  public void setConvexConvexMultipointIterations(int numPerturbationIterations, int minimumPointsPerturbationThreshold) {
    gdxBulletJNI.btDefaultCollisionConfiguration_setConvexConvexMultipointIterations__SWIG_0(swigCPtr, this, numPerturbationIterations, minimumPointsPerturbationThreshold);
  }

  public void setConvexConvexMultipointIterations(int numPerturbationIterations) {
    gdxBulletJNI.btDefaultCollisionConfiguration_setConvexConvexMultipointIterations__SWIG_1(swigCPtr, this, numPerturbationIterations);
  }

  public void setConvexConvexMultipointIterations() {
    gdxBulletJNI.btDefaultCollisionConfiguration_setConvexConvexMultipointIterations__SWIG_2(swigCPtr, this);
  }

  public void setPlaneConvexMultipointIterations(int numPerturbationIterations, int minimumPointsPerturbationThreshold) {
    gdxBulletJNI.btDefaultCollisionConfiguration_setPlaneConvexMultipointIterations__SWIG_0(swigCPtr, this, numPerturbationIterations, minimumPointsPerturbationThreshold);
  }

  public void setPlaneConvexMultipointIterations(int numPerturbationIterations) {
    gdxBulletJNI.btDefaultCollisionConfiguration_setPlaneConvexMultipointIterations__SWIG_1(swigCPtr, this, numPerturbationIterations);
  }

  public void setPlaneConvexMultipointIterations() {
    gdxBulletJNI.btDefaultCollisionConfiguration_setPlaneConvexMultipointIterations__SWIG_2(swigCPtr, this);
  }

}

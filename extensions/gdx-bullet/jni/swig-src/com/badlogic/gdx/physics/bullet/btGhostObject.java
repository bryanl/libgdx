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

public class btGhostObject extends btCollisionObject {
  private long swigCPtr;

  protected btGhostObject(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btGhostObject_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btGhostObject obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btGhostObject(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public btGhostObject() {
    this(gdxBulletJNI.new_btGhostObject(), true);
  }

  public void convexSweepTest(btConvexShape castShape, btTransform convexFromWorld, btTransform convexToWorld, SWIGTYPE_p_btCollisionWorld__ConvexResultCallback resultCallback, float allowedCcdPenetration) {
    gdxBulletJNI.btGhostObject_convexSweepTest__SWIG_0(swigCPtr, this, btConvexShape.getCPtr(castShape), castShape, btTransform.getCPtr(convexFromWorld), convexFromWorld, btTransform.getCPtr(convexToWorld), convexToWorld, SWIGTYPE_p_btCollisionWorld__ConvexResultCallback.getCPtr(resultCallback), allowedCcdPenetration);
  }

  public void convexSweepTest(btConvexShape castShape, btTransform convexFromWorld, btTransform convexToWorld, SWIGTYPE_p_btCollisionWorld__ConvexResultCallback resultCallback) {
    gdxBulletJNI.btGhostObject_convexSweepTest__SWIG_1(swigCPtr, this, btConvexShape.getCPtr(castShape), castShape, btTransform.getCPtr(convexFromWorld), convexFromWorld, btTransform.getCPtr(convexToWorld), convexToWorld, SWIGTYPE_p_btCollisionWorld__ConvexResultCallback.getCPtr(resultCallback));
  }

  public void rayTest(Vector3 rayFromWorld, Vector3 rayToWorld, SWIGTYPE_p_btCollisionWorld__RayResultCallback resultCallback) {
    gdxBulletJNI.btGhostObject_rayTest(swigCPtr, this, rayFromWorld, rayToWorld, SWIGTYPE_p_btCollisionWorld__RayResultCallback.getCPtr(resultCallback));
  }

  public void addOverlappingObjectInternal(btBroadphaseProxy otherProxy, btBroadphaseProxy thisProxy) {
    gdxBulletJNI.btGhostObject_addOverlappingObjectInternal__SWIG_0(swigCPtr, this, btBroadphaseProxy.getCPtr(otherProxy), otherProxy, btBroadphaseProxy.getCPtr(thisProxy), thisProxy);
  }

  public void addOverlappingObjectInternal(btBroadphaseProxy otherProxy) {
    gdxBulletJNI.btGhostObject_addOverlappingObjectInternal__SWIG_1(swigCPtr, this, btBroadphaseProxy.getCPtr(otherProxy), otherProxy);
  }

  public void removeOverlappingObjectInternal(btBroadphaseProxy otherProxy, btDispatcher dispatcher, btBroadphaseProxy thisProxy) {
    gdxBulletJNI.btGhostObject_removeOverlappingObjectInternal__SWIG_0(swigCPtr, this, btBroadphaseProxy.getCPtr(otherProxy), otherProxy, btDispatcher.getCPtr(dispatcher), dispatcher, btBroadphaseProxy.getCPtr(thisProxy), thisProxy);
  }

  public void removeOverlappingObjectInternal(btBroadphaseProxy otherProxy, btDispatcher dispatcher) {
    gdxBulletJNI.btGhostObject_removeOverlappingObjectInternal__SWIG_1(swigCPtr, this, btBroadphaseProxy.getCPtr(otherProxy), otherProxy, btDispatcher.getCPtr(dispatcher), dispatcher);
  }

  public int getNumOverlappingObjects() {
    return gdxBulletJNI.btGhostObject_getNumOverlappingObjects(swigCPtr, this);
  }

  public btCollisionObject getOverlappingObject(int index) {
    long cPtr = gdxBulletJNI.btGhostObject_getOverlappingObject__SWIG_0(swigCPtr, this, index);
    return (cPtr == 0) ? null : new btCollisionObject(cPtr, false);
  }

  public btCollisionObjectArray getOverlappingPairs() {
    return new btCollisionObjectArray(gdxBulletJNI.btGhostObject_getOverlappingPairs__SWIG_0(swigCPtr, this), false);
  }

  public static btGhostObject upcast(btCollisionObject colObj) {
    long cPtr = gdxBulletJNI.btGhostObject_upcast__SWIG_0(btCollisionObject.getCPtr(colObj), colObj);
    return (cPtr == 0) ? null : new btGhostObject(cPtr, false);
  }

}

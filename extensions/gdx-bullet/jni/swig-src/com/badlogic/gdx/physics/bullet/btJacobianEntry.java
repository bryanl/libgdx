/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;

public class btJacobianEntry {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  public btJacobianEntry(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btJacobianEntry obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btJacobianEntry(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btJacobianEntry() {
    this(gdxBulletJNI.new_btJacobianEntry__SWIG_0(), true);
  }

  public btJacobianEntry(Matrix3 world2A, Matrix3 world2B, Vector3 rel_pos1, Vector3 rel_pos2, Vector3 jointAxis, Vector3 inertiaInvA, float massInvA, Vector3 inertiaInvB, float massInvB) {
    this(gdxBulletJNI.new_btJacobianEntry__SWIG_1(world2A, world2B, rel_pos1, rel_pos2, jointAxis, inertiaInvA, massInvA, inertiaInvB, massInvB), true);
  }

  public btJacobianEntry(Vector3 jointAxis, Matrix3 world2A, Matrix3 world2B, Vector3 inertiaInvA, Vector3 inertiaInvB) {
    this(gdxBulletJNI.new_btJacobianEntry__SWIG_2(jointAxis, world2A, world2B, inertiaInvA, inertiaInvB), true);
  }

  public btJacobianEntry(Vector3 axisInA, Vector3 axisInB, Vector3 inertiaInvA, Vector3 inertiaInvB) {
    this(gdxBulletJNI.new_btJacobianEntry__SWIG_3(axisInA, axisInB, inertiaInvA, inertiaInvB), true);
  }

  public btJacobianEntry(Matrix3 world2A, Vector3 rel_pos1, Vector3 rel_pos2, Vector3 jointAxis, Vector3 inertiaInvA, float massInvA) {
    this(gdxBulletJNI.new_btJacobianEntry__SWIG_4(world2A, rel_pos1, rel_pos2, jointAxis, inertiaInvA, massInvA), true);
  }

  public float getDiagonal() {
    return gdxBulletJNI.btJacobianEntry_getDiagonal(swigCPtr, this);
  }

  public float getNonDiagonal(btJacobianEntry jacB, float massInvA) {
    return gdxBulletJNI.btJacobianEntry_getNonDiagonal__SWIG_0(swigCPtr, this, btJacobianEntry.getCPtr(jacB), jacB, massInvA);
  }

  public float getNonDiagonal(btJacobianEntry jacB, float massInvA, float massInvB) {
    return gdxBulletJNI.btJacobianEntry_getNonDiagonal__SWIG_1(swigCPtr, this, btJacobianEntry.getCPtr(jacB), jacB, massInvA, massInvB);
  }

  public float getRelativeVelocity(Vector3 linvelA, Vector3 angvelA, Vector3 linvelB, Vector3 angvelB) {
    return gdxBulletJNI.btJacobianEntry_getRelativeVelocity(swigCPtr, this, linvelA, angvelA, linvelB, angvelB);
  }

  public void setM_linearJointAxis(btVector3 value) {
    gdxBulletJNI.btJacobianEntry_m_linearJointAxis_set(swigCPtr, this, btVector3.getCPtr(value), value);
  }

  public btVector3 getM_linearJointAxis() {
    long cPtr = gdxBulletJNI.btJacobianEntry_m_linearJointAxis_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3(cPtr, false);
  }

  public void setM_aJ(btVector3 value) {
    gdxBulletJNI.btJacobianEntry_m_aJ_set(swigCPtr, this, btVector3.getCPtr(value), value);
  }

  public btVector3 getM_aJ() {
    long cPtr = gdxBulletJNI.btJacobianEntry_m_aJ_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3(cPtr, false);
  }

  public void setM_bJ(btVector3 value) {
    gdxBulletJNI.btJacobianEntry_m_bJ_set(swigCPtr, this, btVector3.getCPtr(value), value);
  }

  public btVector3 getM_bJ() {
    long cPtr = gdxBulletJNI.btJacobianEntry_m_bJ_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3(cPtr, false);
  }

  public void setM_0MinvJt(btVector3 value) {
    gdxBulletJNI.btJacobianEntry_m_0MinvJt_set(swigCPtr, this, btVector3.getCPtr(value), value);
  }

  public btVector3 getM_0MinvJt() {
    long cPtr = gdxBulletJNI.btJacobianEntry_m_0MinvJt_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3(cPtr, false);
  }

  public void setM_1MinvJt(btVector3 value) {
    gdxBulletJNI.btJacobianEntry_m_1MinvJt_set(swigCPtr, this, btVector3.getCPtr(value), value);
  }

  public btVector3 getM_1MinvJt() {
    long cPtr = gdxBulletJNI.btJacobianEntry_m_1MinvJt_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3(cPtr, false);
  }

  public void setM_Adiag(float value) {
    gdxBulletJNI.btJacobianEntry_m_Adiag_set(swigCPtr, this, value);
  }

  public float getM_Adiag() {
    return gdxBulletJNI.btJacobianEntry_m_Adiag_get(swigCPtr, this);
  }

}

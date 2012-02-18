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

public class btPoint2PointConstraintDoubleData {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  public btPoint2PointConstraintDoubleData(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btPoint2PointConstraintDoubleData obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btPoint2PointConstraintDoubleData(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public void setM_typeConstraintData(btTypedConstraintData value) {
    gdxBulletJNI.btPoint2PointConstraintDoubleData_m_typeConstraintData_set(swigCPtr, this, btTypedConstraintData.getCPtr(value), value);
  }

  public btTypedConstraintData getM_typeConstraintData() {
    long cPtr = gdxBulletJNI.btPoint2PointConstraintDoubleData_m_typeConstraintData_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btTypedConstraintData(cPtr, false);
  }

  public void setM_pivotInA(btVector3DoubleData value) {
    gdxBulletJNI.btPoint2PointConstraintDoubleData_m_pivotInA_set(swigCPtr, this, btVector3DoubleData.getCPtr(value), value);
  }

  public btVector3DoubleData getM_pivotInA() {
    long cPtr = gdxBulletJNI.btPoint2PointConstraintDoubleData_m_pivotInA_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3DoubleData(cPtr, false);
  }

  public void setM_pivotInB(btVector3DoubleData value) {
    gdxBulletJNI.btPoint2PointConstraintDoubleData_m_pivotInB_set(swigCPtr, this, btVector3DoubleData.getCPtr(value), value);
  }

  public btVector3DoubleData getM_pivotInB() {
    long cPtr = gdxBulletJNI.btPoint2PointConstraintDoubleData_m_pivotInB_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3DoubleData(cPtr, false);
  }

  public btPoint2PointConstraintDoubleData() {
    this(gdxBulletJNI.new_btPoint2PointConstraintDoubleData(), true);
  }

}

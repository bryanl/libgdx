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

public class btSliderConstraintData {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  public btSliderConstraintData(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btSliderConstraintData obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btSliderConstraintData(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public void setM_typeConstraintData(btTypedConstraintData value) {
    gdxBulletJNI.btSliderConstraintData_m_typeConstraintData_set(swigCPtr, this, btTypedConstraintData.getCPtr(value), value);
  }

  public btTypedConstraintData getM_typeConstraintData() {
    long cPtr = gdxBulletJNI.btSliderConstraintData_m_typeConstraintData_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btTypedConstraintData(cPtr, false);
  }

  public void setM_rbAFrame(btTransformFloatData value) {
    gdxBulletJNI.btSliderConstraintData_m_rbAFrame_set(swigCPtr, this, btTransformFloatData.getCPtr(value), value);
  }

  public btTransformFloatData getM_rbAFrame() {
    long cPtr = gdxBulletJNI.btSliderConstraintData_m_rbAFrame_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btTransformFloatData(cPtr, false);
  }

  public void setM_rbBFrame(btTransformFloatData value) {
    gdxBulletJNI.btSliderConstraintData_m_rbBFrame_set(swigCPtr, this, btTransformFloatData.getCPtr(value), value);
  }

  public btTransformFloatData getM_rbBFrame() {
    long cPtr = gdxBulletJNI.btSliderConstraintData_m_rbBFrame_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btTransformFloatData(cPtr, false);
  }

  public void setM_linearUpperLimit(float value) {
    gdxBulletJNI.btSliderConstraintData_m_linearUpperLimit_set(swigCPtr, this, value);
  }

  public float getM_linearUpperLimit() {
    return gdxBulletJNI.btSliderConstraintData_m_linearUpperLimit_get(swigCPtr, this);
  }

  public void setM_linearLowerLimit(float value) {
    gdxBulletJNI.btSliderConstraintData_m_linearLowerLimit_set(swigCPtr, this, value);
  }

  public float getM_linearLowerLimit() {
    return gdxBulletJNI.btSliderConstraintData_m_linearLowerLimit_get(swigCPtr, this);
  }

  public void setM_angularUpperLimit(float value) {
    gdxBulletJNI.btSliderConstraintData_m_angularUpperLimit_set(swigCPtr, this, value);
  }

  public float getM_angularUpperLimit() {
    return gdxBulletJNI.btSliderConstraintData_m_angularUpperLimit_get(swigCPtr, this);
  }

  public void setM_angularLowerLimit(float value) {
    gdxBulletJNI.btSliderConstraintData_m_angularLowerLimit_set(swigCPtr, this, value);
  }

  public float getM_angularLowerLimit() {
    return gdxBulletJNI.btSliderConstraintData_m_angularLowerLimit_get(swigCPtr, this);
  }

  public void setM_useLinearReferenceFrameA(int value) {
    gdxBulletJNI.btSliderConstraintData_m_useLinearReferenceFrameA_set(swigCPtr, this, value);
  }

  public int getM_useLinearReferenceFrameA() {
    return gdxBulletJNI.btSliderConstraintData_m_useLinearReferenceFrameA_get(swigCPtr, this);
  }

  public void setM_useOffsetForConstraintFrame(int value) {
    gdxBulletJNI.btSliderConstraintData_m_useOffsetForConstraintFrame_set(swigCPtr, this, value);
  }

  public int getM_useOffsetForConstraintFrame() {
    return gdxBulletJNI.btSliderConstraintData_m_useOffsetForConstraintFrame_get(swigCPtr, this);
  }

  public btSliderConstraintData() {
    this(gdxBulletJNI.new_btSliderConstraintData(), true);
  }

}

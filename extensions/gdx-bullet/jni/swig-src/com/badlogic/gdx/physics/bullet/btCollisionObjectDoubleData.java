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

public class btCollisionObjectDoubleData {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected btCollisionObjectDoubleData(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btCollisionObjectDoubleData obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btCollisionObjectDoubleData(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setM_broadphaseHandle(SWIGTYPE_p_void value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_broadphaseHandle_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getM_broadphaseHandle() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_broadphaseHandle_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setM_collisionShape(SWIGTYPE_p_void value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_collisionShape_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getM_collisionShape() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_collisionShape_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setM_rootCollisionShape(btCollisionShapeData value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_rootCollisionShape_set(swigCPtr, this, btCollisionShapeData.getCPtr(value), value);
  }

  public btCollisionShapeData getM_rootCollisionShape() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_rootCollisionShape_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btCollisionShapeData(cPtr, false);
  }

  public void setM_name(String value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_name_set(swigCPtr, this, value);
  }

  public String getM_name() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_name_get(swigCPtr, this);
  }

  public void setM_worldTransform(btTransformDoubleData value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_worldTransform_set(swigCPtr, this, btTransformDoubleData.getCPtr(value), value);
  }

  public btTransformDoubleData getM_worldTransform() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_worldTransform_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btTransformDoubleData(cPtr, false);
  }

  public void setM_interpolationWorldTransform(btTransformDoubleData value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_interpolationWorldTransform_set(swigCPtr, this, btTransformDoubleData.getCPtr(value), value);
  }

  public btTransformDoubleData getM_interpolationWorldTransform() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_interpolationWorldTransform_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btTransformDoubleData(cPtr, false);
  }

  public void setM_interpolationLinearVelocity(btVector3DoubleData value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_interpolationLinearVelocity_set(swigCPtr, this, btVector3DoubleData.getCPtr(value), value);
  }

  public btVector3DoubleData getM_interpolationLinearVelocity() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_interpolationLinearVelocity_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3DoubleData(cPtr, false);
  }

  public void setM_interpolationAngularVelocity(btVector3DoubleData value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_interpolationAngularVelocity_set(swigCPtr, this, btVector3DoubleData.getCPtr(value), value);
  }

  public btVector3DoubleData getM_interpolationAngularVelocity() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_interpolationAngularVelocity_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3DoubleData(cPtr, false);
  }

  public void setM_anisotropicFriction(btVector3DoubleData value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_anisotropicFriction_set(swigCPtr, this, btVector3DoubleData.getCPtr(value), value);
  }

  public btVector3DoubleData getM_anisotropicFriction() {
    long cPtr = gdxBulletJNI.btCollisionObjectDoubleData_m_anisotropicFriction_get(swigCPtr, this);
    return (cPtr == 0) ? null : new btVector3DoubleData(cPtr, false);
  }

  public void setM_contactProcessingThreshold(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_contactProcessingThreshold_set(swigCPtr, this, value);
  }

  public double getM_contactProcessingThreshold() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_contactProcessingThreshold_get(swigCPtr, this);
  }

  public void setM_deactivationTime(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_deactivationTime_set(swigCPtr, this, value);
  }

  public double getM_deactivationTime() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_deactivationTime_get(swigCPtr, this);
  }

  public void setM_friction(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_friction_set(swigCPtr, this, value);
  }

  public double getM_friction() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_friction_get(swigCPtr, this);
  }

  public void setM_restitution(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_restitution_set(swigCPtr, this, value);
  }

  public double getM_restitution() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_restitution_get(swigCPtr, this);
  }

  public void setM_hitFraction(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_hitFraction_set(swigCPtr, this, value);
  }

  public double getM_hitFraction() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_hitFraction_get(swigCPtr, this);
  }

  public void setM_ccdSweptSphereRadius(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_ccdSweptSphereRadius_set(swigCPtr, this, value);
  }

  public double getM_ccdSweptSphereRadius() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_ccdSweptSphereRadius_get(swigCPtr, this);
  }

  public void setM_ccdMotionThreshold(double value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_ccdMotionThreshold_set(swigCPtr, this, value);
  }

  public double getM_ccdMotionThreshold() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_ccdMotionThreshold_get(swigCPtr, this);
  }

  public void setM_hasAnisotropicFriction(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_hasAnisotropicFriction_set(swigCPtr, this, value);
  }

  public int getM_hasAnisotropicFriction() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_hasAnisotropicFriction_get(swigCPtr, this);
  }

  public void setM_collisionFlags(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_collisionFlags_set(swigCPtr, this, value);
  }

  public int getM_collisionFlags() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_collisionFlags_get(swigCPtr, this);
  }

  public void setM_islandTag1(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_islandTag1_set(swigCPtr, this, value);
  }

  public int getM_islandTag1() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_islandTag1_get(swigCPtr, this);
  }

  public void setM_companionId(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_companionId_set(swigCPtr, this, value);
  }

  public int getM_companionId() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_companionId_get(swigCPtr, this);
  }

  public void setM_activationState1(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_activationState1_set(swigCPtr, this, value);
  }

  public int getM_activationState1() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_activationState1_get(swigCPtr, this);
  }

  public void setM_internalType(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_internalType_set(swigCPtr, this, value);
  }

  public int getM_internalType() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_internalType_get(swigCPtr, this);
  }

  public void setM_checkCollideWith(int value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_checkCollideWith_set(swigCPtr, this, value);
  }

  public int getM_checkCollideWith() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_checkCollideWith_get(swigCPtr, this);
  }

  public void setM_padding(String value) {
    gdxBulletJNI.btCollisionObjectDoubleData_m_padding_set(swigCPtr, this, value);
  }

  public String getM_padding() {
    return gdxBulletJNI.btCollisionObjectDoubleData_m_padding_get(swigCPtr, this);
  }

  public btCollisionObjectDoubleData() {
    this(gdxBulletJNI.new_btCollisionObjectDoubleData(), true);
  }

}

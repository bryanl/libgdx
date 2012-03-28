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

public class btVector3 {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected btVector3(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btVector3 obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btVector3(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setM_floats(float[] value) {
    gdxBulletJNI.btVector3_m_floats_set(swigCPtr, this, value);
  }

  public float[] getM_floats() {
    return gdxBulletJNI.btVector3_m_floats_get(swigCPtr, this);
  }

  public btVector3() {
    this(gdxBulletJNI.new_btVector3__SWIG_0(), true);
  }

  public btVector3(float x, float y, float z) {
    this(gdxBulletJNI.new_btVector3__SWIG_1(x, y, z), true);
  }

  public float dot(Vector3 v) {
    return gdxBulletJNI.btVector3_dot(swigCPtr, this, v);
  }

  public float length2() {
    return gdxBulletJNI.btVector3_length2(swigCPtr, this);
  }

  public float length() {
    return gdxBulletJNI.btVector3_length(swigCPtr, this);
  }

  public float distance2(Vector3 v) {
    return gdxBulletJNI.btVector3_distance2(swigCPtr, this, v);
  }

  public float distance(Vector3 v) {
    return gdxBulletJNI.btVector3_distance(swigCPtr, this, v);
  }

  public Vector3 safeNormalize() {
	return gdxBulletJNI.btVector3_safeNormalize(swigCPtr, this);
}

  public Vector3 normalize() {
	return gdxBulletJNI.btVector3_normalize(swigCPtr, this);
}

  public Vector3 normalized() {
	return gdxBulletJNI.btVector3_normalized(swigCPtr, this);
}

  public Vector3 rotate(Vector3 wAxis, float angle) {
	return gdxBulletJNI.btVector3_rotate(swigCPtr, this, wAxis, angle);
}

  public float angle(Vector3 v) {
    return gdxBulletJNI.btVector3_angle(swigCPtr, this, v);
  }

  public Vector3 absolute() {
	return gdxBulletJNI.btVector3_absolute(swigCPtr, this);
}

  public Vector3 cross(Vector3 v) {
	return gdxBulletJNI.btVector3_cross(swigCPtr, this, v);
}

  public float triple(Vector3 v1, Vector3 v2) {
    return gdxBulletJNI.btVector3_triple(swigCPtr, this, v1, v2);
  }

  public int minAxis() {
    return gdxBulletJNI.btVector3_minAxis(swigCPtr, this);
  }

  public int maxAxis() {
    return gdxBulletJNI.btVector3_maxAxis(swigCPtr, this);
  }

  public int furthestAxis() {
    return gdxBulletJNI.btVector3_furthestAxis(swigCPtr, this);
  }

  public int closestAxis() {
    return gdxBulletJNI.btVector3_closestAxis(swigCPtr, this);
  }

  public void setInterpolate3(Vector3 v0, Vector3 v1, float rt) {
    gdxBulletJNI.btVector3_setInterpolate3(swigCPtr, this, v0, v1, rt);
  }

  public Vector3 lerp(Vector3 v, float t) {
	return gdxBulletJNI.btVector3_lerp(swigCPtr, this, v, t);
}

  public float getX() {
    return gdxBulletJNI.btVector3_getX(swigCPtr, this);
  }

  public float getY() {
    return gdxBulletJNI.btVector3_getY(swigCPtr, this);
  }

  public float getZ() {
    return gdxBulletJNI.btVector3_getZ(swigCPtr, this);
  }

  public void setX(float x) {
    gdxBulletJNI.btVector3_setX(swigCPtr, this, x);
  }

  public void setY(float y) {
    gdxBulletJNI.btVector3_setY(swigCPtr, this, y);
  }

  public void setZ(float z) {
    gdxBulletJNI.btVector3_setZ(swigCPtr, this, z);
  }

  public void setW(float w) {
    gdxBulletJNI.btVector3_setW(swigCPtr, this, w);
  }

  public float x() {
    return gdxBulletJNI.btVector3_x(swigCPtr, this);
  }

  public float y() {
    return gdxBulletJNI.btVector3_y(swigCPtr, this);
  }

  public float z() {
    return gdxBulletJNI.btVector3_z(swigCPtr, this);
  }

  public float w() {
    return gdxBulletJNI.btVector3_w(swigCPtr, this);
  }

  public void setMax(Vector3 other) {
    gdxBulletJNI.btVector3_setMax(swigCPtr, this, other);
  }

  public void setMin(Vector3 other) {
    gdxBulletJNI.btVector3_setMin(swigCPtr, this, other);
  }

  public void setValue(float x, float y, float z) {
    gdxBulletJNI.btVector3_setValue(swigCPtr, this, x, y, z);
  }

  public void getSkewSymmetricMatrix(btVector3 v0, btVector3 v1, btVector3 v2) {
    gdxBulletJNI.btVector3_getSkewSymmetricMatrix(swigCPtr, this, btVector3.getCPtr(v0), v0, btVector3.getCPtr(v1), v1, btVector3.getCPtr(v2), v2);
  }

  public void setZero() {
    gdxBulletJNI.btVector3_setZero(swigCPtr, this);
  }

  public boolean isZero() {
    return gdxBulletJNI.btVector3_isZero(swigCPtr, this);
  }

  public boolean fuzzyZero() {
    return gdxBulletJNI.btVector3_fuzzyZero(swigCPtr, this);
  }

  public void serialize(btVector3FloatData dataOut) {
    gdxBulletJNI.btVector3_serialize(swigCPtr, this, btVector3FloatData.getCPtr(dataOut), dataOut);
  }

  public void deSerialize(btVector3FloatData dataIn) {
    gdxBulletJNI.btVector3_deSerialize(swigCPtr, this, btVector3FloatData.getCPtr(dataIn), dataIn);
  }

  public void serializeFloat(btVector3FloatData dataOut) {
    gdxBulletJNI.btVector3_serializeFloat(swigCPtr, this, btVector3FloatData.getCPtr(dataOut), dataOut);
  }

  public void deSerializeFloat(btVector3FloatData dataIn) {
    gdxBulletJNI.btVector3_deSerializeFloat(swigCPtr, this, btVector3FloatData.getCPtr(dataIn), dataIn);
  }

  public void serializeDouble(btVector3DoubleData dataOut) {
    gdxBulletJNI.btVector3_serializeDouble(swigCPtr, this, btVector3DoubleData.getCPtr(dataOut), dataOut);
  }

  public void deSerializeDouble(btVector3DoubleData dataIn) {
    gdxBulletJNI.btVector3_deSerializeDouble(swigCPtr, this, btVector3DoubleData.getCPtr(dataIn), dataIn);
  }

}

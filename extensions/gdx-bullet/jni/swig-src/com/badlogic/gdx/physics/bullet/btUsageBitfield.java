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

public class btUsageBitfield {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected btUsageBitfield(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(btUsageBitfield obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btUsageBitfield(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btUsageBitfield() {
    this(gdxBulletJNI.new_btUsageBitfield(), true);
  }

  public void reset() {
    gdxBulletJNI.btUsageBitfield_reset(swigCPtr, this);
  }

  public void setUsedVertexA(int value) {
    gdxBulletJNI.btUsageBitfield_usedVertexA_set(swigCPtr, this, value);
  }

  public int getUsedVertexA() {
    return gdxBulletJNI.btUsageBitfield_usedVertexA_get(swigCPtr, this);
  }

  public void setUsedVertexB(int value) {
    gdxBulletJNI.btUsageBitfield_usedVertexB_set(swigCPtr, this, value);
  }

  public int getUsedVertexB() {
    return gdxBulletJNI.btUsageBitfield_usedVertexB_get(swigCPtr, this);
  }

  public void setUsedVertexC(int value) {
    gdxBulletJNI.btUsageBitfield_usedVertexC_set(swigCPtr, this, value);
  }

  public int getUsedVertexC() {
    return gdxBulletJNI.btUsageBitfield_usedVertexC_get(swigCPtr, this);
  }

  public void setUsedVertexD(int value) {
    gdxBulletJNI.btUsageBitfield_usedVertexD_set(swigCPtr, this, value);
  }

  public int getUsedVertexD() {
    return gdxBulletJNI.btUsageBitfield_usedVertexD_get(swigCPtr, this);
  }

  public void setUnused1(int value) {
    gdxBulletJNI.btUsageBitfield_unused1_set(swigCPtr, this, value);
  }

  public int getUnused1() {
    return gdxBulletJNI.btUsageBitfield_unused1_get(swigCPtr, this);
  }

  public void setUnused2(int value) {
    gdxBulletJNI.btUsageBitfield_unused2_set(swigCPtr, this, value);
  }

  public int getUnused2() {
    return gdxBulletJNI.btUsageBitfield_unused2_get(swigCPtr, this);
  }

  public void setUnused3(int value) {
    gdxBulletJNI.btUsageBitfield_unused3_set(swigCPtr, this, value);
  }

  public int getUnused3() {
    return gdxBulletJNI.btUsageBitfield_unused3_get(swigCPtr, this);
  }

  public void setUnused4(int value) {
    gdxBulletJNI.btUsageBitfield_unused4_set(swigCPtr, this, value);
  }

  public int getUnused4() {
    return gdxBulletJNI.btUsageBitfield_unused4_get(swigCPtr, this);
  }

}

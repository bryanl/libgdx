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

public class btTriangleMesh extends btTriangleIndexVertexArray {
  private long swigCPtr;

  protected btTriangleMesh(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btTriangleMesh_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btTriangleMesh obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btTriangleMesh(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public void setM_weldingThreshold(float value) {
    gdxBulletJNI.btTriangleMesh_m_weldingThreshold_set(swigCPtr, this, value);
  }

  public float getM_weldingThreshold() {
    return gdxBulletJNI.btTriangleMesh_m_weldingThreshold_get(swigCPtr, this);
  }

  public btTriangleMesh(boolean use32bitIndices, boolean use4componentVertices) {
    this(gdxBulletJNI.new_btTriangleMesh__SWIG_0(use32bitIndices, use4componentVertices), true);
  }

  public btTriangleMesh(boolean use32bitIndices) {
    this(gdxBulletJNI.new_btTriangleMesh__SWIG_1(use32bitIndices), true);
  }

  public btTriangleMesh() {
    this(gdxBulletJNI.new_btTriangleMesh__SWIG_2(), true);
  }

  public boolean getUse32bitIndices() {
    return gdxBulletJNI.btTriangleMesh_getUse32bitIndices(swigCPtr, this);
  }

  public boolean getUse4componentVertices() {
    return gdxBulletJNI.btTriangleMesh_getUse4componentVertices(swigCPtr, this);
  }

  public void addTriangle(Vector3 vertex0, Vector3 vertex1, Vector3 vertex2, boolean removeDuplicateVertices) {
    gdxBulletJNI.btTriangleMesh_addTriangle__SWIG_0(swigCPtr, this, vertex0, vertex1, vertex2, removeDuplicateVertices);
  }

  public void addTriangle(Vector3 vertex0, Vector3 vertex1, Vector3 vertex2) {
    gdxBulletJNI.btTriangleMesh_addTriangle__SWIG_1(swigCPtr, this, vertex0, vertex1, vertex2);
  }

  public int getNumTriangles() {
    return gdxBulletJNI.btTriangleMesh_getNumTriangles(swigCPtr, this);
  }

  public int findOrAddVertex(Vector3 vertex, boolean removeDuplicateVertices) {
    return gdxBulletJNI.btTriangleMesh_findOrAddVertex(swigCPtr, this, vertex, removeDuplicateVertices);
  }

  public void addIndex(int index) {
    gdxBulletJNI.btTriangleMesh_addIndex(swigCPtr, this, index);
  }

}

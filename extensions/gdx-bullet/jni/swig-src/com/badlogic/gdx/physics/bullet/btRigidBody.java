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

public class btRigidBody extends btCollisionObject {
  private long swigCPtr;

  public btRigidBody(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btRigidBody_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btRigidBody obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btRigidBody(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  static {
    new SharedLibraryLoader().load("gdx-bullet");
  }

  public btRigidBody(btRigidBodyConstructionInfo constructionInfo) {
    this(gdxBulletJNI.new_btRigidBody__SWIG_0(btRigidBodyConstructionInfo.getCPtr(constructionInfo), constructionInfo), true);
  }

  public btRigidBody(float mass, btMotionState motionState, btCollisionShape collisionShape, Vector3 localInertia) {
    this(gdxBulletJNI.new_btRigidBody__SWIG_1(mass, btMotionState.getCPtr(motionState), motionState, btCollisionShape.getCPtr(collisionShape), collisionShape, localInertia), true);
  }

  public btRigidBody(float mass, btMotionState motionState, btCollisionShape collisionShape) {
    this(gdxBulletJNI.new_btRigidBody__SWIG_2(mass, btMotionState.getCPtr(motionState), motionState, btCollisionShape.getCPtr(collisionShape), collisionShape), true);
  }

  public void proceedToTransform(btTransform newTrans) {
    gdxBulletJNI.btRigidBody_proceedToTransform(swigCPtr, this, btTransform.getCPtr(newTrans), newTrans);
  }

  public static btRigidBody upcast(btCollisionObject colObj) {
    long cPtr = gdxBulletJNI.btRigidBody_upcast__SWIG_0(btCollisionObject.getCPtr(colObj), colObj);
    return (cPtr == 0) ? null : new btRigidBody(cPtr, false);
  }

  public void predictIntegratedTransform(float step, btTransform predictedTransform) {
    gdxBulletJNI.btRigidBody_predictIntegratedTransform(swigCPtr, this, step, btTransform.getCPtr(predictedTransform), predictedTransform);
  }

  public void saveKinematicState(float step) {
    gdxBulletJNI.btRigidBody_saveKinematicState(swigCPtr, this, step);
  }

  public void applyGravity() {
    gdxBulletJNI.btRigidBody_applyGravity(swigCPtr, this);
  }

  public void setGravity(Vector3 acceleration) {
    gdxBulletJNI.btRigidBody_setGravity(swigCPtr, this, acceleration);
  }

  public Vector3 getGravity() {
	return gdxBulletJNI.btRigidBody_getGravity(swigCPtr, this);
}

  public void setDamping(float lin_damping, float ang_damping) {
    gdxBulletJNI.btRigidBody_setDamping(swigCPtr, this, lin_damping, ang_damping);
  }

  public float getLinearDamping() {
    return gdxBulletJNI.btRigidBody_getLinearDamping(swigCPtr, this);
  }

  public float getAngularDamping() {
    return gdxBulletJNI.btRigidBody_getAngularDamping(swigCPtr, this);
  }

  public float getLinearSleepingThreshold() {
    return gdxBulletJNI.btRigidBody_getLinearSleepingThreshold(swigCPtr, this);
  }

  public float getAngularSleepingThreshold() {
    return gdxBulletJNI.btRigidBody_getAngularSleepingThreshold(swigCPtr, this);
  }

  public void applyDamping(float timeStep) {
    gdxBulletJNI.btRigidBody_applyDamping(swigCPtr, this, timeStep);
  }

  public btCollisionShape getCollisionShape() {
    long cPtr = gdxBulletJNI.btRigidBody_getCollisionShape__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : btCollisionShape.newDerivedObject(cPtr, false);
  }

  public void setMassProps(float mass, Vector3 inertia) {
    gdxBulletJNI.btRigidBody_setMassProps(swigCPtr, this, mass, inertia);
  }

  public Vector3 getLinearFactor() {
	return gdxBulletJNI.btRigidBody_getLinearFactor(swigCPtr, this);
}

  public void setLinearFactor(Vector3 linearFactor) {
    gdxBulletJNI.btRigidBody_setLinearFactor(swigCPtr, this, linearFactor);
  }

  public float getInvMass() {
    return gdxBulletJNI.btRigidBody_getInvMass(swigCPtr, this);
  }

  public Matrix3 getInvInertiaTensorWorld() {
	return gdxBulletJNI.btRigidBody_getInvInertiaTensorWorld(swigCPtr, this);
}

  public void integrateVelocities(float step) {
    gdxBulletJNI.btRigidBody_integrateVelocities(swigCPtr, this, step);
  }

  public void setCenterOfMassTransform(btTransform xform) {
    gdxBulletJNI.btRigidBody_setCenterOfMassTransform(swigCPtr, this, btTransform.getCPtr(xform), xform);
  }

  public void applyCentralForce(Vector3 force) {
    gdxBulletJNI.btRigidBody_applyCentralForce(swigCPtr, this, force);
  }

  public Vector3 getTotalForce() {
	return gdxBulletJNI.btRigidBody_getTotalForce(swigCPtr, this);
}

  public Vector3 getTotalTorque() {
	return gdxBulletJNI.btRigidBody_getTotalTorque(swigCPtr, this);
}

  public Vector3 getInvInertiaDiagLocal() {
	return gdxBulletJNI.btRigidBody_getInvInertiaDiagLocal(swigCPtr, this);
}

  public void setInvInertiaDiagLocal(Vector3 diagInvInertia) {
    gdxBulletJNI.btRigidBody_setInvInertiaDiagLocal(swigCPtr, this, diagInvInertia);
  }

  public void setSleepingThresholds(float linear, float angular) {
    gdxBulletJNI.btRigidBody_setSleepingThresholds(swigCPtr, this, linear, angular);
  }

  public void applyTorque(Vector3 torque) {
    gdxBulletJNI.btRigidBody_applyTorque(swigCPtr, this, torque);
  }

  public void applyForce(Vector3 force, Vector3 rel_pos) {
    gdxBulletJNI.btRigidBody_applyForce(swigCPtr, this, force, rel_pos);
  }

  public void applyCentralImpulse(Vector3 impulse) {
    gdxBulletJNI.btRigidBody_applyCentralImpulse(swigCPtr, this, impulse);
  }

  public void applyTorqueImpulse(Vector3 torque) {
    gdxBulletJNI.btRigidBody_applyTorqueImpulse(swigCPtr, this, torque);
  }

  public void applyImpulse(Vector3 impulse, Vector3 rel_pos) {
    gdxBulletJNI.btRigidBody_applyImpulse(swigCPtr, this, impulse, rel_pos);
  }

  public void clearForces() {
    gdxBulletJNI.btRigidBody_clearForces(swigCPtr, this);
  }

  public void updateInertiaTensor() {
    gdxBulletJNI.btRigidBody_updateInertiaTensor(swigCPtr, this);
  }

  public Vector3 getCenterOfMassPosition() {
	return gdxBulletJNI.btRigidBody_getCenterOfMassPosition(swigCPtr, this);
}

  public Quaternion getOrientation() {
	return gdxBulletJNI.btRigidBody_getOrientation(swigCPtr, this);
}

  public btTransform getCenterOfMassTransform() {
    return new btTransform(gdxBulletJNI.btRigidBody_getCenterOfMassTransform(swigCPtr, this), false);
  }

  public Vector3 getLinearVelocity() {
	return gdxBulletJNI.btRigidBody_getLinearVelocity(swigCPtr, this);
}

  public Vector3 getAngularVelocity() {
	return gdxBulletJNI.btRigidBody_getAngularVelocity(swigCPtr, this);
}

  public void setLinearVelocity(Vector3 lin_vel) {
    gdxBulletJNI.btRigidBody_setLinearVelocity(swigCPtr, this, lin_vel);
  }

  public void setAngularVelocity(Vector3 ang_vel) {
    gdxBulletJNI.btRigidBody_setAngularVelocity(swigCPtr, this, ang_vel);
  }

  public Vector3 getVelocityInLocalPoint(Vector3 rel_pos) {
	return gdxBulletJNI.btRigidBody_getVelocityInLocalPoint(swigCPtr, this, rel_pos);
}

  public void translate(Vector3 v) {
    gdxBulletJNI.btRigidBody_translate(swigCPtr, this, v);
  }

  public void getAabb(Vector3 aabbMin, Vector3 aabbMax) {
    gdxBulletJNI.btRigidBody_getAabb(swigCPtr, this, aabbMin, aabbMax);
  }

  public float computeImpulseDenominator(Vector3 pos, Vector3 normal) {
    return gdxBulletJNI.btRigidBody_computeImpulseDenominator(swigCPtr, this, pos, normal);
  }

  public float computeAngularImpulseDenominator(Vector3 axis) {
    return gdxBulletJNI.btRigidBody_computeAngularImpulseDenominator(swigCPtr, this, axis);
  }

  public void updateDeactivation(float timeStep) {
    gdxBulletJNI.btRigidBody_updateDeactivation(swigCPtr, this, timeStep);
  }

  public boolean wantsSleeping() {
    return gdxBulletJNI.btRigidBody_wantsSleeping(swigCPtr, this);
  }

  public btBroadphaseProxy getBroadphaseProxy() {
    long cPtr = gdxBulletJNI.btRigidBody_getBroadphaseProxy__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : new btBroadphaseProxy(cPtr, false);
  }

  public void setNewBroadphaseProxy(btBroadphaseProxy broadphaseProxy) {
    gdxBulletJNI.btRigidBody_setNewBroadphaseProxy(swigCPtr, this, btBroadphaseProxy.getCPtr(broadphaseProxy), broadphaseProxy);
  }

  public btMotionState getMotionState() {
    long cPtr = gdxBulletJNI.btRigidBody_getMotionState__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : btMotionState.newDerivedObject(cPtr, false);
  }

  public void setMotionState(btMotionState motionState) {
    gdxBulletJNI.btRigidBody_setMotionState(swigCPtr, this, btMotionState.getCPtr(motionState), motionState);
  }

  public void setM_contactSolverType(int value) {
    gdxBulletJNI.btRigidBody_m_contactSolverType_set(swigCPtr, this, value);
  }

  public int getM_contactSolverType() {
    return gdxBulletJNI.btRigidBody_m_contactSolverType_get(swigCPtr, this);
  }

  public void setM_frictionSolverType(int value) {
    gdxBulletJNI.btRigidBody_m_frictionSolverType_set(swigCPtr, this, value);
  }

  public int getM_frictionSolverType() {
    return gdxBulletJNI.btRigidBody_m_frictionSolverType_get(swigCPtr, this);
  }

  public void setAngularFactor(Vector3 angFac) {
    gdxBulletJNI.btRigidBody_setAngularFactor__SWIG_0(swigCPtr, this, angFac);
  }

  public void setAngularFactor(float angFac) {
    gdxBulletJNI.btRigidBody_setAngularFactor__SWIG_1(swigCPtr, this, angFac);
  }

  public Vector3 getAngularFactor() {
	return gdxBulletJNI.btRigidBody_getAngularFactor(swigCPtr, this);
}

  public boolean isInWorld() {
    return gdxBulletJNI.btRigidBody_isInWorld(swigCPtr, this);
  }

  public boolean checkCollideWithOverride(btCollisionObject co) {
    return gdxBulletJNI.btRigidBody_checkCollideWithOverride(swigCPtr, this, btCollisionObject.getCPtr(co), co);
  }

  public void addConstraintRef(btTypedConstraint c) {
    gdxBulletJNI.btRigidBody_addConstraintRef(swigCPtr, this, btTypedConstraint.getCPtr(c), c);
  }

  public void removeConstraintRef(btTypedConstraint c) {
    gdxBulletJNI.btRigidBody_removeConstraintRef(swigCPtr, this, btTypedConstraint.getCPtr(c), c);
  }

  public btTypedConstraint getConstraintRef(int index) {
    long cPtr = gdxBulletJNI.btRigidBody_getConstraintRef(swigCPtr, this, index);
    return (cPtr == 0) ? null : new btTypedConstraint(cPtr, false);
  }

  public int getNumConstraintRefs() {
    return gdxBulletJNI.btRigidBody_getNumConstraintRefs(swigCPtr, this);
  }

  public void setFlags(int flags) {
    gdxBulletJNI.btRigidBody_setFlags(swigCPtr, this, flags);
  }

  public int getFlags() {
    return gdxBulletJNI.btRigidBody_getFlags(swigCPtr, this);
  }

  public Vector3 getDeltaLinearVelocity() {
	return gdxBulletJNI.btRigidBody_getDeltaLinearVelocity(swigCPtr, this);
}

  public Vector3 getDeltaAngularVelocity() {
	return gdxBulletJNI.btRigidBody_getDeltaAngularVelocity(swigCPtr, this);
}

  public Vector3 getPushVelocity() {
	return gdxBulletJNI.btRigidBody_getPushVelocity(swigCPtr, this);
}

  public Vector3 getTurnVelocity() {
	return gdxBulletJNI.btRigidBody_getTurnVelocity(swigCPtr, this);
}

  public Vector3 internalGetDeltaLinearVelocity() {
	return gdxBulletJNI.btRigidBody_internalGetDeltaLinearVelocity(swigCPtr, this);
}

  public Vector3 internalGetDeltaAngularVelocity() {
	return gdxBulletJNI.btRigidBody_internalGetDeltaAngularVelocity(swigCPtr, this);
}

  public Vector3 internalGetAngularFactor() {
	return gdxBulletJNI.btRigidBody_internalGetAngularFactor(swigCPtr, this);
}

  public Vector3 internalGetInvMass() {
	return gdxBulletJNI.btRigidBody_internalGetInvMass(swigCPtr, this);
}

  public Vector3 internalGetPushVelocity() {
	return gdxBulletJNI.btRigidBody_internalGetPushVelocity(swigCPtr, this);
}

  public Vector3 internalGetTurnVelocity() {
	return gdxBulletJNI.btRigidBody_internalGetTurnVelocity(swigCPtr, this);
}

  public void internalGetVelocityInLocalPointObsolete(Vector3 rel_pos, Vector3 velocity) {
    gdxBulletJNI.btRigidBody_internalGetVelocityInLocalPointObsolete(swigCPtr, this, rel_pos, velocity);
  }

  public void internalGetAngularVelocity(Vector3 angVel) {
    gdxBulletJNI.btRigidBody_internalGetAngularVelocity(swigCPtr, this, angVel);
  }

  public void internalApplyImpulse(Vector3 linearComponent, Vector3 angularComponent, float impulseMagnitude) {
    gdxBulletJNI.btRigidBody_internalApplyImpulse(swigCPtr, this, linearComponent, angularComponent, impulseMagnitude);
  }

  public void internalApplyPushImpulse(Vector3 linearComponent, Vector3 angularComponent, float impulseMagnitude) {
    gdxBulletJNI.btRigidBody_internalApplyPushImpulse(swigCPtr, this, linearComponent, angularComponent, impulseMagnitude);
  }

  public void internalWritebackVelocity() {
    gdxBulletJNI.btRigidBody_internalWritebackVelocity__SWIG_0(swigCPtr, this);
  }

  public void internalWritebackVelocity(float timeStep) {
    gdxBulletJNI.btRigidBody_internalWritebackVelocity__SWIG_1(swigCPtr, this, timeStep);
  }

}

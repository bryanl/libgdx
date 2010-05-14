/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_badlogic_gdx_physics_box2d_Contact */

#ifndef _Included_com_badlogic_gdx_physics_box2d_Contact
#define _Included_com_badlogic_gdx_physics_box2d_Contact
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_physics_box2d_Contact
 * Method:    jniGetWorldManifold
 * Signature: (J[F)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_physics_box2d_Contact_jniGetWorldManifold
  (JNIEnv *, jobject, jlong, jfloatArray);

/*
 * Class:     com_badlogic_gdx_physics_box2d_Contact
 * Method:    jniIsTouching
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_physics_box2d_Contact_jniIsTouching
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_badlogic_gdx_physics_box2d_Contact
 * Method:    jniSetEnabled
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_badlogic_gdx_physics_box2d_Contact_jniSetEnabled
  (JNIEnv *, jobject, jlong, jboolean);

/*
 * Class:     com_badlogic_gdx_physics_box2d_Contact
 * Method:    jniIsEnabled
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_physics_box2d_Contact_jniIsEnabled
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_badlogic_gdx_physics_box2d_Contact
 * Method:    jniGetFixtureA
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_physics_box2d_Contact_jniGetFixtureA
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_badlogic_gdx_physics_box2d_Contact
 * Method:    jniGetFixtureB
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_physics_box2d_Contact_jniGetFixtureB
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif

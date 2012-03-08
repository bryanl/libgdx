/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    initFreeTypeJni
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_initFreeTypeJni
  (JNIEnv *, jclass);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    doneFreeType
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_doneFreeType
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    newMemoryFace
 * Signature: (JLjava/nio/ByteBuffer;II)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_newMemoryFace
  (JNIEnv *, jclass, jlong, jobject, jint, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    doneFace
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_doneFace
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    selectSize
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_selectSize
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    setCharSize
 * Signature: (JIIII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_setCharSize
  (JNIEnv *, jclass, jlong, jint, jint, jint, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    setPixelSizes
 * Signature: (JII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_setPixelSizes
  (JNIEnv *, jclass, jlong, jint, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    loadGlyph
 * Signature: (JII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_loadGlyph
  (JNIEnv *, jclass, jlong, jint, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    loadChar
 * Signature: (JII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_loadChar
  (JNIEnv *, jclass, jlong, jint, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    renderGlyph
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_renderGlyph
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    hasKerning
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_hasKerning
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    getKerning
 * Signature: (JIII)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_getKerning
  (JNIEnv *, jclass, jlong, jint, jint, jint);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType
 * Method:    getCharIndex
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_getCharIndex
  (JNIEnv *, jclass, jlong, jint);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
 * Method:    getRows
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Bitmap_getRows
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
 * Method:    getWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Bitmap_getWidth
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
 * Method:    getPitch
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Bitmap_getPitch
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
 * Method:    getBuffer
 * Signature: (J)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Bitmap_getBuffer
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
 * Method:    getNumGray
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Bitmap_getNumGray
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Bitmap
 * Method:    getPixelMode
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Bitmap_getPixelMode
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getFaceFlags
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getFaceFlags
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getStyleFlags
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getStyleFlags
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getNumGlyphs
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getNumGlyphs
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getAscender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getAscender
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getDescender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getDescender
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getHeight
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getMaxAdvanceWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getMaxAdvanceWidth
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getMaxAdvanceHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getMaxAdvanceHeight
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getUnderlinePosition
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getUnderlinePosition
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getUnderlineThickness
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getUnderlineThickness
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getGlyph
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getGlyph
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Face
 * Method:    getSize
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Face_getSize
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getWidth
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getHeight
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getHoriBearingX
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getHoriBearingX
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getHoriBearingY
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getHoriBearingY
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getHoriAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getHoriAdvance
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getVertBearingX
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getVertBearingX
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getVertBearingY
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getVertBearingY
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphMetrics
 * Method:    getVertAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphMetrics_getVertAdvance
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getMetrics
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getMetrics
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getLinearHoriAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getLinearHoriAdvance
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getLinearVertAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getLinearVertAdvance
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getAdvanceX
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getAdvanceX
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getAdvanceY
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getAdvanceY
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getFormat
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getFormat
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getBitmap
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getBitmap
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getBitmapLeft
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getBitmapLeft
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_GlyphSlot
 * Method:    getBitmapTop
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024GlyphSlot_getBitmapTop
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_Library */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Library
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Library
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_Pointer */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Pointer
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Pointer
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_Size */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Size
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_Size
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_Size
 * Method:    getMetrics
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024Size_getMetrics
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics */

#ifndef _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
#define _Included_com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getXppem
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getXppem
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getYppem
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getYppem
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getXscale
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getXscale
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getYscale
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getYscale
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getAscender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getAscender
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getDescender
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getDescender
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getHeight
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_badlogic_gdx_graphics_g2d_freetype_FreeType_SizeMetrics
 * Method:    getMaxAdvance
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_badlogic_gdx_graphics_g2d_freetype_FreeType_00024SizeMetrics_getMaxAdvance
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif

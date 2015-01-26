/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jmraa_I2c */

#ifndef _Included_jmraa_I2c
#define _Included_jmraa_I2c
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jmraa_I2c
 * Method:    loadI2cNative
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_jmraa_I2c_loadI2cNative
  (JNIEnv *, jobject, jint, jboolean);

/*
 * Class:     jmraa_I2c
 * Method:    destroyI2cNative
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jmraa_I2c_destroyI2cNative
  (JNIEnv *, jobject);

/*
 * Class:     jmraa_I2c
 * Method:    address
 * Signature: (B)I
 */
JNIEXPORT jint JNICALL Java_jmraa_I2c_address
  (JNIEnv *, jobject, jbyte);

/*
 * Class:     jmraa_I2c
 * Method:    readByte
 * Signature: ()B
 */
JNIEXPORT jbyte JNICALL Java_jmraa_I2c_readByte
  (JNIEnv *, jobject);

/*
 * Class:     jmraa_I2c
 * Method:    readReg
 * Signature: (B)B
 */
JNIEXPORT jbyte JNICALL Java_jmraa_I2c_readReg
  (JNIEnv *, jobject, jbyte);

/*
 * Class:     jmraa_I2c
 * Method:    readWordReg
 * Signature: (B)S
 */
JNIEXPORT jshort JNICALL Java_jmraa_I2c_readWordReg
  (JNIEnv *, jobject, jbyte);

/*
 * Class:     jmraa_I2c
 * Method:    writeByte
 * Signature: (B)I
 */
JNIEXPORT jint JNICALL Java_jmraa_I2c_writeByte
  (JNIEnv *, jobject, jbyte);

/*
 * Class:     jmraa_I2c
 * Method:    write
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_jmraa_I2c_write
  (JNIEnv *, jobject, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif

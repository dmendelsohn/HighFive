#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_I2c.h"

JNIEXPORT void JNICALL Java_jmraa_I2c_loadI2cNative (JNIEnv *env, jobject thisObj, jint bus, jboolean raw){
  mraa::I2c *inst = new mraa::I2c(bus, raw);
  setHandle(env, thisObj, inst);
}

JNIEXPORT void JNICALL Java_jmraa_I2c_destroyI2cNative (JNIEnv *env, jobject thisObj){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);
  delete inst;
}

JNIEXPORT jbyte JNICALL Java_jmraa_I2c_readByte (JNIEnv *env, jobject thisObj){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);  
  return (jbyte)(inst->readByte());
}

JNIEXPORT jbyte JNICALL Java_jmraa_I2c_readReg (JNIEnv *env, jobject thisObj, jbyte reg){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);  
  return (jbyte)(inst->readReg((uint8_t)reg));
}

JNIEXPORT jshort JNICALL Java_jmraa_I2c_readWordReg (JNIEnv *env, jobject thisObj, jbyte reg){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);  
  return (jshort)(inst->readWordReg((uint8_t)reg));
}

JNIEXPORT jint JNICALL Java_jmraa_I2c_writeByte (JNIEnv *env, jobject thisObj, jbyte value){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);  
  return (int)(inst->writeByte((uint8_t)value));
}

JNIEXPORT jint JNICALL Java_jmraa_I2c_address (JNIEnv *env, jobject thisObj, jbyte address){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);  
  return (int)(inst->address((uint8_t)address));
}

JNIEXPORT jint JNICALL Java_jmraa_I2c_write (JNIEnv *env, jobject thisObj, jbyteArray values){
  mraa::I2c *inst = getHandle<mraa::I2c>(env, thisObj);
  int length = (int)(env->GetArrayLength(values));
  uint8_t *byteArr = (uint8_t*)(env->GetByteArrayElements(values, (jboolean)false));
  mraa_result_t res = (inst->write(byteArr, length));
  //  std::cout << "i2cwrite result: " << res << "\n";
  //std::cout << "im confident this file has been recompiled";
  int ret = (int) res;
  //std::cout << "i2cwrite result int: " << ret << "\n";
  return ret;
}

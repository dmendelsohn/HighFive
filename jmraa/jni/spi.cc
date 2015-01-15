#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_Spi.h"

JNIEXPORT void JNICALL Java_jmraa_Spi_loadSpiNative (JNIEnv *env, jobject thisObj, jint bus){
  mraa::Spi *inst = new mraa::Spi(bus);
  setHandle(env, thisObj, inst);
}

JNIEXPORT jbyte JNICALL Java_jmraa_Spi_write__B (JNIEnv *env, jobject thisObj, jbyte data){
  mraa::Spi *inst = getHandle<mraa::Spi>(env, thisObj);  
  return (jbyte)(inst->write((char)data));
}

JNIEXPORT jbyteArray JNICALL Java_jmraa_Spi_write___3B (JNIEnv *env, jobject thisObj, jbyteArray data){
  mraa::Spi *inst = getHandle<mraa::Spi>(env, thisObj);
  int length = (int)(env->GetArrayLength(data));
  char *charArr = (char*)(env->GetByteArrayElements(data, (jboolean)false));
  char* response = inst->write(charArr, length);
  jbyteArray ret = env->NewByteArray(length);
  env->SetByteArrayRegion(ret,0,length,(jbyte*)response);
  return ret;
}

JNIEXPORT jint JNICALL Java_jmraa_Spi_bitPerWord (JNIEnv *env, jobject thisObj, jint bits){
  mraa::Spi *inst = getHandle<mraa::Spi>(env, thisObj);
  return (int)(inst->bitPerWord(bits));
}

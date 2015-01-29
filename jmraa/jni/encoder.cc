#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_Encoder.h"

#include "Encoder.h"

JNIEXPORT void JNICALL Java_jmraa_Encoder_loadEncoderNative (JNIEnv *env, jobject thisObj, jint pinA, jint pinB){
  Encoder *inst = new Encoder(pinA, pinB);
  setHandle(env, thisObj, inst);
}

JNIEXPORT jint JNICALL Java_jmraa_Encoder_getCountNative (JNIEnv *env, jobject thisObj){
  Encoder *inst = getHandle<Encoder>(env, thisObj);
  return (int)(inst->get_count());
}

JNIEXPORT jint JNICALL Java_jmraa_Encoder_getDerivative (JNIEnv *env, jobject thisObj){
  Encoder *inst = getHandle<Encoder>(env, thisObj);
  return (int)(inst->get_derivative());
}

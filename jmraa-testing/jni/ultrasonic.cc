#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_Ultrasonic.h"

#include "Ultrasonic.h"

JNIEXPORT void JNICALL Java_jmraa_Ultrasonic_loadUltrasonicNative (JNIEnv *env, jobject thisObj, jint echoPin){
  Ultrasonic *inst = new Ultrasonic(echoPin);
  setHandle(env, thisObj, inst);
}

JNIEXPORT jdouble JNICALL Java_jmraa_Ultrasonic_getTime (JNIEnv *env, jobject thisObj){
  Ultrasonic *inst = getHandle<Ultrasonic>(env, thisObj);
  return (jdouble)(inst->get_time());
}

#include <jni.h>
#include <iostream>

//#include <csignal>
//#include <unistd.h>
//#include "mraa.hpp

#include "jlibmraa_Gpio.h"

JNIEXPORT jlong JNICALL Java_jlibmraa_Gpio_loadGpioNative(JNIEnv *env, jclass thisClass, jint pin, jboolean owner, jboolean raw){
  printf("loadGpio\n");
  return 1;
}

JNIEXPORT jint JNICALL Java_jlibmraa_Gpio_dir (JNIEnv *env, jobject thisObj, jobject dir){
  printf("dir\n");
  return 1;
}

JNIEXPORT jint JNICALL Java_jlibmraa_Gpio_write (JNIEnv *env, jobject thisObj, jint value){
  printf("write\n");
  return 1;
}

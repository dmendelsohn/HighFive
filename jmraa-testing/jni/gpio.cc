#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_Gpio.h"

JNIEXPORT void JNICALL Java_jmraa_Gpio_loadGpioNative (JNIEnv *env, jobject thisObj, jint pin){
  mraa::Gpio *inst = new mraa::Gpio(pin);
  setHandle(env, thisObj, inst);
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_dir (JNIEnv *env, jobject thisObj, jobject dir){
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);  

  jclass enumClass = env->FindClass("jmraa/Utils$Dir");
  jmethodID getNameMethod = env->GetMethodID(enumClass, "name", "()Ljava/lang/String;");
  jstring value = (jstring)env->CallObjectMethod(dir, getNameMethod);
  std::string valueNative = env->GetStringUTFChars(value, 0);
  if (valueNative.compare("DIR_OUT") == 0) {
    return (int)(inst->dir(mraa::DIR_OUT));
  }else{
    return (int)(inst->dir(mraa::DIR_IN));
  }
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_dir (JNIEnv *env, jobject thisObj, jobject mode){
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);  

  jclass enumClass = env->FindClass("jmraa/Utils$Mode");
  jmethodID getNameMethod = env->GetMethodID(enumClass, "name", "()Ljava/lang/String;");
  jstring value = (jstring)env->CallObjectMethod(mode, getNameMethod);
  std::string valueNative = env->GetStringUTFChars(value, 0);
  if (valueNative.compare("MODE_STRONG") == 0) {
    return (int)(inst->mode(mraa::MODE_STRONG));
  }else if (valueNative.compare("MODE_PULLUP") == 0) {
    return (int)(inst->dir(mraa::MODE_PULLUP));
  } else if (valueNative.compare("MODE_PULLDOWN") == 0) {
    return (int)(inst->dir(mraa::MODE_PULLDOWN));
  } else{
    return (int)(inst->dir(mraa::MODE_HIZ));
  }
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_write (JNIEnv *env, jobject thisObj, jint value){
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);  
  return (int)(inst->write(value));
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_read (JNIEnv *env, jobject thisObj){
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);
  return (int)(inst->read());
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_getPin (JNIEnv *env, jobject thisObj){
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);
  return (int)(inst->getPin());
}

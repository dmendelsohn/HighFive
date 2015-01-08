#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_Gpio.h"

JNIEXPORT void JNICALL Java_jmraa_Gpio_loadGpioNative (JNIEnv *env, jobject thisObj, jint pin, jboolean owner, jboolean raw){
  mraa::Gpio *inst = new mraa::Gpio(pin, owner, raw);
  setHandle(env, thisObj, inst);
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_dir (JNIEnv *env, jobject thisObj, jobject dir){
  printf("dir\n");
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);  

  jclass enumClass = env->FindClass("jmraa/Utils$Dir");
  jmethodID getNameMethod = env->GetMethodID(enumClass, "name", "()Ljava/lang/String;");
  jstring value = (jstring)env->CallObjectMethod(dir, getNameMethod);
  std::string valueNative = env->GetStringUTFChars(value, 0);
  std::cout << valueNative << "\n";
  if (valueNative.compare("DIR_OUT") == 0) {
    return (int)(inst->dir(mraa::DIR_OUT));
  }else{
    return (int)(inst->dir(mraa::DIR_IN));
  }
  return -100;
}

JNIEXPORT jint JNICALL Java_jmraa_Gpio_write (JNIEnv *env, jobject thisObj, jint value){
  printf("write\n");
  mraa::Gpio *inst = getHandle<mraa::Gpio>(env, thisObj);  
  return (int)(inst->write(value));
  //return -150;
}

#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>
#include "mraa.hpp"

#include "handle.h"
#include "jmraa_Aio.h"

JNIEXPORT void JNICALL Java_jmraa_Aio_loadAioNative (JNIEnv *env, jobject thisObj, jint pin){
  mraa::Aio *inst = new mraa::Aio(pin);
  setHandle(env, thisObj, inst);
}

JNIEXPORT jint JNICALL Java_jmraa_Aio_read (JNIEnv *env, jobject thisObj){
  mraa::Aio *inst = getHandle<mraa::Aio>(env, thisObj);
  return (int)(inst->read());
}

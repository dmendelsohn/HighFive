#include <jni.h>
#include <iostream>

#include <string>

#include <csignal>
#include <unistd.h>

#include "jmraa_Utils.h"

JNIEXPORT jint JNICALL Java_jmraa_Utils_usleep (JNIEnv *env, jclass thisClass, jint us){
  return usleep(us);
}

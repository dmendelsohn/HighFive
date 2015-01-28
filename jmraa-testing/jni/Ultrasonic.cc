#include <iostream>
#include <cassert>
#include <sys/time.h>

#include "mraa.hpp"
#include "Ultrasonic.h"

using namespace std;

Ultrasonic::Ultrasonic(int echoPin){
  time = -1;
  echoIn = new mraa::Gpio(echoPin);
  assert(echoPin != NULL);
  echoIn->dir(mraa::DIR_IN);
  echoIn->isr(mraa::EDGE_BOTH, &Ultrasonic::echo_handler, (void*)this);
}

void Ultrasonic::echo_handler(void* args) {
  // Grab end time first, for accuracy
  struct timeval end;
  gettimeofday(&end, NULL);

  Ultrasonic *ultra = (Ultrasonic*)args;
  mraa::Gpio *echo = ultra->echoIn;
  static struct timeval start;
  bool rising = echo->read() == 1;
  if (rising) {
    gettimeofday(&start, NULL);
  }
  else {
    int diffSec = end.tv_sec - start.tv_sec;
    int diffUSec = end.tv_usec - start.tv_usec;
    printf("sec: %d usec: %d\n", diffSec, diffUSec);
    ultra->time = (double)diffSec + 0.000001*diffUSec;
  }
}

double Ultrasonic::get_time(){
  return time;
}

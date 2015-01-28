#include <iostream>
#include <cassert>
#include <sys/time.h>
#include <fstream>

#include "mraa.hpp"
#include "Ultrasonic.h"

using namespace std;

Ultrasonic::Ultrasonic(int echoPin){
  ofstream file;
  file.open("ultrasonic.log");
  time = -1;
  rising = false;
  echoIn = new mraa::Gpio(echoPin);
  //assert(echoIn != NULL);
  file << echoIn->dir(mraa::DIR_IN) << "\n";
  file << echoIn->isr(mraa::EDGE_BOTH, &Ultrasonic::echo_handler, (void*)this) << "\n";
  file << "made native ultra\n";
  file.close();
}

void Ultrasonic::echo_handler(void* args) {
  Ultrasonic *ultra = (Ultrasonic*)args;
  mraa::Gpio *echo = ultra->echoIn;
  ultra->rising = echo->read() == 1;
  static struct timeval start;

  ofstream file;
  file.open("echo.log", std::ofstream::out | std::ofstream::app);
  file << "echo handling\n";

  // Grab end time first, for accuracy
  struct timeval end;
  gettimeofday(&end, NULL);
  if (ultra->rising) {
    gettimeofday(&start, NULL);
  }
  else {
    int diffSec = end.tv_sec - start.tv_sec;
    int diffUSec = end.tv_usec - start.tv_usec;
    file << "diffUSec=" << diffUSec;
    ultra->time = (double)diffSec + 0.000001*diffUSec;
  }
  file.close();
}

double Ultrasonic::get_time(){
  return time;
}

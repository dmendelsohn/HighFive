#include <iostream>
#include "Ultrasonic.h"
#include "mraa.hpp"

int main() {
  mraa::Gpio* trig = new mraa::Gpio(8);
  trig->dir(mraa::DIR_OUT);
  Ultrasonic *ultra = new Ultrasonic(9);

  for(int i = 0; i < 1000; i++){
    ultra->rising = false;
    trig->write(1);
    usleep(20);
    trig->write(0);
    printf("time: %f\n", ultra->get_time());

    usleep(500000.0);
  }
}

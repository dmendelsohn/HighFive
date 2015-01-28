#include "mraa.hpp"

class Ultrasonic {
 public:
  volatile double time;
  mraa::Gpio *echoIn;
  static void echo_handler(void* args);
  Ultrasonic (int);
  double get_time();
};

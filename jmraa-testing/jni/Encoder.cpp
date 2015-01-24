#include <iostream>
#include "mraa.hpp"

using namespace std;

class Encoder {
  int count[100];
  mraa::Gpio *encA, *encB;
  volatile int aState;
  volatile int bState;
  volatile int count;
  int getPhase(int aIn, int bIn);
  void updateTick(int prevPhase, int curPhase);
  void aHandler(void* args);
  void bHandler(void* args);
public:
  Encoder (int, int);
  int get_count();
  int get_derivative();
};

Encoder::Encoder (int pinA, int pinB){
  encA = & new mraa::Gpio(pinA);
  assert(encA != NULL);
  encA->dir(mraa::DIR_IN);
  encA->isr(mraa::EDGE_BOTH, aHandler, encA);
  encB = & new mraa::Gpio(pinB);
  assert(encB != NULL);
  encB->dir(mraa::DIR_IN);
  encB->isr(mraa::EDGE_BOTH, bHandler, encB);
}  

int Encoder::get_count () {
  return count[0];
}

int Encoder::get_derivative(){
  return count[0]-count[99];
}

int getPhase(int a, int b) {
  assert(a == 0 || a == 1);
  assert(b == 0 || b == 1);
  if (a == 0 && b == 0) {
    return 0;
  }
  else if (a == 1 && b == 0) {
    return 1;
  }
  else if (a == 1 && b == 1) {
    return 2;
  }
  else if (a == 0 && b == 1) {
    return 3;
  }
  // Unreachable
  assert(false);
}

void updateTick(int prevPhase, int curPhase) {
  // Tick forward (possibly wrapping)
  if (curPhase - prevPhase == 1 ||
      curPhase - prevPhase == -3) {
    count++;
  }
  // Tick backward (possibly wrapping)
  else if (curPhase - prevPhase == -1 ||
           curPhase - prevPhase == 3) {
    count--;
  }
  else {
    std::cerr << "Weird phase change: "
              << prevPhase << " to " << curPhase << std::endl;
  }
}

void aHandler(void* args) {
  // Get the gpio handle from the args
  mraa::Gpio *encA = (mraa::Gpio*)args;
  int a = aState;
  int b = bState;
  int newA = encA->read();
  aState = newA;
  int prevPhase = getPhase(a, b);
  int curPhase = getPhase(newA, b);
  updateTick(prevPhase, curPhase);
}

void bHandler(void* args) {
  // Get the gpio handle from the args
  mraa::Gpio *encB = (mraa::Gpio*)args;
  int a = aState;
  int b = bState;
  int newB = encB->read();
  bState = newB;
  int prevPhase = getPhase(a, b);
  int curPhase = getPhase(a, newB);
  updateTick(prevPhase, curPhase);
}

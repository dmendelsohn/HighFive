#include <iostream>
#include <cassert>

#include "mraa.hpp"
#include "Encoder.h"

using namespace std;

Encoder::Encoder (int pinA, int pinB){
  aState = 0;
  bState = 0;
  /*for(int i = 0; i < 100; i++){
    count[i] = 0;
    }*/
  count = 0;
  encA = new mraa::Gpio(pinA);
  assert(encA != NULL);
  encA->dir(mraa::DIR_IN);
  encA->isr(mraa::EDGE_BOTH, &Encoder::aHandler, (void*)this);
  encB = new mraa::Gpio(pinB);
  assert(encB != NULL);
  encB->dir(mraa::DIR_IN);
  encB->isr(mraa::EDGE_BOTH, &Encoder::bHandler, (void*)this);
  printf("made native encoder object\n");
}  

int Encoder::get_count () {
  return count;
}

int Encoder::get_derivative(){
  return 0;
  //  return count[0]-count[99];
}

int Encoder::getPhase(int a, int b) {
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

void Encoder::updateTick(int prevPhase, int curPhase) {
  //printf("updating tick\n");
  //  for(int i = 99; i > 0; i++){
  //  count[i] = count[i-1];
  //}
  //  printf("shifted array\n");
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
  else if(curPhase - prevPhase != 0){
    std::cerr << "Weird phase change: "
              << prevPhase << " to " << curPhase << std::endl;
  }
}

void Encoder::aHandler(void* args) {
  Encoder *enc = (Encoder*)args;
  // Get the gpio handle from the args
  mraa::Gpio *gpioA = enc->encA;
  int a = enc->aState;
  int b = enc->bState;
  int newA = gpioA->read();
  enc->aState = newA;
  int prevPhase = Encoder::getPhase(a, b);
  int curPhase = Encoder::getPhase(newA, b);
  enc->updateTick(prevPhase, curPhase);
}

void Encoder::bHandler(void* args) {
  //printf("handling b");
  Encoder *enc = (Encoder*)args;
  // Get the gpio handle from the args
  mraa::Gpio *gpioB = enc->encB;
  int a = enc->aState;
  int b = enc->bState;
  int newB = gpioB->read();
  enc->bState = newB;
  int prevPhase = Encoder::getPhase(a, b);
  int curPhase = Encoder::getPhase(a, newB);
  enc->updateTick(prevPhase, curPhase);
}

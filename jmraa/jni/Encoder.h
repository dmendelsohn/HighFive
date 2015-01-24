#include "mraa.hpp"

class Encoder {
 public:
  //volatile int count[100];
  volatile int count;
  mraa::Gpio *encA;
  mraa::Gpio *encB;
  volatile int aState;
  volatile int bState;
  static int getPhase(int aIn, int bIn);
  void updateTick(int prevPhase, int curPhase);
  static void aHandler(void* args);
  static void bHandler(void* args);
  Encoder (int, int);
  int get_count();
  int get_derivative();
};

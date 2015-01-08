# Compile-time flags to use
CPPFLAGS = -Wall -g

# Include directories
INCLUDES = -I../include -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -I/usr/include

# Which source files to compile
SRCS = gpio.cc
OBJS = $(SRCS:.cc=.o)

.PHONY: depend clean sharedlibenv

all:	sharedlib sharedlibenv

sharedlib: $(OBJS)
	g++ -shared -o lib$(APP).so $(OBJS) -L/usr/lib -lmraa

%.o: %.cc
	g++ -fPIC -c $(CPPFLAGS) $(INCLUDES) $< -o $@

# Rebuild sources if any dependency file changes
depend: $(SRCS)
	makedepend $(INCLUDES) $^

clean:
	rm *.o *.so *~

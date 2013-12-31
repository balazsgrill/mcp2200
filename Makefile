LIBUSBXVERSION=1.0.17
INCLUDE=-Ilibusbx/libusbx-$(LIBUSBXVERSION)/libusb
ifeq ($(CC),i586-mingw32msvc-gcc)
LIBS=
CONFIGURE=
else
LIBS=-lrt -lpthread -ludev
ifeq ($(ARCH),64)
MARCH=-m64
DEPS=
else
MARCH=-m32
DEPS=libc-32bit
CONFIGURE=--target=i586-unknown-linux-gnu --host=i586-unknown-linux-gnu
endif
endif

all: bin/mcp2200cli

deps: libudev $(DEPS) libusbx-latest install-compiler

bin/mcp2200.o: bin src/mcp2200.c src/mcp2200.h
	$(CC) $(MARCH) $(INCLUDE) -c -Wall src/mcp2200.c -o bin/mcp2200.o

bin:
	mkdir bin

bin/mcp2200cli.o: bin src/mcp2200cli.c
	$(CC) $(MARCH) $(INCLUDE) -c -Wall src/mcp2200cli.c -o bin/mcp2200cli.o

bin/mcp2200cli: bin/mcp2200.o bin/mcp2200cli.o
	$(CC) $(MARCH) -o bin/mcp2200cli bin/mcp2200.o bin/mcp2200cli.o libusbx/libusbx-$(LIBUSBXVERSION)/libusb/.libs/libusb-1.0.a $(LIBS)

libudev:
	sudo apt-get install libudev-dev

libc-32bit:
	sudo apt-get install libc6-dev-i386 libudev-dev:i386 ia32-libs gcc-multilib

libusbx-latest: 
	mkdir -p libusbx
	wget "http://sourceforge.net/projects/libusbx/files/releases/$(LIBUSBXVERSION)/source/libusbx-$(LIBUSBXVERSION).tar.bz2/download" -O libusbx/libusbx.tar.bz2
	bzip2 -f -d libusbx/libusbx.tar.bz2
	cd libusbx; tar -xvf libusbx.tar
	cd libusbx/libusbx-$(LIBUSBXVERSION); ./configure $(CONFIGURE) "CC=$(CC) $(MARCH)"
	cd libusbx/libusbx-$(LIBUSBXVERSION)/libusb; make

install-compiler:
ifeq ($(CC),i586-mingw32msvc-gcc)
	sudo apt-get install gcc-mingw32
else
	echo "gcc is part of the basic installation"
endif

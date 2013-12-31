LIBUSBXVERSION=1.0.17
INCLUDE=-Ilibusbx/libusbx-$(LIBUSBXVERSION)/libusb

all: bin/mcp2200cli

deps: libudev libusbx-latest install-compiler

bin/mcp2200.o: bin src/mcp2200.c src/mcp2200.h
	$(CC) $(INCLUDE) -c -Wall src/mcp2200.c -o bin/mcp2200.o

bin:
	mkdir bin

bin/mcp2200cli.o: bin src/mcp2200cli.c
	$(CC) $(INCLUDE) -c -Wall src/mcp2200cli.c -o bin/mcp2200cli.o

bin/mcp2200cli: bin/mcp2200.o bin/mcp2200cli.o
	$(CC) -o bin/mcp2200cli bin/mcp2200.o bin/mcp2200cli.o libusbx/libusbx-$(LIBUSBXVERSION)/libusb/.libs/libusb-1.0.a -lrt -lpthread -ludev

libudev:
	sudo apt-get install libudev-dev

libusbx-latest: 
	mkdir -p libusbx
	wget "http://sourceforge.net/projects/libusbx/files/releases/$(LIBUSBXVERSION)/source/libusbx-$(LIBUSBXVERSION).tar.bz2/download" -O libusbx/libusbx.tar.bz2
	bzip2 -f -d libusbx/libusbx.tar.bz2
	cd libusbx; tar -xvf libusbx.tar
	cd libusbx/libusbx-$(LIBUSBXVERSION); ./configure
	cd libusbx/libusbx-$(LIBUSBXVERSION); make

install-compiler:
ifeq ($(CC),i586-mingw32msvc-gcc)
	sudo apt-get install gcc-mingw32
else
	echo "gcc is part of the basic installation"
endif

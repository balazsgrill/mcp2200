LIBUSBXVERSION=1.0.17

ifeq ($(CC),i586-mingw32msvc-gcc)
WIN=TRUE
INCLUDE=-Ilibusbx/include/libusbx-1.0
LIBS=
CONFIGURE=
else
WIN=FALSE
INCLUDE=-Ilibusbx/libusbx-$(LIBUSBXVERSION)/libusb
LIBS=-lrt -lpthread -ludev
ifeq ($(CC),gcc)
CONFIGURE=
else
CONFIGURE=--target=i586-unknown-linux-gnu --host=i586-unknown-linux-gnu
endif
endif

all: bin/mcp2200cli

bin/mcp2200.o: bin src/mcp2200.c src/mcp2200.h
	$(CC) $(MARCH) $(INCLUDE) -c -Wall src/mcp2200.c -o bin/mcp2200.o

bin:
	mkdir -p bin

bin/mcp2200cli.o: bin src/mcp2200cli.c
	$(CC) $(MARCH) $(INCLUDE) -c -Wall src/mcp2200cli.c -o bin/mcp2200cli.o

bin/mcp2200cli: bin/mcp2200.o bin/mcp2200cli.o
	$(CC) $(MARCH) -o bin/mcp2200cli bin/mcp2200.o bin/mcp2200cli.o bin/libusb-1.0.a $(LIBS)

libusbx-latest: bin
	mkdir -p libusbx
ifeq ($(WIN),TRUE)
	wget "http://sourceforge.net/projects/libusbx/files/releases/$(LIBUSBXVERSION)/binaries/libusbx-$(LIBUSBXVERSION)-win.7z/download" -O libusbx/libusbx.7z
	7za e libusbx/libusbx.7z
	cp libusbx/MinGW32/static/libusb-1.0.a bin/libusb-1.0.a
else
	wget "http://sourceforge.net/projects/libusbx/files/releases/$(LIBUSBXVERSION)/source/libusbx-$(LIBUSBXVERSION).tar.bz2/download" -O libusbx/libusbx.tar.bz2
	bzip2 -f -d libusbx/libusbx.tar.bz2
	cd libusbx; tar -xvf libusbx.tar
	cd libusbx/libusbx-$(LIBUSBXVERSION); ./configure $(CONFIGURE)
	cd libusbx/libusbx-$(LIBUSBXVERSION)/libusb; make
	cp libusbx/libusbx-$(LIBUSBXVERSION)/libusb/.libs/libusb-1.0.a bin/libusb-1.0.a
endif


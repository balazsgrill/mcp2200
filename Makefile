all: bin/mcp2200cli

clear:
	rm -R bin

bin/mcp2200.o: bin src/mcp2200.c src/mcp2200.h
	$(CC) -c -Wall src/mcp2200.c -o bin/mcp2200.o

bin:
	mkdir -p bin

bin/mcp2200cli.o: bin src/mcp2200cli.c
	$(CC) $(MARCH) $(INCLUDE) -c -Wall src/mcp2200cli.c -o bin/mcp2200cli.o

bin/mcp2200cli: bin/mcp2200.o bin/mcp2200cli.o
	$(CC) -o bin/mcp2200cli bin/mcp2200.o bin/mcp2200cli.o -lrt -lpthread -ludev -lusb-1.0



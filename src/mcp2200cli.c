/*
 * mcp2200cli.c
 *
 *  Created on: 2012.08.27.
 *      Author: balazs.grill
 */

#include <stdio.h>
#include <sys/types.h>

#include "mcp2200.h"

static uint8_t data[64]; 
int sendNum = 0;

static uint8_t calcCheck(uint8_t id, uint8_t data){
    uint8_t check = 0xFu;
    check -= (id + (data & 0xFu) + ((data & 0xF0u)>>4u));
    check = check & 0xFu;
    return check;
}

void com_sendMsg(uint8_t id, uint8_t value){
    uint8_t check = calcCheck(id, value);
    uint8_t h = (check << 4u) + (id & 0xFu);
    
    data[sendNum] = h;
    data[sendNum+1] = value;
    sendNum += 2;
}

int main(int argc, char** argv){

	int r = mcp2200_init();
	if (r < 0)
		return r;

	int cnt = mcp2200_list_devices(MCP2200_VENDOR_ID, MCP2200_PRODUCT_ID);
	if (cnt < 0) return cnt;

	if (cnt == 0){
		printf("No device found!");
		return 0;
	}

	if (cnt == 1){
		int address = mcp2200_get_address(0);
		printf("Opening at address 0x%x\n", address);

		int connectionID = mcp2200_connect(0);

		if (connectionID < 0){
			printf("Connection failed! Error code: %d\n", connectionID);
			return 0;
		}

		r = mcp2200_hid_configure(connectionID, 0, 0, 0, 0, 1249);
		if (r != 0){
			printf("Configure error: %d\n", r);
		}

com_sendMsg(0,5);
com_sendMsg(1,255);
com_sendMsg(2,255);

		int i;
		int j;
		int count;
		uint8_t rcv_data[32];
	

		printf("Sending..\n");
		r = mcp2200_send(connectionID, data, sendNum);
		printf("result: %d\n", r);

		for(count=0;count<20;count++){
	
		
		printf("Receive..\n");
		r = mcp2200_receive(connectionID, rcv_data, 32, &i);
		printf("result: %d %d\n", r, i);
		if ( r == 0){
			for(j = 0;j<i;j++){
				printf("%d ",rcv_data[j]);
			}
		}
		printf("\n");
		}

	}else{
		printf("Multiple devices, couldn't choose..");
	}

	mcp2200_close();
	return 0;
}

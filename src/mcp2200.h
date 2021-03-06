/*
 * mcp2200.h
 *
 *  Created on: 2012.08.27.
 *      Author: balazs.grill
 */

#ifndef MCP2200_H_
#define MCP2200_H_

#include <libusb-1.0/libusb.h>

#define MCP2200_VENDOR_ID	0x04d8
#define MCP2200_PRODUCT_ID	0x00df

#define MCP2200_INVALID_CONNECTION_ID -101
#define MCP2200_INVALID_RESPONSE -102
#define MCP2200_IO_ERROR -103

/**
 * Init the MCP2200 driver
 */
extern int mcp2200_init();

/**
 * Dispose the driver and free any resources
 */
extern void mcp2200_close();

/**
 * Enumerate all connected devices
 * Return the number of device found
 */
extern int mcp2200_list_devices(int vendorID, int productID);

/**
 * Return the address of the enumerated device
 */
extern int mcp2200_get_address(int index);

/**
 * Connect to the device with the specified index
 * Returns negative on error, the connection ID otherwise.
 */
extern int mcp2200_connect(int index);

/**
 * Release the connection with the given ID
 */
extern void mcp2200_disconnect(int connectionID);

/**
 * Set/Clear the output pins
 */
extern int mcp2200_hid_set_clear_output(int connectionID, uint8_t set_bmap, uint8_t clr_bmap);

/**
 * Configure all settings
 */
extern int mcp2200_hid_configure(int connectionID,
		uint8_t IO_bmap,
    /*   SSPNG, USBCFG,   N/A,    N/A,  RxLED,  TxLED,    N/A,    N/A */
		uint8_t config_alt_pins,
		uint8_t IO_default_pins,
    /*   RxTGL,  TxTGL,  LEDX,    N/A,    N/A,    N/A, INVERT,HW_FLOW */
    /*   LEDX=0 - blink fast(100ms), LEDX=1 - blink slow (200ms) */
		uint8_t config_alt_options,
    /* Baud rate divisor. To calculate, use the following equation: =(12000000/desiredBaudRate)-1 */
		uint16_t baudRate
		);

/**
 * Write EEPROM
 */
extern int mcp2200_hid_write_ee(int connectionID, uint8_t address, uint8_t data);

/**
 * Read EEPROM
 */
extern int mcp2200_hid_read_ee(int connectionID, uint8_t address, uint8_t *data);

/**
 * Read IO values
 */
extern int mcp2200_hid_read_io(int connectionID, uint8_t *data);

/**
 * Receive data from UART channel
 */
extern int mcp2200_receive(int connectionID, uint8_t *data, int length, int* received);

/**
 * Send data on UART channel
 */
extern int mcp2200_send(int connectionID, uint8_t *data, int length);

#endif /* MCP2200_H_ */

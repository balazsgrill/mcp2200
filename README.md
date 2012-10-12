mcp2200
=======

libusb(x)-1.0 based user-mode driver for MCP2200

What?
=====

This project aims to provide an easy-to-use and simple install-less driver for MCP2200. Basically, it is
a C library using libusb-1.0 (libusbx-1.0 on windows), providing simple API which can access the 
functions of the device. On top of that, there is a JNI library providing the same for JAVA applications.
To make it complete, Eclipse developers can make use of the Eclipse wrapper plug-in (along with the OS-specific
binaries as fragments) in RCP applications.

The structure of the repository:

* mcp2200: C library and JNI binding
* mcp2200: Java code for JNI binding, and a high-level API
* hu.mcp2200: [Eclipse](http://eclipse.org) wrapper plug-in
* hu.mcp2200.win32.x86: Plug-in fragment for Win32 platform-specific binaries
* hu.mcp2200.linux.x86: Plug-in fragment for Linux-x86 platform-specific binaries
* hu.mcp2200.ui: Eclipse application - GUI to control an MCP2200 device

Getting started:
https://gist.github.com/3754350

Why?
====

Why to write a new driver when Microchip provides an official driver?

Microchip provides a windows-only driver. On linux, the kernel identifies the 
device, but it is hard to extract exactly which one is mounted to which point under /dev.
I wanted a truly cross-platform solution which provides exactly the same interface accessing
the device on any platform. Plus it is more flexible, as a user-mode driver the end user does not
need to install a driver (s)he can simply plug-in the device and the software suddenly recognizes 
it. It is simple and lightweight.

Is it ready?
============

No, it's not. Currently, the driver provides working interface for a very small fragment of the 
MCP2200 functionality. A couple of HID commands work (for example GPIO control), others are implemented
but does not seem to work properly (EEPROM commands), and the CDC interface is not even started. To be honest,
I just need GPIO for my current project therefore it is the only feature I've spent enough time on to get it
working.



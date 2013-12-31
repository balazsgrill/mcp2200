mcp2200
=======

libusbx-1.0 based user-mode driver for MCP2200

What?
=====

This project aims to provide an easy-to-use and simple install-less driver for MCP2200. Basically, it is
a C library using libusbx-1.0 (http://libusbx.org), providing simple API which can access the 
functions of the device. 

On top of that, there is a JNI library providing the same for JAVA applications: https://github.com/balazsgrill/mcp2200-jni To make it complete, Eclipse developers can make use of the Eclipse wrapper plug-ins (along with the OS-specific binaries as fragments) in RCP applications: https://github.com/balazsgrill/mcp2200-eclipse

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

No, it's not. All of the device functions has been implemented, but it needs extended testing. You are 
encuraged to try and play with it (and provide feedback) but don't expect a production-stable quality.



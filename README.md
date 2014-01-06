mcp2200
=======

[libusbx](http://libusb.org)-based user-mode driver for [MCP2200](http://www.microchip.com/wwwproducts/Devices.aspx?dDocName=en546923)

What?
=====

This project aims to provide an easy-to-use and simple install-less driver for [MCP2200](http://www.microchip.com/wwwproducts/Devices.aspx?dDocName=en546923) an USB-to-UART serial converter produced by Microchip . Basically, it is a cross-platform C library using [libusbx](http://libusbx.org), providing simple API which can access the functions of the device. 

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

License
=======
The source code of the mcp2200 library and all content of this repository is licensed under the Eclipse Public License version 1.0 (http://www.eclipse.org/legal/epl-v10.html)

> Note #1: mcp2200 is using libusbx (http://libusbx.org), which is licensed under LGPL 2.1 (http://www.gnu.org/licenses/lgpl-2.1.html).
> According to this license a "Library that uses an LGPL Library" is not a derivative work, but the compiled/linked object code is. Any distributions in object format must apply the terms of LGPL 2.1 (see section 7.) for parts incorporating libusbx.

> Note #2: This library does NOT use or incorporate any code or other intellectual property of Microchip Technology Inc. (http://www.microchip.com/). The implementation is based entirely on the MCP2200 user documentation provided by Microchip.

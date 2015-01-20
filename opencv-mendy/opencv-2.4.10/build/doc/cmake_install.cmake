# Install script for directory: /Users/danielmendelsohn/opencv-2.4.10/doc

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/usr/local")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Release")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/opencv-logo.png")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/opencv-logo2.png")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/opencv-logo-white.png")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/opencv.ico")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/pattern.png")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/acircles_pattern.png")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE FILES "/Users/danielmendelsohn/opencv-2.4.10/doc/haartraining.htm")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE OPTIONAL FILES "/Users/danielmendelsohn/opencv-2.4.10/build/doc/opencv2refman.pdf")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE OPTIONAL FILES "/Users/danielmendelsohn/opencv-2.4.10/build/doc/opencv2manager.pdf")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE OPTIONAL FILES "/Users/danielmendelsohn/opencv-2.4.10/build/doc/opencv_user.pdf")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE OPTIONAL FILES "/Users/danielmendelsohn/opencv-2.4.10/build/doc/opencv_tutorials.pdf")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "docs")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/OpenCV/doc" TYPE FILE OPTIONAL FILES "/Users/danielmendelsohn/opencv-2.4.10/build/doc/opencv_cheatsheet.pdf")
endif()


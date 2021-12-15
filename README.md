# matlab_java_api

Matlab Contact: Paul Kelly `pjk2563@rit.edu`

## How to run
* Download the repository
* Open matlab
* Navigate to the downloaded directory
* Open the `example.m` script in Matlab
  * This script contains comments that explain what each command is doing

## Java backend
The Matlab script interacts with the server through several compiled Java objects. 
* The .java files are the source code
* The .class files are the compiled java objects
The Java objects can be editted in Matlab and compiled with the Makefile 

## The serialized object
The Matlab script is able to see the sensor data from the phone by serializing a Java class on the phone and deserializing it on the computer. A Java 'class' is a source file for a particular part of the Java program. These files must be identical in both instances. The two files that must be identical are the following:
* TestData.java
* TestDataManager.java

Java's serialization library also relies on the 'package' that these classes identify with. To make things simple, the package is essentially the folder that the file resides in. On the phone, these files are under the folder `com/example/logintest/data/sensors/`, so the directory structure was copied in this Matlab project. **If the files change on the phone, they MUST change in this project or the program will NOT work.**

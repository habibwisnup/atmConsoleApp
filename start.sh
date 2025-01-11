#!/bin/bash
# Set the source directory and output directory
SRC_DIR=src/main/java
OUT_DIR=out

# Compile the Java files
find $SRC_DIR -name "*.java" > sources.txt
javac -d $OUT_DIR @sources.txt
rm sources.txt

# Run the main class
java -cp $OUT_DIR org.habibwisnup.Main

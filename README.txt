ABOUT
-----
This is a java project written by Phlippie Bosman for COS720 as part of a virus-scanning assignment.

BUILDING
--------

The project includes sources so that it can be built on any platform. This project is easiest built with ant. To build, run:

	ant jar

This will create an executable COS720A1.jar file in ./dist. This jar can be moved wherever it is needed.
To run the jar from its directory, run:

	java -Xms 512m -jar COS720A1.jar <params>

The -Xms 512m is simply to give the program enough memory. It runs a recursive algorithm on your file structure so it needs plenty of space.

USAGE
-----

The program has two functions:
a) Convert ascii-hex text to binary data
b) Scan a file system for appearances of a pattern

a) Convert ascii-hex text to binary data:
<run-command> (c)onvert <inputfile> <outputfile>
<run-command> is the command you use to run the program, for example
	java -Xms 512m -jar COS720A1.jar 
(c)onvert: The program only tests the first character of the function selector.
<inputfile> is the path to a file containing data to be converted to binary. Valid ascii-hex data consists of pairs of ascii characters valued from 0 to f (or F). The file is valid if
	-	it exists
	-	it contains an even number of characters
	-	each character is in the range 0-9a-fA-f
<outputfile> is the path to a file to write the converted binary string to. If the input file contained the text 1f, the output file will contain a binary value of 0x1F.
The purpose of this function is to create a binary pattern for function b to search.

b) Scan a file system for appearances of a pattern
<run-command> (s)can <root> <patternfile>
(scan): The program only tests the first character of the function selector.
<root> is the root of the directory tree to scan. The scanner will search for the pattern all files in this directory and all sub-directories recursively. The path must be to a directory.
<patternfile> is the path to a file which contains a pattern to search for. The contents will be used as is.


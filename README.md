# pdf-layer-remover

A simple command line program in Java to remove layers from a pdf, by name.

## Description

Uses the Spire.PDF Library to list and remove layers from a PDF file.
Spire.PDF leaves a small watermark in the top left corner and may restrict the number of pages that can be processed.
Dependencies managed with Maven.

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* Package with maven
* Run jar and specify input and output
```
maven package
java -jar <springboot jar file path> -i <input file path> -o <output file path>
```
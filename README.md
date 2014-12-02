Java-multi-main
===============

How to provide a comfortable interface to an application with several _main_ methods.
The dispatcher holds a list of classes providing a _main_ method and a description of the functionality.
If the parameter list is inappropriate a usage hint will be printed.

Build and call without any parameter to display general usage
```
cd <project-root-dir>
mvn package
cd target
java -jar entrypoint-0.0.1-SNAPSHOT.jar
```

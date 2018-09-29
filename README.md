# internet-crawl
Crawls a set of linked pages. Solution to GE Providence Coding Challenge

To create and run the solution:

1. CD to the root project directory (wherever you cloned it)
2. Execute 'mvn package' command on the command line. This will build a jar from the project.
3. CD to the 'target' folder. The jar will be there.

To execute the JAR from the command line, do the following:

java -jar internet-crawl-0.0.1-SNAPSHOT.jar "C:\JobPrep2\internet-crawl\src\main\resources\Internet1.json" "C:\JobPrep2\internet-crawl\src\main\resources\Internet2.json"

You can pass one or both JSON files in as parameters. Separate the parameters with a space. I had the project in a folder called C:\JobPrep2. You should use your own file path as appropriate.

### Pre-requisites
You must have Maven and Java 1.8 installed on your machine

### Assumptions

I assume that unlimited scalability is not a requirement. Although the application will crawl to infinite depth as required, if your JSON file contains a very large number of page nodes, the application will probably run out of memory.

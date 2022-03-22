# Name Finder

This program extracts names from the given web page and prints them to the console using [Jsoup](https://jsoup.org/) to html parse and [Apache OpenNLP](https://opennlp.apache.org/) for natural language process

Usage:<br>
```
mvn clean package<br>
java -jar target\bim207hw2.jar {URL}
```

Example:<br>
```
mvn clean package
java -jar target\bim207hw2.jar https://opennlp.apache.org/books-tutorials-and-talks.html
```

Outputs:
```
Berlin Buzzwords
Boris Galitsky
Drew Farris
Peter Thygesen
Tom Morton
```
URL in example: [check](https://opennlp.apache.org/books-tutorials-and-talks.html)

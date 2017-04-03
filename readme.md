# Embedded Web Server Deployment

To bundle the product you will need Maven. Executing **mvn clean install**, you’ll get a 
fat jar. This jar is handy because it includes all the other dependencies and things 
like your web server inside the archive.

You can give anybody this one .jar and they can run your entire Spring application 
with no fuss: no build tool required, no setup, no web server configuration, etc: 
just **java -jar exchange-0.0.1-SNAPSHOT.jar**.
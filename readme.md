# Embedded Web Server Deployment

To bundle the product you will need Maven. Executing **mvn clean install**, you’ll get a 
fat jar. This jar is handy because it includes all the other dependencies and things 
like your web server inside the archive.

You can give anybody this one .jar and they can run your entire Spring application 
with no fuss: no build tool required, no setup, no web server configuration, etc: 
just **java -jar exchange-0.0.1-SNAPSHOT.jar**.

#Example

curl "http://localhost:8080/conversor?from_amount=1.283295&from_currency=GBP&to_currency=EUR"

```
{
  "date":"2017-04-03",
  "fromAmount":1.283295,
  "fromCurrency":"GBP",
  "toAmount":1.50515,
  "toCurrency":"EUR"
}
```

curl "http://localhost:8080/conversor?from_amount=1.50515&from_currency=EUR&to_currency=GBP"

```
{
  "date":"2017-04-03",
  "fromAmount":1.50515,
  "fromCurrency":"EUR",
  "toAmount":1.283290890,
  "toCurrency":"GBP"
}
```



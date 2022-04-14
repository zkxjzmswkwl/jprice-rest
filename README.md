# Java-REST-Client
A Java HTTP/REST library created for my own educational purposes.

##### Using the jar build:

`java -jar jprice-rest_build_{version}.jar <method> <url> $<data>`

##### Example GET request with the API

```java
	Response res = new Request(url) {
		@Override
		public String getContentType() {
			return "application/json";
		}
	}.get();
```
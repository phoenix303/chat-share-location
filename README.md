# chat-share-location
Chat application where user can share location with others in the chat room.

It is a console application where multiple users at the same time can hangout.

You must add the below snippet in maven pom.xml

```xml
  <dependencies>
    <dependency>
      <groupId>com.maxmind.geoip</groupId>
      <artifactId>geoip-api</artifactId>
      <version>1.2.14</version>
    </dependency>
  </dependencies>
````
### ChatServer.java
In order to use share location feature call ChatServer class.

````java
	ChatServer chatServer =new ChatServer(filePath);
	chatServer.getServer(portNumber);
````
where portNumber is the port on which server connection will listen for the client connection and filePath is the location of the GeoLiteCity.dat (you can also download latest GeoLiteCity.dat from http://dev.maxmind.com/geoip/legacy/geolite/).

If you want to restrict location sharing feature then instantiate ChatServer class this way

````java
	ChatServer chatServer =new ChatServer();
	chatServer.getServer(portNumber);
````
ChatClient.java

To instantiate ChatClient class 

````java
	ChatClient chatClient=new ChatClient();
	chatClient.getClient( host, portNumber);
````
where host is the server IP address and portNumber must be same as passed in getServer(portNumber).


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
In order to use share location feature call ChatServer class.

````java
    ChatServer ch =new ChatServer();
		ch.getServer(portNumber);
````

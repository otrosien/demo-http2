[ ![Build Status](https://travis-ci.org/otrosien/demo-http2.svg)](https://travis-ci.org/otrosien/demo-http2)

# HTTP2 w/ Spring Boot + Jetty

* Based on [HTTP2 w/ Spring Boot + Undertow](https://github.com/making/demo-http2)
* Jetty registration based on [blog post by Jeroen van Wilgenburg](https://vanwilgenburg.wordpress.com/2016/04/01/spring-boot-http2/).
* Replaced pure alpn-boot mechanism with [Jetty ALPN Agent](https://github.com/jetty-project/jetty-alpn-agent) wrapper, that loads the correct ALPN Boot library, depending on your JRE.

## Running directly from gradle

    $ ./gradlew run

## Or manually creating and running uber-jar

    $ ./gradlew build
    $ java -javaagent:lib/jetty-alpn-agent-2.0.4.jar -jar build/libs/demo-0.0.1-SNAPSHOT.jar

On the command-line it should finalize startup with these log lines.

```
...
o.e.jetty.server.AbstractConnector       : Started ServerConnector@193dcfbe{SSL,[ssl, alpn, h2, h2-17, h2-16, h2-15, h2-14]}{0.0.0.0:8443}
.s.b.c.e.j.JettyEmbeddedServletContainer : Jetty started on port(s) 8443 (ssl, alpn, h2, h2-17, h2-16, h2-15, h2-14)
demo.DemoHttp2Application                : Started DemoHttp2Application in 2.914 seconds (JVM running for 3.274)
```

Go [https://localhost:8443](https://localhost:8443)

```
$ curl --http2 -k -v https://localhost:8443

* Rebuilt URL to: https://localhost:8443/
*   Trying ::1...
* Connected to localhost (::1) port 8443 (#0)
* ALPN, offering h2
* ALPN, offering http/1.1
* Cipher selection: ALL:!EXPORT:!EXPORT40:!EXPORT56:!aNULL:!LOW:!RC4:@STRENGTH
* successfully set certificate verify locations:
*   CAfile: /etc/pki/tls/certs/ca-bundle.crt
  CApath: none
* TLSv1.2 (OUT), TLS handshake, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Server hello (2):
* TLSv1.2 (IN), TLS handshake, Certificate (11):
* TLSv1.2 (IN), TLS handshake, Server key exchange (12):
* TLSv1.2 (IN), TLS handshake, Server finished (14):
* TLSv1.2 (OUT), TLS handshake, Client key exchange (16):
* TLSv1.2 (OUT), TLS change cipher, Client hello (1):
* TLSv1.2 (OUT), TLS handshake, Finished (20):
* TLSv1.2 (IN), TLS change cipher, Client hello (1):
* TLSv1.2 (IN), TLS handshake, Finished (20):
* SSL connection using TLSv1.2 / ECDHE-RSA-AES128-GCM-SHA256
* ALPN, server accepted to use h2
* Server certificate:
*  subject: C=Unknown; ST=Unknown; L=Unknown; O=Unknown; OU=Unknown; CN=Unknown
*  start date: Apr  4 21:10:18 2016 GMT
*  expire date: Jul  3 21:10:18 2016 GMT
*  issuer: C=Unknown; ST=Unknown; L=Unknown; O=Unknown; OU=Unknown; CN=Unknown
*  SSL certificate verify result: self signed certificate (18), continuing anyway.
* Using HTTP2, server supports multi-use
* Connection state changed (HTTP/2 confirmed)
* TCP_NODELAY set
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=0
* Using Stream ID: 1 (easy handle 0xb0c3c0)
> GET / HTTP/1.1
> Host: localhost:8443
> User-Agent: curl/7.49.1
> Accept: */*
> 
* Connection state changed (MAX_CONCURRENT_STREAMS updated)!
* HTTP 1.0, assume close after body
< HTTP/2 200 
< server: Jetty(9.3.10.v20160621)
< date: Thu, 23 Jun 2016 09:40:04 GMT
< content-type: text/plain;charset=utf-8
< content-length: 6
< 
* Closing connection 0
* TLSv1.2 (OUT), TLS alert, Client hello (1):
```

## Reference

* http://undertow.io/blog/2015/03/26/HTTP2-In-Wildfly.html
* https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-undertow-ssl
* https://vanwilgenburg.wordpress.com/2016/04/01/spring-boot-http2/
* https://github.com/jetty-project/jetty-alpn-agent

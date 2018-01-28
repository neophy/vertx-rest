# vertx-rest

This repository explains how to build a vertex app. from scratch. 
Two verticles are defined: 
The Main-Verticle named "Server" deploys the other ChargeServiceController verticle. 

This ChargeServiceController has routes defined to take and handle the http requests. The handler will be moved 
to another verticle in the upcoming commits and both verticles can then talk to each other using event bus. 

This verticle also explains how to make REST api calls which includes GET calls with path params. 

<b>WebClient<b>
The ChargeServiceController makes an http call to an external service to fetch data. It then decodes the AsyncResponse to JSON.


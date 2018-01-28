package io.vertx.cnd.service.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

/**
 * @author Neophy
 */
public class ChargeServiceController extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/getOrder/:orderId").handler(routingContext -> {

            String orderId = routingContext.pathParams().get("orderId");
            HttpServerResponse serverResponse = routingContext.response();
            serverResponse.putHeader("content-type", "text/plain");
            // Sleep of 200 ms to static mimic the oms response time
//            vertx.setTimer(200,id->
//                    serverResponse.end("SERVER timer")
//            );


            WebClient webClient = WebClient.create(vertx);
            webClient.get("oms.scmqa.myntra.com", "/myntra-oms-service/oms/order/" + orderId)
                    .putHeader("Authorization", "Basic YTph")
                    .putHeader("Accept", "application/json")
                    .as(BodyCodec.jsonObject())
                    .send(ar -> {
                        if (ar.succeeded()) {
                            // Obtain response
                            HttpResponse<JsonObject> response = ar.result();
                            serverResponse.end("SERVER : " + response.body().encodePrettily());
                        } else {
                            serverResponse.end("ERROR FROM OMS: " + ar.cause().fillInStackTrace());
                        }
                    });
        });

        server.requestHandler(router::accept).listen(8080);
    }


}

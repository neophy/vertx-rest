package io.vertx.cnd.service.verticle;

import com.myntra.oms.client.response.OrderResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

/**
 * Created by 16544 on 27/01/18.
 */
public class ChargeServiceController extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/getOrder/:orderId").handler(routingContext -> {

            String orderId = routingContext.pathParams().get("orderId");

            // This handler will be called for every request
            HttpServerResponse serverResponse = routingContext.response();
            serverResponse.putHeader("content-type", "text/plain");

            WebClient webClient = WebClient.create(vertx);
            webClient.get(8080, "oms.scmqa.myntra.com", "/myntra-oms-service/oms/order/" + orderId)
                    .as(BodyCodec.json(OrderResponse.class))
                    .send(ar -> {
                        if (ar.succeeded()) {
                            // Obtain response
                            HttpResponse<OrderResponse> response = ar.result();

                            System.out.println("Received response with status code" + response.statusCode());
                            serverResponse.end("SUCCESS FROM OMS: " + response.statusMessage());
                        } else {
                            System.out.println("Something went wrong " + ar.cause().getMessage());
                            serverResponse.end("ERROR FROM OMS");

                        }
                    });

            // Write to the response and end it
//            serverResponse.end("Hello Neoo from Http method example \n" + response.toString());
        });

        server.requestHandler(router::accept).listen(8080);
    }


}

package io.vertx.cnd.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Created by 16544 on 27/01/18.
 */
public class ChargeServiceController extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/getOrder/:orderId").handler(routingContext -> {

            Long orderId = Long.valueOf(routingContext.pathParams().get("orderId"));
            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello Neoo from Http method example " + orderId);
        });

        server.requestHandler(router::accept).listen(8080);
    }


}

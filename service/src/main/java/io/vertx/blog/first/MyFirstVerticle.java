package io.vertx.blog.first;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @author Neophy
 */
public class MyFirstVerticle extends AbstractVerticle {

    /**
     * This method is called when the verticle is deployed. It creates a HTTP server and registers a simple request
     * handler.
     * <p/>
     * Notice the `listen` method. It passes a lambda checking the port binding result. When the HTTP server has been
     * bound on the port, it call the `complete` method to inform that the starting has completed. Else it reports the
     * error.
     *
     * @param fut the future
     */

    // HTTP SERVER USING VERTEX-CORE
//    @Override
//    public void start(Future<Void> fut) {
//        vertx
//                .createHttpServer()
//                .requestHandler(r -> {
//                    r.response().end("<h1>Hello from my first " +
//                            "Vert.x 3 application</h1>");
//                })
//                .listen(
//                        // Retrieve the port from the configuration,
//                        // default to 8080.
//                        config().getInteger("http.port", 8888),
//                        result -> {
//                            if (result.succeeded()) {
//                                fut.complete();
//                            } else {
//                                fut.fail(result.cause());
//                            }
//                        }
//                );
//    }

    // HTTP SERVER USING VERTEX-WEB
    @Override
    public void start(Future<Void> fut) {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/getOrder").handler(routingContext -> {

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello Neoo from Http method example");
        });

        server.requestHandler(router::accept).listen(8080);
    }

}
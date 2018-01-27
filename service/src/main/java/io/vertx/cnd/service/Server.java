package io.vertx.cnd.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/** Server verticle gets deployed at the time of startup. It is configured as Main-Verticle in service pom.
 *  @author Neophy
 */
public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
    }
}


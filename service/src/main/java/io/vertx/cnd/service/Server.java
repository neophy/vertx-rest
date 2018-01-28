package io.vertx.cnd.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;

/** Server verticle gets deployed at the time of startup. It is configured as Main-Verticle in service pom.
 *  @author Neophy
 */
public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        // As MAC is 8 core and so running 8 instances of verticle
        DeploymentOptions options = new DeploymentOptions().setInstances(8);
        vertx.deployVerticle("io.vertx.cnd.service.verticle.ChargeServiceController", options);
    }
}


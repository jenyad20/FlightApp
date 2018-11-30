package com.test.flight.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by jenya on 11/30/18.
 */
public class MainVerticle  extends AbstractVerticle{

    private static final Logger log = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start(Future<Void> fut) throws Exception {
        vertx.deployVerticle(DataVerticle.class.getName(), new DeploymentOptions().setWorker(true), res->{
            if (!res.succeeded()) {

                fut.fail(res.cause());
            } else {
                vertx.deployVerticle(WebServer.class.getName(), wsResult->{
                    if(!wsResult.succeeded()){
                        fut.fail(wsResult.cause());
                    }
                });
            }
        });


    }

    @Override
    public void stop(Future<Void> fut) throws Exception {

    }
}

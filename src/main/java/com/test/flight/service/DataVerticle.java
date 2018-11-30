package com.test.flight.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.parsetools.JsonParser;

import java.util.concurrent.atomic.AtomicInteger;

import static com.test.flight.config.Maps.SOURCE_DATA;

/**
 * Created by jenya on 11/30/18.
 */
public class DataVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(DataVerticle.class);


    @Override
    public void start(Future<Void> fut) throws Exception {
        vertx.fileSystem().readFile("datasource.json", ar -> {
            if (ar.succeeded()) {
                JsonObject data = ar.result().toJsonObject();
                log.info("prepared data: " + data.encodePrettily());
                vertx.sharedData().getLocalMap(SOURCE_DATA).putAll(data.getMap());

            } else {
                fut.fail(ar.cause());
            }
        });
    }


    @Override
    public void stop() throws Exception {

    }
}

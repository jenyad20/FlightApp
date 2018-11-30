package com.test.flight.service;

import com.test.flight.handlers.BaggageValidatorHandler;
import com.test.flight.handlers.CouponHandler;
import com.test.flight.handlers.TicketIdValidatorHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

/**
 * Created by jenya on 11/30/18.
 */
public class WebServer extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(WebServer.class);

    @Override
    public void start(Future<Void> fut) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        router.get("/ticket/validate/:id").handler(new TicketIdValidatorHandler());
        router.post("/baggage/validateCheckin").consumes("application/json").handler(new BaggageValidatorHandler());
        router.post("/coupon/check").consumes("application/json").handler(new CouponHandler());

        vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                                log.info("webserver deployed");
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }

}

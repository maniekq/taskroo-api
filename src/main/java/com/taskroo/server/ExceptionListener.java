package com.taskroo.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

public class ExceptionListener implements ApplicationEventListener {

    @Override
    public void onEvent(ApplicationEvent event) {

    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return new ExceptionRequestEventListener();
    }

    public static class ExceptionRequestEventListener implements RequestEventListener{
        private final Logger logger;

        public ExceptionRequestEventListener(){
            logger = LogManager.getLogger(getClass());
        }

        @Override
        public void onEvent(RequestEvent event) {
            switch (event.getType()){
                case ON_EXCEPTION:
                    Throwable t = event.getException();
                    logger.error("Found exception for requestType: "+event.getType(), t);
            }
        }
    }
}
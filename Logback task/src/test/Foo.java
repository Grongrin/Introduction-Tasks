package test;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Foo {
    static final Logger logger = LoggerFactory.getLogger(Foo.class);
    final static Marker fatal = MarkerFactory.getMarker("FATAL");

    public void countDown(int t) throws InterruptedException {
        logger.debug("Countdown started");
        logger.error("Someone started the countdown! Quick, stop it!");
        for(int i = t; i>=0; i--){
            TimeUnit.SECONDS.sleep(1);
            if(i==0){
                logger.error(fatal,"Zero reached!");
            }else if(i<4){
                logger.warn("Hurry up! Only {} seconds left!", i);
            }
        }
        logger.debug("Countdown finished");
    }
}
package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


public class MyApp1 {

    final static Logger logger = LoggerFactory.getLogger(MyApp1.class);


    public static void main(String[] args) {

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        logger.info("Entering application.");

        Foo foo = new Foo();
        try{
            foo.countDown(5);
        }catch(InterruptedException e){}

        logger.info("Exiting application.");

    }
}


import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender


import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

def byDay = timestamp("yyyyMMdd")
appender("INFO_TO_FILE", FileAppender) {
    file = "myApp-INFO-${byDay}.log"
    filter(LevelFilter) {
        level = ERROR
        onMatch = DENY
    }
    filter(LevelFilter) {
        level = WARN
        onMatch = DENY
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%d{YYYY:MM:dd HH:mm:ss} [%level] [%thread] %logger{36} [%file:%line] %msg%n"
    }
}
appender("WARN_TO_FILE", FileAppender) {
    file = "myApp-WARN-${byDay}.log"
    filter(LevelFilter) {
        level = WARN
        onMatch = ACCEPT
        onMismatch = DENY
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%d{YYYY:MM:dd HH:mm:ss} [%level] [%thread] %logger{36} [%file:%line] %msg%n"
    }
}
appender("ERROR_TO_FILE", FileAppender) {
    file = "myApp-ERROR-${byDay}.log"
    filter(LevelFilter) {
        level = ERROR
        onMatch = ACCEPT
        onMismatch = DENY
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%d{YYYY:MM:dd HH:mm:ss} [%marker%level] [%thread] %logger{36} [%file:%line] %msg%n"
    }
}
appender("FILE", FileAppender) {
    file = "myApp-${byDay}.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{YYYY:MM:dd HH:mm:ss} [%level] [%thread] %logger{36} [%file:%line] %msg%n"
    }
}
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
    }
}

logger("test.Foo",
        DEBUG)
logger("test",
        INFO,
        ["INFO_TO_FILE", "WARN_TO_FILE", "ERROR_TO_FILE"])


root(DEBUG, ["STDOUT"])
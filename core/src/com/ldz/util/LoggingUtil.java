package com.ldz.util;

import com.badlogic.gdx.Gdx;

import java.time.LocalDateTime;

/**
 * Created by Loic on 05/09/2017.
 */
public class LoggingUtil {

    static final String SEPARATOR = "************";

    public static void DEBUG(String tag, String messageBefore, String messageAfter) {
        Gdx.app.debug(tag, LocalDateTime.now().toString() + " " + messageBefore + " " + SEPARATOR + " " + messageAfter);
    }

    public static void DEBUG(String tag, String messageBefore, String messageAfter, Throwable error) {
        DEBUG(tag, messageBefore, messageAfter);
        Gdx.app.error(tag, error.getMessage(), error);
    }

}

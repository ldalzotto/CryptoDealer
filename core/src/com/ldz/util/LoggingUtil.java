package com.ldz.util;

import com.badlogic.gdx.Gdx;

import java.time.LocalDateTime;

/**
 * <p>Cette classe est designée pour être utiliée en tant que logger de l'application.</p>
 * Exemple de log : <br/>
 * <li>ClassName: YYYY-MM-JJThh:mm:ss.SSS "log message 1" {@link #SEPARATOR} "log message 2" </li>
 */
public class LoggingUtil {

    /**
     * Le séparateur des logs affichés. Ce séprateur est utilisé pour séparer le nom de la classe et le message voulu.<br/>
     */
    static final String SEPARATOR = "************";

    /**
     * Message de debug simple sans utilisation du {@link #SEPARATOR} <br/>
     * Exemple de log : <br/>
     * <li>TAG: YYYY-MM-JJThh:mm:ss.SSS "log message 1" </li>
     *
     * @param tag           le TAG du log
     * @param messageBefore le "log message 1" du log
     */
    public static void DEBUG(String tag, String messageBefore) {
        Gdx.app.debug(tag, LocalDateTime.now().toString() + " " + messageBefore);
    }

    /**
     * Message de debug dimple avec l'utilisation du {@link #SEPARATOR} <br/>
     * Exemple de log : <br/>
     * <li>TAG: YYYY-MM-JJThh:mm:ss.SSS "log message 1" {@link #SEPARATOR} "log message 2"</li>
     *
     * @param tag           le TAG du log
     * @param messageBefore le "log message 1" du log
     * @param messageAfter  le "log message 2" du log
     */
    public static void DEBUG(String tag, String messageBefore, String messageAfter) {
        Gdx.app.debug(tag, LocalDateTime.now().toString() + " " + messageBefore + " " + SEPARATOR + " " + messageAfter);
    }

    /**
     * Message de debug simple loguant une erreur. Avant de logger l'erreur, un message de debug est écrit {@link #DEBUG(String, String)}<br/>
     * exemple de log d'erreur : <br/>
     * <li>TAG: "message d'erreur" "stacktrace d'erreur"</li>
     *
     * @param tag           le TAG du log
     * @param messageBefore le "log d'erreur 1" du log informatif avant le log de l'erreur
     * @param error         la "stacktrace d'erreur" du log
     */
    public static void DEBUG(String tag, String messageBefore, Throwable error) {
        DEBUG(tag, messageBefore);
        Gdx.app.error(tag, error.getMessage(), error);
    }

    /**
     * Message de debug simple loguant une erreur. Avant de logger l'erreur, un message de debug est écrit {@link #DEBUG(String, String, String)}.<br/>
     * Le Message utilise le {@link #SEPARATOR} <br/>
     * exemple de log d'erreur : <br/>
     * <li>TAG: "log message 1" "stacktrace d'erreur"</li>
     *
     * @param tag           le TAG du log
     * @param messageBefore le message informatif avant le log d'erreur
     * @param messageAfter  le message informatif avant le log d'erreur
     * @param error         l'erreur loguée
     */
    public static void DEBUG(String tag, String messageBefore, String messageAfter, Throwable error) {
        DEBUG(tag, messageBefore, messageAfter);
        Gdx.app.error(tag, error.getMessage(), error);
    }

}

package com.ldz.common;

import com.ldz.exception.ArgValidationException;

import java.util.*;

/**
 * Created by Loic on 06/09/2017.
 */
public class ArgsVerificatorUtil {

    public static final String ARG_TEMPLATE_MINUS = "--";
    public static final String ARG_TEMPLATE_NAME = "{1}";
    public static final String ARG_TEMPLATE_EQUALS = "=";
    public static final String ARG_TEMPLATE_VALUE = "{2}";
    public static final String ARG_TEMPLATE = ARG_TEMPLATE_MINUS + ARG_TEMPLATE_NAME + ARG_TEMPLATE_EQUALS + ARG_TEMPLATE_VALUE;

    public static Map<String, String> extractScriptArgs(String... args) {
        Map<String, String> argsMap = new HashMap<>();
        List<RuntimeException> exceptions = new ArrayList<>();

        for (String arg :
                args) {
            if (!validateArgFormat(arg)) {
                exceptions.add(new ArgValidationException("Args input : " + arg + " doesn't respect the following format : " + ARG_TEMPLATE));
            } else {
                Map.Entry<String, String> entry = extractArg(arg);
                argsMap.put(entry.getKey().replace(ARG_TEMPLATE_MINUS, ""), entry.getValue());
            }
        }

        if (exceptions.size() != 0) {
            for (Exception exception :
                    exceptions) {
                System.out.println(exception);
            }
            throw exceptions.get(0);
        }

        return argsMap;
    }

    private static boolean validateArgFormat(String arg) {

        if (!arg.contains(ARG_TEMPLATE_MINUS)) {
            return false;
        }
        arg.replaceAll(ARG_TEMPLATE_MINUS, "");
        String[] splittedArg = arg.split(ARG_TEMPLATE_EQUALS);

        if (splittedArg.length != 2) {
            return false;
        }


        return true;
    }

    private static Map.Entry<String, String> extractArg(String arg) {
        arg.replaceAll(ARG_TEMPLATE_MINUS, "");
        String[] splittedArg = arg.split(ARG_TEMPLATE_EQUALS);

        return new AbstractMap.SimpleEntry<String, String>(splittedArg[0], splittedArg[1]);
    }


}

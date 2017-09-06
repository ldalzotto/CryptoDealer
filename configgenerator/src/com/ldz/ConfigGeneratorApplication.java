package com.ldz;

import com.badlogic.gdx.utils.Json;
import com.ldz.common.ArgsVerificatorUtil;

import java.util.Map;

/**
 * Created by Loic on 06/09/2017.
 */
public class ConfigGeneratorApplication {

    private static String OUTPUT_JSON_FILE_ARG = "outputJson";
    private Json json = new Json();

    public static void main(String[] argc) {

        Map<String, String> args = ArgsVerificatorUtil.extractScriptArgs(argc);

        String outputJsonFileName = args.get(OUTPUT_JSON_FILE_ARG);

        if (outputJsonFileName != null) {
            
        }

    }

}

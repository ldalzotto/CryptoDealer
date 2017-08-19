package com.ldz.config.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.config.input.domain.InputProcessors;

/**
 * Created by Loic on 07/08/2017.
 */
public class InputMultiplexerConfig {

    private static InputMultiplexerConfig instance = null;

    public static InputMultiplexerConfig getInstance(){
        if(instance == null){
            instance = new InputMultiplexerConfig();
        }
        return instance;
    }

    private Json json = new Json();
    private InputProcessors inputProcessors;


    private InputMultiplexerConfig(){
        inputProcessors = json.fromJson(InputProcessors.class, Gdx.files.internal("config/InputProcessorPriority.json"));
    }

    public InputProcessors getInputProcessors() {
        return inputProcessors;
    }
}

package com.ldz.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.ldz.config.input.InputMultiplexerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 07/08/2017.
 */
public class MyInputMultiplexer extends InputMultiplexer {

    private static MyInputMultiplexer instance = null;

    public static MyInputMultiplexer getInstance(){
        if (instance == null){
            instance = new MyInputMultiplexer();
            Gdx.input.setInputProcessor(instance);
        }
        return instance;
    }

    @Override
    public void addProcessor(InputProcessor processor) {
        super.addProcessor(processor);
        setProcessorWithPriority();
    }

    @Override
    public void removeProcessor(InputProcessor processor) {
        super.removeProcessor(processor);
        setProcessorWithPriority();
    }

    private void setProcessorWithPriority(){

        List<InputProcessor> inputProcessors = new ArrayList<>();

        for (InputProcessor inputProcessor :
                this.getProcessors()) {
            inputProcessors.add(inputProcessor);
        }

        //remove all processor
        this.clear();

        InputMultiplexerConfig.getInstance().getInputProcessors().getInputprocessors().forEach(inputProcessorDetail -> {
            inputProcessors.forEach(inputProcessor -> {
                if(inputProcessor.getClass().getSimpleName().equals(inputProcessorDetail.getName())){
                    super.addProcessor(inputProcessor);
                }
            });
        });
    }

}

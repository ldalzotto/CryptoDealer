package com.ldz.config.childhierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.config.childhierarchy.domain.ChildEntities;
import com.ldz.config.childhierarchy.domain.ChildEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Loic on 20/08/2017.
 */
public class ChildEntitiesConfig {

    private static ChildEntitiesConfig instance = null;

    public static ChildEntitiesConfig getInstance(){
        if(instance == null){
            instance = new ChildEntitiesConfig();
        }
        return instance;
    }


    private Json json = new Json();
    private ChildEntities childEntities;

    public ChildEntitiesConfig() {
        this.childEntities = json.fromJson(ChildEntities.class, Gdx.files.internal("config/ChildHierarchy.json"));
    }

    public ChildEntities getChildEntities() {
        return childEntities;
    }

    public void iterateThroughChildRecursively(BiFunction<String, List<String>, Void> stringListVoidBiFunction, List<ChildEntity> childEntities){
        for (ChildEntity childEntity:
                childEntities) {

                //get the list of string
                List<String> classNames = new ArrayList<>();
                if(childEntity.getChilds() != null){
                    for (ChildEntity childEntity1:
                         childEntity.getChilds()) {
                        classNames.add(childEntity1.getClassname());
                    }
                }

                stringListVoidBiFunction.apply(childEntity.getClassname(), classNames);

            if(childEntity.getChilds() != null){
                this.iterateThroughChildRecursively(stringListVoidBiFunction, childEntity.getChilds());
            }

        }

    }
}

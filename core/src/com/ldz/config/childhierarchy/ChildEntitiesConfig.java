package com.ldz.config.childhierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.config.childhierarchy.domain.ChildEntities;

/**
 * Created by Loic on 20/08/2017.
 */
public class ChildEntitiesConfig {

    private static ChildEntitiesConfig instance = null;
    private Json json = new Json();
    private ChildEntities childEntities;

    public ChildEntitiesConfig() {
        this.childEntities = json.fromJson(ChildEntities.class, Gdx.files.internal("config/ChildHierarchy.json"));
    }

    public static ChildEntitiesConfig getInstance() {
        if (instance == null) {
            instance = new ChildEntitiesConfig();
        }
        return instance;
    }

    public ChildEntities getChildEntities() {
        return childEntities;
    }

}

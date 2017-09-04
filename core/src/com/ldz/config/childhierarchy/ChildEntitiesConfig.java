package com.ldz.config.childhierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.config.childhierarchy.domain.ChildEntities;
import com.ldz.config.childhierarchy.domain.ChildEntity;
import com.ldz.config.game.entities.EntityId;
import com.ldz.util.ParentAndChildUtil;

import java.util.ArrayList;
import java.util.List;

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

    public void applyLinkingThroughChildRecursively(List<ChildEntity> childEntities) {
        for (ChildEntity childEntity :
                childEntities) {

            //get the list of string
            List<EntityId> classNames = new ArrayList<>();
            if (childEntity.getChilds() != null) {
                for (ChildEntity childEntity1 :
                        childEntity.getChilds()) {
                    classNames.add(childEntity1.getId());
                }
            }

            ParentAndChildUtil.linkParentAndChildOfEntity(childEntity.getId(), classNames);

            if (childEntity.getChilds() != null) {
                this.applyLinkingThroughChildRecursively(childEntity.getChilds());
            }

        }

    }
}

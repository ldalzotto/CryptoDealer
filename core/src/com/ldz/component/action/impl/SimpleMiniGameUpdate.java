package com.ldz.component.action.impl;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.SimpleMiniGameComponent;
import com.ldz.component.action.IMiniGameUpdate;
import com.ldz.config.game.entities.EntityId;
import com.ldz.engine.MyEngine;
import com.ldz.entity.EntityFactory;
import com.ldz.entity.EntityWithId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 17/09/2017.
 */
public class SimpleMiniGameUpdate implements IMiniGameUpdate {

    private Rectangle gameArea;
    private List<Entity> entityToAddToEngine = new ArrayList<>();

    @Override
    public void init(Rectangle gameArea) {
        this.gameArea = gameArea;

        EntityWithId entityWithId = EntityFactory.createInstanceFromEntityId(EntityId.simple_mini_game_target, new Vector2(this.gameArea.x, this.gameArea.y),
                new Texture(Gdx.files.internal("badlogic.jpg")), 7, new Vector2(0, 10));
        this.entityToAddToEngine.add(entityWithId);

    }

    @Override
    public int update(float delta) {

        Engine engine = MyEngine.getInstance(null, null);

        //get all entities associated to this minigame.
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(SimpleMiniGameComponent.class).get());

        return 0;
    }

    @Override
    public List<Entity> addNewEntityToEngine(Engine engine) {
        List<Entity> returnList = new ArrayList<>();
        for (Entity entity :
                this.entityToAddToEngine) {
            engine.addEntity(entity);
            returnList.add(entity);
        }
        this.entityToAddToEngine.clear();
        return returnList;
    }

}

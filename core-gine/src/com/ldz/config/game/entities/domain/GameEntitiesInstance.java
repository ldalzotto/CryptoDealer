package com.ldz.config.game.entities.domain;

import java.util.List;

/**
 * Created by Loic on 10/09/2017.
 */
public class GameEntitiesInstance {
    private List<GameEntityInstance> entities;

    public List<GameEntityInstance> getEntities() {
        return entities;
    }

    public void setEntities(List<GameEntityInstance> entities) {
        this.entities = entities;
    }

}

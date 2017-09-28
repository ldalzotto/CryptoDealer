package com.ldz.entity.mini.games.simple;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.util.EntityUtil;

/**
 * Created by Loic on 26/09/2017.
 */
public class PlayerEntity extends EntityWithId {

    public PlayerEntity(Vector2 vector2, Texture texture, int z, Vector2 speed) {
        EntityUtil.addtextureEntityComponents(vector2, texture, z, this);
        EntityUtil.addPhysicsMovementCompoentn(speed, this);
        EntityUtil.addCollisionCalculationComponent(this);
        EntityUtil.addDragAndDropComponent(this);
    }

}

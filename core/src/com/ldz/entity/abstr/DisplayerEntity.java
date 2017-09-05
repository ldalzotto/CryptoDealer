package com.ldz.entity.abstr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.util.EntityUtil;

import java.util.ArrayList;

/**
 * Created by Loic on 20/08/2017.
 */
public class DisplayerEntity extends EntityWithId {

    public DisplayerEntity(Vector2 position, Texture texture, int z, ArrayList<String> entityInBagIds) {

        EntityUtil.addtextureEntityComponents(position, texture, z, this);
        EntityUtil.addDisplayerEntityComponentsFromEntityId(entityInBagIds, this);

    }
}

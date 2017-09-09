package com.ldz.entity.abstr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.util.EntityUtil;

import java.util.ArrayList;

/**
 * Created by Loic on 20/08/2017.
 */
public class DisplayerEntity extends EntityWithId {

    public DisplayerEntity() {
        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();
        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(bagOfEntitiesComponent);
        this.add(parentAndChildComponent);
    }

    public DisplayerEntity(Vector2 position, Texture texture, int z, ArrayList<String> entityInBagIds) {

        EntityUtil.addtextureEntityComponents(position, texture, z, this);
        EntityUtil.addDisplayerEntityComponentsFromEntityId(entityInBagIds, this);

    }
}

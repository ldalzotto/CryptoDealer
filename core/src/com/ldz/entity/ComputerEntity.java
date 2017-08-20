package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.TimeAccumlatorComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class ComputerEntity extends TextureDisplayEntity {

    public ComputerEntity(Vector2 position, Texture texture) {
        super(position, texture);

        TimeAccumlatorComponent timeAccumlatorComponent = new TimeAccumlatorComponent();
        this.add(timeAccumlatorComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();

        this.add(parentAndChildComponent);

        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntity(EntityFactory.ENTITY_TYPE.UPGRADE_MENU_BACKGROUND));
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntity(EntityFactory.ENTITY_TYPE.EXIT_BOX_MENU));

        this.add(bagOfEntitiesComponent);
    }
}

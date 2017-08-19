package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
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

        Entity backgroundEntity = EntityFactory.getEntity(EntityFactory.ENTITY_TYPE.UPGRADE_MENU_BACKGROUND);
        ParentAndChildComponent parentAndChildComponent1 = backgroundEntity.getComponent(ParentAndChildComponent.class);
        if(parentAndChildComponent1 != null){
            parentAndChildComponent1.parent = this;
        }
        parentAndChildComponent.childs.add(backgroundEntity);

        this.add(parentAndChildComponent);


    }
}

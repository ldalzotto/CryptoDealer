package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.PopUpComponent;
import com.ldz.component.TimeAccumlatorComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class ComputerEntity extends TextureDisplayEntity {

    public ComputerEntity(Vector2 position, Texture texture) {
        super(position, texture);

        TimeAccumlatorComponent timeAccumlatorComponent = new TimeAccumlatorComponent();
        timeAccumlatorComponent.targetEntity = EntityFactory.getEntity(EntityFactory.ENTITY_TYPE.UPGRADE_MENU_BACKGROUND);

        PopUpComponent popUpComponent = timeAccumlatorComponent.targetEntity.getComponent(PopUpComponent.class);
        if(popUpComponent != null){
            popUpComponent.sourceOfPopupTimeAccumulatorComponent = timeAccumlatorComponent;
            popUpComponent.sourceEntity = this;
        }

        this.add(timeAccumlatorComponent);

    }
}

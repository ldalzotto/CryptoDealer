package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PopUpComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class ComputerMenuBackgroundEntity extends TextureDisplayEntity {

    public ComputerMenuBackgroundEntity(Vector2 position, Texture texture) {

        super(position, texture);

        PopUpComponent popUpComponent = new PopUpComponent();
        popUpComponent.popupBounding = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        this.add(popUpComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();

        Entity exitBoxEntity = EntityFactory.getEntity(EntityFactory.ENTITY_TYPE.EXIT_BOX_MENU);
        ParentAndChildComponent parentAndChildComponent1 = exitBoxEntity.getComponent(ParentAndChildComponent.class);
        if(parentAndChildComponent1 != null){
            parentAndChildComponent1.parent = this;
        }
        parentAndChildComponent.childs.add(exitBoxEntity);


        this.add(parentAndChildComponent);


    }
}

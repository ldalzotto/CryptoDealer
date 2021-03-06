package com.ldz.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PopUpComponent;
import com.ldz.config.game.entities.EntityId;
import com.ldz.entity.abstr.TextureDisplayEntity;
import com.ldz.entity.util.EntityUtil;

import java.util.List;

/**
 * Created by Loic on 19/08/2017.
 */
public class PopUpEntity extends TextureDisplayEntity {

    public PopUpEntity(Vector2 position, Texture texture, int z, List<String> entityInBagIds) {

        super(position, texture, z);

        PopUpComponent popUpComponent = new PopUpComponent();
        popUpComponent.popupBounding = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        this.add(popUpComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        EntityUtil.addBagOfEntitiesComponent(entityInBagIds, this);

        EntityWithId exitBox = EntityFactory.createInstanceFromEntityId(EntityId.upgrade_exit_button, position, new Texture(Gdx.files.internal("assets/exit_cross.jpg")), z);
        BagOfEntitiesComponent bagOfEntitiesComponent = this.getComponent(BagOfEntitiesComponent.class);
        if (bagOfEntitiesComponent != null) {
            bagOfEntitiesComponent.entities.add(exitBox);
        }
    }
}

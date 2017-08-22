package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.OnActionComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

import java.util.function.Function;

/**
 * Created by Loic on 20/08/2017.
 */
public class ButtonEntity extends TextureDisplayEntity {

    private static Function<Entity, Void> entityVoidFunction = new Function<Entity, Void>() {
        @Override
        public Void apply(Entity entity) {
            //get cost
            ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
            if (parentAndChildComponent != null) {
                Entity parentEntity = parentAndChildComponent.parent;
                BuyableUpgradeComponent buyableUpgradeComponent = parentEntity.getComponent(BuyableUpgradeComponent.class);
                buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.ASKING_FOR_UPGRADE;
            }

            return null;
        }
    };

    public ButtonEntity(Vector2 position, Texture texture, int z) {
        super(position, texture, z);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        OnActionComponent onActionComponent = new OnActionComponent();
        onActionComponent.action_type = OnActionComponent.ACTION_TYPE.ON_CLICK_INSIDE;
        onActionComponent.function = entityVoidFunction;
        this.add(onActionComponent);

    }
}

package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.OnActionComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.action.IOnAction;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 20/08/2017.
 */
public class ButtonEntity extends TextureDisplayEntity {


    public ButtonEntity(Vector2 position, Texture texture, IOnAction actionToExecute, int z) {
        super(position, texture, z);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        OnActionComponent onActionComponent = new OnActionComponent();
        onActionComponent.action_type = OnActionComponent.ACTION_TYPE.ON_CLICK_INSIDE;
        onActionComponent.action = actionToExecute;

        this.add(onActionComponent);

    }
}

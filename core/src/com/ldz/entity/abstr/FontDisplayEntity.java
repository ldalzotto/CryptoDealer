package com.ldz.entity.abstr;

import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.TranformComponent;
import com.ldz.entity.EntityWithId;

/**
 * Created by Loic on 20/08/2017.
 */
public class FontDisplayEntity extends EntityWithId {

    public FontDisplayEntity(Vector2 position, String stringToDisplay) {

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;
        this.add(tranformComponent);

        BitmapFontComponent bitmapFontComponent = new BitmapFontComponent();
        bitmapFontComponent.stringToDisplay = stringToDisplay;
        this.add(bitmapFontComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

    }
}

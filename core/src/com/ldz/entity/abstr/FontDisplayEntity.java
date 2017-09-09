package com.ldz.entity.abstr;

import com.badlogic.gdx.math.Vector2;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.util.EntityUtil;

/**
 * Created by Loic on 20/08/2017.
 */
public class FontDisplayEntity extends EntityWithId {

    public FontDisplayEntity(Vector2 position, String stringToDisplay) {

        EntityUtil.addFontDisplayComponentsFromEntityId(position, stringToDisplay, this);

    }
}

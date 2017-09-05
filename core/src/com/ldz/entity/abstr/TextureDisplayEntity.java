package com.ldz.entity.abstr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.util.EntityUtil;

/**
 * Created by Loic on 19/08/2017.
 */
public abstract class TextureDisplayEntity extends EntityWithId {

    public TextureDisplayEntity(Vector2 position, Texture texture, int z) {

        EntityUtil.addtextureEntityComponents(position, texture, z, this);

    }
}

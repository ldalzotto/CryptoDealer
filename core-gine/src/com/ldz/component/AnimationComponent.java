package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.entity.custom.TextureAnimation;

/**
 * Created by Loic on 03/09/2017.
 */
public class AnimationComponent implements Component {

    public TextureAnimation animation;
    public float currentAnimationTime = 0f;

}

package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Loic on 19/08/2017.
 */
public class PopUpComponent implements Component {

    public Rectangle popupBounding;
    public Entity sourceEntity;
    public TimeAccumlatorComponent sourceOfPopupTimeAccumulatorComponent;

}

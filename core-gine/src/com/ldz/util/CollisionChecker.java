package com.ldz.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.ldz.component.BoudingRectangleComponent;

/**
 * Created by Loic on 19/08/2017.
 */
public class CollisionChecker {

    public static boolean tapPressedInside(int mx, int my, Entity entity, OrthographicCamera orthographicCamera){
        boolean isPressedInside = false;
        if(entity != null){
            BoudingRectangleComponent boudingRectangleComponent = entity.getComponent(BoudingRectangleComponent.class);
            if(boudingRectangleComponent != null){
                Vector3 unprojected = orthographicCamera.unproject(new Vector3(mx, my, 0));
                if(boudingRectangleComponent.rectangle.contains(unprojected.x, unprojected.y)){
                    isPressedInside = true;
                }
            }
        }
        return isPressedInside;
    }
}

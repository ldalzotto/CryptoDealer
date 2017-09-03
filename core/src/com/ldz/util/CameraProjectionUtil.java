package com.ldz.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loic on 03/09/2017.
 */
public class CameraProjectionUtil {

    public static Map<MapKey, Float> getWidthAndHeight(Rectangle boudingRectangle, Vector2 position, OrthographicCamera orthographicCamera) {
        Vector2 oppositePoint = new Vector2(position.x + Math.abs(boudingRectangle.width), position.y + Math.abs(boudingRectangle.height));

        Vector3 vector3 = orthographicCamera.project(new Vector3(boudingRectangle.x, boudingRectangle.y, 0));
        Vector3 oppositePointProjected = orthographicCamera.project(new Vector3(oppositePoint.x, oppositePoint.y, 0));

        Map<MapKey, Float> mapKeyFloatMap = new HashMap<>();
        mapKeyFloatMap.put(MapKey.WIDHT, oppositePointProjected.x - vector3.x);
        mapKeyFloatMap.put(MapKey.HEIGHT, oppositePointProjected.y - vector3.y);

        mapKeyFloatMap.put(MapKey.X, vector3.x);
        mapKeyFloatMap.put(MapKey.Y, vector3.y);

        return mapKeyFloatMap;
    }

    public enum MapKey {
        HEIGHT, WIDHT, X, Y;
    }

}

package com.ldz.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.TextureComponent;
import com.ldz.component.TranformComponent;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import com.ldz.util.ComponentUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by Loic on 19/08/2017.
 */
public class RenderingSystem extends SortedIteratingSystem implements IRetrieveAllEntitiesFromSystem {

    private static final String TAG = RenderingSystem.class.getSimpleName();

    private OrthographicCamera orthographicCamera;
    private SpriteBatch batch;

    public RenderingSystem(OrthographicCamera orthographicCamera, SpriteBatch spriteBatch) {
        super(Family.all(TextureComponent.class, TranformComponent.class, BoudingRectangleComponent.class).get(), new zComparator());
        this.orthographicCamera = orthographicCamera;
        this.batch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //retrive graphics component
        Map<String, Component> componentContainer = null;
        try {
            componentContainer = ComponentUtil.getAllComponentsFromEntity(entity, TextureComponent.class, TranformComponent.class, BoudingRectangleComponent.class);
        } catch (Exception e) {
            Gdx.app.error(TAG, e.getMessage());
            Gdx.app.error(TAG, e.getCause().toString());
            return;
        }

        TextureComponent textureComponent = (TextureComponent) componentContainer.get(TextureComponent.class.getSimpleName());
        TranformComponent tranformComponent = (TranformComponent) componentContainer.get(TranformComponent.class.getSimpleName());
        BoudingRectangleComponent boudingRectangleComponent = (BoudingRectangleComponent) componentContainer.get(BoudingRectangleComponent.class.getSimpleName());

        //apply render
        Vector2 position = tranformComponent.position;
        Rectangle rectangle = boudingRectangleComponent.rectangle;

        Vector2 oppositePoint = new Vector2(position.x + Math.abs(rectangle.width), position.y + Math.abs(rectangle.height));

        Vector3 vector3 = orthographicCamera.project(new Vector3(rectangle.x, rectangle.y, 0));
        Vector3 oppositePointProjected = orthographicCamera.project(new Vector3(oppositePoint.x, oppositePoint.y, 0));

        batch.draw(textureComponent.texture, vector3.x, vector3.y, (oppositePointProjected.x - vector3.x), (oppositePointProjected.y - vector3.y));
    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.getEntities());
        return entities;
    }

    public static class zComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity o1, Entity o2) {
            TranformComponent tranformComponent1 = o1.getComponent(TranformComponent.class);
            TranformComponent tranformComponent2 = o2.getComponent(TranformComponent.class);
            return (int) Math.signum(tranformComponent1.z - tranformComponent2.z);
        }
    }
}

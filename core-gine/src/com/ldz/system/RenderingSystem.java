package com.ldz.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.AnimationComponent;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.TextureComponent;
import com.ldz.component.TranformComponent;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import com.ldz.util.CameraProjectionUtil;
import com.ldz.util.ComponentUtil;
import com.ldz.util.zComparator;

import java.util.Arrays;
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
        super(Family.all(TranformComponent.class, BoudingRectangleComponent.class)
                .one(TextureComponent.class, AnimationComponent.class).get(), new zComparator());
        this.orthographicCamera = orthographicCamera;
        this.batch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //retrive graphics component
        Map<String, Component> componentContainer = null;
        try {
            componentContainer = ComponentUtil.getAllComponentsFromEntity(entity, TextureComponent.class, TranformComponent.class, BoudingRectangleComponent.class, AnimationComponent.class);
        } catch (Exception e) {
            Gdx.app.error(TAG, e.getMessage());
            Gdx.app.error(TAG, e.getCause().toString());
            return;
        }

        TranformComponent tranformComponent = (TranformComponent) componentContainer.get(TranformComponent.class.getSimpleName());
        BoudingRectangleComponent boudingRectangleComponent = (BoudingRectangleComponent) componentContainer.get(BoudingRectangleComponent.class.getSimpleName());

        TextureComponent textureComponent = (TextureComponent) componentContainer.get(TextureComponent.class.getSimpleName());
        AnimationComponent animationComponent = (AnimationComponent) componentContainer.get(AnimationComponent.class.getSimpleName());

        Vector2 position = tranformComponent.position;
        Rectangle rectangle = boudingRectangleComponent.rectangle;
        Map<CameraProjectionUtil.MapKey, Float> mapKeyFloatMap = CameraProjectionUtil.getWidthAndHeight(rectangle, position, this.orthographicCamera);
        Texture textureToDraw = null;

        if (textureComponent != null) {
            textureToDraw = textureComponent.texture;
        } else if (animationComponent != null) {
            animationComponent.currentAnimationTime += deltaTime;
            textureToDraw = animationComponent.animation.getKeyFrame(animationComponent.currentAnimationTime);
        }

        batch.draw(textureToDraw, mapKeyFloatMap.get(CameraProjectionUtil.MapKey.X), mapKeyFloatMap.get(CameraProjectionUtil.MapKey.Y),
                mapKeyFloatMap.get(CameraProjectionUtil.MapKey.WIDHT), mapKeyFloatMap.get(CameraProjectionUtil.MapKey.HEIGHT));

    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.getEntities());
        return entities;
    }

}

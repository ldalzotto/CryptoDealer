package com.ldz.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.config.game.entities.EntityId;
import com.ldz.engine.MyEngine;
import com.ldz.entity.EntityFactory;
import com.ldz.listener.EntityRemoveListener;
import com.ldz.screen.viewport.GlobalViewport;
import com.ldz.system.*;

/**
 * Created by Loic on 19/08/2017.
 */
public class MainGameScreen extends GlobalViewport implements Screen {

    private static final String TAG = MainGameScreen.class.getSimpleName();
    private static final int MAIN_GAME_SCREEN_HEIGHT = 960;
    private static final int MAIN_GAME_SCREEN_WIDTH = 720;
    private static MainGameScreen instance = null;
    private SpriteBatch batch = null;
    private OrthographicCamera camera;
    private Viewport viewport;
    //Ashley ECS
    private Engine engine;

    public MainGameScreen() {
        setupViewport(MAIN_GAME_SCREEN_WIDTH, MAIN_GAME_SCREEN_HEIGHT);
    }

    public static MainGameScreen getInstance() {
        if (instance == null) {
            instance = new MainGameScreen();
        }
        return instance;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(VIEWPORT.getViewportWidth(), VIEWPORT.getViewportHeight());
        camera.setToOrtho(false, VIEWPORT.getViewportWidth(), VIEWPORT.getViewportHeight());

        viewport = new FitViewport(VIEWPORT.getViewportWidth(), VIEWPORT.getViewportHeight(), camera);

        camera.update();

        //ashley initialisation
        engine = MyEngine.getInstance();

        //set all entity
        engine.addEntity(EntityFactory.getEntityFromId(EntityId.zit_coin_entity));
        engine.addEntity(EntityFactory.getEntityFromId(EntityId.ithereum_coin_entity));
        engine.addEntity(EntityFactory.getEntityFromId(EntityId.loud_coin_entity));
        engine.addEntity(EntityFactory.getEntityFromId(EntityId.computer_entity));

        //add persistant upgrade entity
        engine.addEntity(EntityFactory.getEntityFromId(EntityId.persistant_upgrade_1));
        engine.addEntity(EntityFactory.getEntityFromId(EntityId.persistant_upgrade_2));

        //set all systems
        engine.addSystem(new RenderingSystem(camera, batch));
        engine.addSystem(new RenderingBitmapSystem(camera, batch));
        engine.addSystem(CurrencySystem.getInstance());
        engine.addSystem(DelayedDisplayerSystem.getInstance(camera));
        engine.addSystem(PopupSystem.getInstance(camera));
        engine.addSystem(ExitBoxSystem.getInstance(camera));
        engine.addSystem(ParentAndChildSystem.getInstance());
        engine.addSystem(BagOfEntitiesToEngineSystem.getInstance());
        engine.addSystem(InstantDisplayerSystem.getInstance(camera));
        engine.addSystem(BuyableUpgradePopupSystem.getInstance());
        engine.addSystem(OnActionSystem.getInstance(camera));
        engine.addSystem(PersistantUpgradeSystem.getInstance());


        engine.addEntityListener(Family.all(ParentAndChildComponent.class).get(), new EntityRemoveListener());

    }

    @Override
    public void render(float delta) {
        camera.update();

        //Gdx.gl.glViewport(0, 0, (int) VIEWPORT.getViewportWidth(), (int) VIEWPORT.getViewportHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        engine.update(delta);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

package com.ldz.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.system.custom.MyIteratingSystem;
import com.ldz.util.ComponentUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loic on 27/08/2017.
 */
public class PersistantUpgradeSystem extends MyIteratingSystem {

    private static final String TAG = PersistantUpgradeSystem.class.getSimpleName();

    private static PersistantUpgradeSystem instance;

    public PersistantUpgradeSystem() {
        super(Family.all(PersistantUpgradeComponent.class).get());
    }

    public static PersistantUpgradeSystem getInstance() {
        if (instance == null) {
            instance = new PersistantUpgradeSystem();
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {


        Map<String, Component> componentMap = new HashMap<>();

        try {
            componentMap = ComponentUtil.getAllComponentsFromEntity(entity, PersistantUpgradeComponent.class);
        } catch (Exception e) {
            Gdx.app.debug(TAG, e.getMessage());
            Gdx.app.debug(TAG, e.getCause().toString());
        }

        PersistantUpgradeComponent persistantUpgradeComponent = (PersistantUpgradeComponent) componentMap.get(PersistantUpgradeComponent.class.getSimpleName());

        persistantUpgradeComponent.timeAccumulator += deltaTime;

        //update decay performance
        if (persistantUpgradeComponent.timeAccumulator >= 1.0f) {
            persistantUpgradeComponent.itemPerformances = persistantUpgradeComponent.itemPerformances * persistantUpgradeComponent.decayRatePerSeconds;
            persistantUpgradeComponent.timeAccumulator = 0.0f;
        }

    }
}

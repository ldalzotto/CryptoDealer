package com.ldz.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.component.UpgradeCurrencyDisplayerComponent;
import com.ldz.system.custom.MyIteratingSystem;
import com.ldz.util.ComponentUtil;
import com.ldz.util.LoggingUtil;
import com.ldz.util.UpgradeUtil;

import java.util.Map;

/**
 * Created by Loic on 06/09/2017.
 */
public class UpgradeCurrencyDisplayerSystem extends MyIteratingSystem {

    private static final String TAG = UpgradeCurrencyDisplayerSystem.class.getSimpleName();
    public static UpgradeCurrencyDisplayerSystem instance = null;
    private ImmutableArray<Entity> persistantUpgradeEntities;

    public UpgradeCurrencyDisplayerSystem() {
        super(Family.all(UpgradeCurrencyDisplayerComponent.class, BitmapFontComponent.class).get());
    }

    public static UpgradeCurrencyDisplayerSystem getInstance() {
        if (instance == null) {
            instance = new UpgradeCurrencyDisplayerSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.persistantUpgradeEntities = engine.getEntitiesFor(Family.all(PersistantUpgradeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {


        Map<String, Component> componentMap = null;
        try {
            componentMap = ComponentUtil.getAllComponentsFromEntity(entity, UpgradeCurrencyDisplayerComponent.class, BitmapFontComponent.class);
        } catch (Exception e) {
            LoggingUtil.DEBUG(TAG, "Cannot get components.", e);
            return;
        }

        UpgradeCurrencyDisplayerComponent upgradeCurrencyDisplayerComponent = (UpgradeCurrencyDisplayerComponent) componentMap.get(UpgradeCurrencyDisplayerComponent.class.getSimpleName());
        BitmapFontComponent bitmapFontComponent = (BitmapFontComponent) componentMap.get(BitmapFontComponent.class.getSimpleName());


        //extract entity from parent
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        if (parentAndChildComponent != null) {
            Object upgradeId = parentAndChildComponent.dataToTransit.get(ParentAndChildComponent.DATA_TO_TRANSIT_KEY.UPGRADE_ID_KEY);
            if (upgradeId != null) {
                PersistantUpgradeComponent.UpgradeId currentUpgradeId = (PersistantUpgradeComponent.UpgradeId) upgradeId;
                upgradeCurrencyDisplayerComponent.upgradeId = currentUpgradeId;
            }
        }

        PersistantUpgradeComponent persistantUpgradeComponent = UpgradeUtil.retrievePersistantUpgrade(upgradeCurrencyDisplayerComponent.upgradeId, this.persistantUpgradeEntities);

        if (persistantUpgradeComponent != null) {
            Float objectCost = persistantUpgradeComponent.extractFromObjectCost(upgradeCurrencyDisplayerComponent.currencyType);
            bitmapFontComponent.stringToDisplay = Float.toString(objectCost);

        }

    }
}

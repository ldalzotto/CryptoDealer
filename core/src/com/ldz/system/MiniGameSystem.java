package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.MiniGameComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.engine.MyEngine;
import com.ldz.util.ParentAndChildUtil;

/**
 * Created by Loic on 17/09/2017.
 */
public class MiniGameSystem extends IteratingSystem {

    private static MiniGameSystem instance = null;

    public MiniGameSystem() {
        super(Family.all(MiniGameComponent.class).get());
    }

    public static MiniGameSystem getInstance() {
        if (instance == null) {
            instance = new MiniGameSystem();
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        MiniGameComponent miniGameComponent = entity.getComponent(MiniGameComponent.class);

        //catch data from parents
        //retrieve state
        if (parentAndChildComponent != null && miniGameComponent != null) {
            BuyableUpgradeComponent.STATE buyableUpgradeComponentState = (BuyableUpgradeComponent.STATE) parentAndChildComponent.extractFromDataToTransit(ParentAndChildComponent.DATA_TO_TRANSIT_KEY.BUYABLE_UPGRADE_COMPONENT_STATE);
            if (buyableUpgradeComponentState != null) {
                miniGameComponent.buyableUpgradeCompState = buyableUpgradeComponentState;
            }

            PersistantUpgradeComponent.UpgradeId upgradeId = (PersistantUpgradeComponent.UpgradeId) parentAndChildComponent.extractFromDataToTransit(ParentAndChildComponent.DATA_TO_TRANSIT_KEY.UPGRADE_ID_KEY);
            if (upgradeId != null) {
                miniGameComponent.upgradeId = upgradeId;
            }
        }


        int scoreToAdd = 0;

        if (miniGameComponent != null) {

            switch (miniGameComponent.state) {
                case PENDING:
                    miniGameComponent.state = MiniGameComponent.STATE.RUNNING;
                    break;
                case RUNNING:
                    miniGameComponent.executionTimeAccumulator += deltaTime;

                    if (miniGameComponent.iMiniGameUpdate != null) {
                        scoreToAdd = miniGameComponent.iMiniGameUpdate.update(miniGameComponent, deltaTime);
                    }

                    if (miniGameComponent.executionTimeAccumulator > MiniGameComponent.EXECUTION_TIME) {
                        miniGameComponent.state = MiniGameComponent.STATE.COMPLETED;
                    }

                    break;
                case COMPLETED:
                    //TODO calculate bonus time to add
                    miniGameComponent.currencScore = 0;
                    miniGameComponent.state = MiniGameComponent.STATE.DESTROY;
                    break;
                case DESTROY:
                    ParentAndChildUtil.destroyFromParent(entity, entity.getComponent(ParentAndChildComponent.class), MyEngine.getInstance());
                    break;
            }

            miniGameComponent.currencScore += scoreToAdd;

        }

    }
}
package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.component.domain.CurrencyInstance;
import com.ldz.config.game.entities.InstanceEntityId;
import com.ldz.entity.EntityFactory;
import com.ldz.entity.EntityWithId;

/**
 * Created by Loic on 27/08/2017.
 */
public class PersistantUpgradeComponent implements Component {

    public final float CRITICAL_PERFORMANCE_LEVEL = 0.2f;

    public UpgradeId upgradeId;
    public CurrencyInstance objectCost;
    public CurrencyInstance objectBonus;

    public float itemPerformances = 1.0f;
    public float decayRatePerSeconds = 0.01f;
    public float timeAccumulator = 0f;
    public int upgradeLevel = 0;

    public STATE state = STATE.PENDING;

    public Float extractFromObjectCost(CurrencyComponent.CURRENCY_TYPE currency_type) {
        return this.objectCost.extractValue(currency_type);
    }

    public Float extractFromObjectBonus(CurrencyComponent.CURRENCY_TYPE currency_type) {
        return this.objectBonus.extractValue(currency_type);
    }

    public enum UpgradeId {
        UPGRADE_1(InstanceEntityId.upgrade_1_critical_state_display, null),
        UPGRADE_2(InstanceEntityId.upgrade_1_critical_state_display, null);

        private InstanceEntityId entityId;
        private EntityWithId criticalStateEntity;

        UpgradeId(InstanceEntityId entityId, EntityWithId criticalStateEntity) {
            this.entityId = entityId;
            this.criticalStateEntity = criticalStateEntity;
        }

        public InstanceEntityId getEntityId() {
            return entityId;
        }

        public EntityWithId getCriticalStateEntity() {
            if (criticalStateEntity == null) {
                criticalStateEntity = EntityFactory.getEntityFromInstanceId(entityId);
            }
            return criticalStateEntity;
        }
    }

    public enum STATE {
        PENDING,
        UPGRADING;
    }

}

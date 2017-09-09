package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.system.custom.MyIteratingSystem;

import java.util.Map;

/**
 * Created by Loic on 09/09/2017.
 */
public class DataTransitToChildSystem extends MyIteratingSystem {

    private static DataTransitToChildSystem instance = null;

    public DataTransitToChildSystem() {
        super(Family.all(ParentAndChildComponent.class).get());
    }

    public static DataTransitToChildSystem getInstance() {
        if (instance == null) {
            instance = new DataTransitToChildSystem();
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);

        if (parentAndChildComponent != null) {
            transitDataToChildRecursively(parentAndChildComponent, parentAndChildComponent.dataToTransit);
        }

    }

    private void transitDataToChildRecursively(ParentAndChildComponent parentAndChildComponent, Map<ParentAndChildComponent.DATA_TO_TRANSIT_KEY, Object> dataToTransit) {

        if (dataToTransit != null && parentAndChildComponent != null && dataToTransit.size() != 0) {
            parentAndChildComponent.dataToTransit.putAll(dataToTransit);

            for (Entity entity :
                    parentAndChildComponent.childs) {
                ParentAndChildComponent parentAndChildComponent1 = entity.getComponent(ParentAndChildComponent.class);
                if (parentAndChildComponent1 != null) {
                    transitDataToChildRecursively(parentAndChildComponent1, dataToTransit);
                }
            }
        }


    }
}

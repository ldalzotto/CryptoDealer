package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.ldz.util.ComponentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Loic on 20/08/2017.
 */
public class BagOfEntitiesComponent implements Component {

    public List<Entity> entities = new ArrayList<>();
    public boolean addEntityToEngine = false;

    /**
     * <p>
     * Add entites possessing {@link BagOfEntitiesComponent} to the engine recursively.
     * <p>
     * <p>
     * Entites possessing {@link DisplayStateComponent} are not processed. The associated condition of display is left to the appropriate system.
     * </p>
     *
     * @param bagOfEntitiesComponent The bag of entities willing to display.
     */
    public static void addTagToEngineFromBagOfEntitiesRecursively(BagOfEntitiesComponent bagOfEntitiesComponent) {

        bagOfEntitiesComponent.addEntityToEngine = true;

        for (Entity entity :
                bagOfEntitiesComponent.entities) {
            Map<String, Component> componentContainer = ComponentUtil.getAllComponentsFromEntity(entity);
            if (componentContainer.get(BagOfEntitiesComponent.class.getSimpleName()) != null &&
                    componentContainer.get(DisplayStateComponent.class.getSimpleName()) == null) {
                addTagToEngineFromBagOfEntitiesRecursively((BagOfEntitiesComponent) componentContainer.get(BagOfEntitiesComponent.class.getSimpleName()));
            }
        }

    }

}

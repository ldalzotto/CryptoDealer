package com.ldz.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;

import java.util.*;

/**
 * Created by Loic on 25/08/2017.
 */
public class ComponentUtil {

    public static <T extends Component> T getComponentFromEntity(Entity entity, Class<T> componentClass) {

        T component = null;
        if (entity == null || componentClass == null) {
            throw new RuntimeException("Cannont extract component from null !");
        }

        component = entity.getComponent(componentClass);


        return component;

    }


    public static Map<String, Component> getAllComponentsFromEntity(Entity entity) {

        Map<String, Component> cClassContainer = new HashMap<>();
        ImmutableArray<Component> componentList = entity.getComponents();

        for (Component component :
                componentList) {
            Class<? extends Component> cclass = component.getClass();
            if (component != null) {
                cClassContainer.put(cclass.getSimpleName(), component);
            }
        }

        return cClassContainer;

    }

    public static Map<String, Component> getAllComponentsFromEntity(Entity entity, Class<? extends Component>... componentClass) throws Exception {

        Map<String, Component> cClassContainer = new HashMap<>();
        List<Class<? extends Component>> componentClassList = Arrays.asList(componentClass);

        for (Class<? extends Component> cclass :
                componentClassList) {
            Component component = ComponentUtil.getComponentFromEntity(entity, cclass);
            if (component != null) {
                cClassContainer.put(cclass.getSimpleName(), component);
            }
        }

        return cClassContainer;

    }

}

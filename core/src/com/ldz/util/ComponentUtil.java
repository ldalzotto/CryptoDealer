package com.ldz.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.*;

/**
 * Created by Loic on 25/08/2017.
 */
public class ComponentUtil {

    public static <T extends Component> T getComponentFromEntity(Entity entity, Class<T> componentClass) throws Exception {

        T component = null;
        try {
            if (entity == null || componentClass == null) {
                throw new RuntimeException("Cannont extract component from null !");
            }

            component = entity.getComponent(componentClass);

            if (component == null) {
                throw new RuntimeException("Cannont extract component from null !");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return component;

    }


    public static Map<String, Component> getAllComponentsFromEntity(Entity entity, Class<? extends Component>... componentClass) throws Exception {

        Map<String, Component> cClassContainer = new HashMap<>();
        List<Class<? extends Component>> componentClassList = Arrays.asList(componentClass);

        for (Class<? extends Component> cclass :
                componentClassList) {
            cClassContainer.put(cclass.getSimpleName(), ComponentUtil.getComponentFromEntity(entity, cclass));
        }

        return cClassContainer;

    }

}

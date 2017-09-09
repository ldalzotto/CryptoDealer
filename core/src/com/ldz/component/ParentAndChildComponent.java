package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Loic on 19/08/2017.
 */
public class ParentAndChildComponent implements Component {

    public List<Entity> childs = new ArrayList<>();
    public Entity parent;
    public Map<DATA_TO_TRANSIT_KEY, Object> dataToTransit = new HashMap<>();

    public Object extractFromDataToTransit(DATA_TO_TRANSIT_KEY dataToTransitKey) {
        if (dataToTransit != null && dataToTransit.get(dataToTransitKey) != null) {
            return dataToTransit.get(dataToTransitKey);
        }

        return null;
    }

    public enum DATA_TO_TRANSIT_KEY {
        UPGRADE_ID_KEY,
        UPGRADE_PERFORMANCE;
    }

}

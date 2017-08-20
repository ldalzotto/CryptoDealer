package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.function.Function;

/**
 * Created by Loic on 20/08/2017.
 */
public class OnActionComponent implements Component {

    public enum ACTION_TYPE {
        ON_CLICK_INSIDE;
    }

    public Function<Entity, Void> function;
    public ACTION_TYPE action_type;

}

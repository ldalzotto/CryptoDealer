package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.component.action.IOnAction;

/**
 * Created by Loic on 20/08/2017.
 */
public class OnActionComponent implements Component {

    public IOnAction action;
    public ACTION_TYPE action_type;

    public enum ACTION_TYPE {
        ON_CLICK_INSIDE;
    }


}

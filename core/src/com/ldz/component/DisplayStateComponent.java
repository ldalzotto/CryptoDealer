package com.ldz.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Loic on 20/08/2017.
 */
public class DisplayStateComponent implements Component {

    public STATE state;
    public boolean isDisplayed = false;

    public enum STATE {
        INSTANT, DELAYED;
    }

}

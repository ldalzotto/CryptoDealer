package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loic on 28/09/2017.
 */
public class DragAndDropComponent implements Component {

    public boolean processing = true;

    public STATE state = STATE.PENDING;

    public Vector2 startPoint = new Vector2();
    public Vector2 endPoint = new Vector2();

    public Vector2 directionVector = new Vector2();

    public Vector2 consumeDirectionVector() {
        Vector2 directionVectorCopy = directionVector.cpy();
        state = STATE.PENDING;
        return directionVectorCopy;
    }

    public enum STATE {
        PENDING,
        START_CLICKING,
        WHILE_CLICKING,
        END_CLICKING,
        PROCESS_FINISHED;
    }

}

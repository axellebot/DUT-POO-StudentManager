package axel.control;

import axel.model.Promotion;

import java.util.ArrayList;

/**
 * Created by axell on 01/12/15.
 */
public abstract class AbstractControleur {
    protected Promotion promotion;

    abstract public void control(ArrayList<String> list);


}

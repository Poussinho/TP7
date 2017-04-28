package work.pegase.android.demo.tp7;

import android.graphics.Color;

public class Point {
    float x,y;
    int raduis = 95;
    int color = Color.GREEN;


    /**
     * Constructor with initialization.
     * By default point color is RED
     * @param x
     * @param y
     */
    public Point (float x, float y){

        this.x = x;
        this.y = y;
    }


    /**
     * Change the Point location (when catch MOVE Touch event)
     * @param x
     * @param y
     */
    public void change(float x, float y){


    }


}

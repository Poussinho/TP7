package work.pegase.android.demo.tp7;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * The Canva for draw circle
 * @author hong
 *
 */
public class VCircle extends View {


    /* List de points */
    private List<Point> points = new ArrayList<Point> ();

    public VCircle(Context context) {
        super(context);
    }

    public VCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VCircle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void changePointColor(MotionEvent event){
		/* TODO Etape 9 */
    }

    /**
     * Move a point
     * @param event The motion event link to a moveFromTouch
     */
    public void moveFromTouch(MotionEvent event) {
        for (int i=0;i<event.getPointerCount();i++) {
			/* TODO A compléter (Etape 7)
			 * TODO 1) Vérifier que le Touch désigne un Point existant
			 * TODO 2) Déplacer le Point
			 */
        }
    }


    /**
     * Move a point
     * @param event The motion event link to a moveFromTouch
     */
    public void moveFromSensor(SensorEvent event) {
		/* TODO Déplacer tous les points en fonction des données du capteur Accéléromètre (Etape 7) */
    }

    /**
     * Adding a new point
     * @param
     */
    public void addPoint(MotionEvent event){

        Point point = new Point(getX(),getY());
        points.add(point);





        invalidate();


    }



    /**
     * Clear all circle
     */
    public void clear(){
		/* TODO Effacer tous les points Etape 9 */
    }

    /**
     * DRAWING
     */
    @Override
    public void draw(Canvas canvas) {
    /* Argument : x,y,raduis */
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 95, paint);
        super.draw(canvas);


    }




}

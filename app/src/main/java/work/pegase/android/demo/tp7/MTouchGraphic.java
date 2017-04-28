package work.pegase.android.demo.tp7;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Switch;

import com.example.pereiraan.session6.R;

import java.util.List;


/**
 * Main activity Class for draw on a canva Description of the TP : This TP shows
 * how to : - Use the Touch event of the devices - The drawing shape on a Canva
 * - The Notion of Click and Double Click detection - The Accelerometer tool -
 * Shake detection - Move detection
 *
 * @author hong
 *
 */
public class MTouchGraphic extends Activity implements OnTouchListener {

    /** The canva for draw circle on screen */
    private VCircle canva;
    private Switch accActivation;




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		/* Set Content View */
        setContentView(R.layout.m_touch_graphic);

		/* Binding */
        canva = (VCircle) findViewById(R.id.canva);
        //accActivation = (Switch) findViewById(R.id.accActivation);
        canva.setOnTouchListener(this);


		/* Give information about all sensors */
        SensorManager accM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> s = accM.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
        s = accM.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (s.size() > 0)
            Log.d("MTouchGraphic", " Sensor ACC OK [" + s.get(0).getVendor() + "]");
        else
            Log.d("MTouchGraphic", " Sensor ACC NOK");
        if (s.size() > 0) {
            Log.d("MTouchGraphic", " Sensor TEMPERATURE OK [" + s.get(0).getVendor() + "]");
        } else {
            Log.d("MTouchGraphic", " Sensor TEMPERATURE NOK");
        }
        s = accM.getSensorList(Sensor.TYPE_GYROSCOPE);
        if (s.size() > 0)
            Log.d("MTouchGraphic", " Sensor GYROSCOPE OK [" + s.get(0).getVendor() + "]");
        else
            Log.d("MTouchGraphic", " Sensor GYROSCOPE NOK");
        s = accM.getSensorList(Sensor.TYPE_LIGHT);
        if (s.size() > 0)
            Log.d("MTouchGraphic", " Sensor LIGHT OK [" + s.get(0).getVendor() + "]");
        else
            Log.d("MTouchGraphic", " Sensor LIGHT NOK");
        s = accM.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if (s.size() > 0)
            Log.d("MTouchGraphic", " Sensor MAGNETIC OK [" + s.get(0).getVendor() + "]");
        else
            Log.d("MTouchGraphic", " Sensor MAGNETIC NOK");
        s = accM.getSensorList(Sensor.TYPE_PRESSURE);
        if (s.size() > 0)
            Log.d("MTouchGraphic", " Sensor PRESSURE OK [" + s.get(0).getVendor() + "]");
        else
            Log.d("MTouchGraphic", " Sensor PRESSURE NOK");

        s = accM.getSensorList(Sensor.TYPE_PROXIMITY);
        if (s.size() > 0)
            Log.d("MTouchGraphic", " Sensor PROXIMITY   OK ["
                    + s.get(0).getVendor() + " - MAX : "
                    + s.get(0).getMaximumRange() + "]");
        else
            Log.d("MTouchGraphic", " Sensor PROXIMITY   NOK");

		/* TODO Etape 10 : instancier le Manager AccelerometerManager */

    }





    @Override
    protected void onResume() {
        super.onResume();
		/* TODO Etape 10 -- Enregistrer le listener sur le Capteur AccelerometerManager */
    }

    @Override
    protected void onPause() {
        super.onPause();
		/* TODO Etape 10 -- Désenregistrer le listener sur le Capteur AccelerometerManager */
    }


    /**
     * Catch touchevent v : the view where event has been triggered event : the
     * motion event
     */

    @Override
    public boolean onTouch(View v, MotionEvent event) {
/**
 * On Move detection
 */
        int action = MotionEventCompat.getActionMasked(event);
        int index = MotionEventCompat.getActionIndex(event);
        switch
                (action) {
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }

        event.getPointerCount();
// Nombre de touch
        int id = event.getPointerId(index);
// Récupérer l'identifiant d'un Pointer (multi-touch)
        float x = event.getX();
// Récupérer les coordonnées du touch courant (mono-touch)
        float y = event.getY();
// Récupérer les coordonnées du touch courant (mono-touch)

        x = MotionEventCompat.getX(event,index);
// Récupérer les coordonnées du touch situé à l'index (multi-touch)

        y = MotionEventCompat.getY(event,index);

// Récupérer les coordonnées du touch situé à l'index (multi-touch)
    /* Parcourir l'ensemble des touches actif */
        for
                (
                int i = 0; i < event.getPointerCount(); i++) {
            x = MotionEventCompat.getX(event, i);
            y = MotionEventCompat.getY(event, i);
        }
        return true;
    }


}
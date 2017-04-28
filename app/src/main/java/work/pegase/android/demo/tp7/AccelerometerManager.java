package work.pegase.android.demo.tp7;


        import java.util.ArrayList;
        import java.util.List;

        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.util.Log;

/**
 * The accelerometer class : Extension of the SensorManager for the
 * Accelerometer Sensor. How to use it :
 * 1) Access to the instance
 * 2) Registration of the listener IAcceleratorListener
 * 3) BIND the IAcceleratorListener with your operation
 * 4) Unregistration of the listener
 *
 * @author hong
 *
 */
public class AccelerometerManager implements SensorEventListener {

    private long mLastForce;
    private int mShakeCount;
    private long mLastTime;
    private long mLastShake;

    public static interface IAcceleratorListener  {

        public void onAcceleratorShakeMotion(float force);
        public void onAcceleratorAccuracyChanged(Sensor sensor, int accuracy);
        public void onAcceleratorSensorChanged(SensorEvent event);
    }


    /**
     * Singleton on the manager
     */
    private static AccelerometerManager obj = null;
    /**
     * Static variable for the calculation delay for shake the Motion shake
     * Event
     */
    private static final int SENSOR_DELAY_GAME = 100;
    private static final int FORCE_THRESHOLD = 350;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 500;
    private static final int SHAKE_DURATION = 1000;
    private static final int SHAKE_COUNT = 3;
    /**
     * Static variable for the calculation delay for shake the Motion shake
     * Event
     */
    private static final int SENSOR_DELAY_NORMAL = 1000;



    public static AccelerometerManager getInstance(SensorManager manager,
                                                   int rate) {
        if (obj == null)
            obj = new AccelerometerManager(manager, rate);
        return obj;
    }

    /**
     * The Accelerator sensor
     */
    private List<Sensor> accSensor = null;
    /**
     * Last update event
     */
    private long lastUpdate = 0;
    /**
     * Last acceleration in X
     */
    private float lastX = 0;
    /**
     * Last acceleration in Y
     */
    private float lastY = 0;
    /**
     * Last acceleration in Z
     */
    private float lastZ = 0;
    /**
     * The list of listener
     */
    private final List<IAcceleratorListener> listeners;
    /**
     * The rate
     */
    private int rate = SensorManager.SENSOR_DELAY_GAME;
    /**
     * Is registered
     */
    private final boolean registered = false;
    /**
     * The Sensor Manager of Android
     */
    private final SensorManager sensorManager;



    private AccelerometerManager(SensorManager manager, int rate) {
        listeners = new ArrayList<IAcceleratorListener>();
        sensorManager = manager;
        accSensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        this.rate = rate;


    }

    /**
     * On triggered event AccuracyChanged
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        for (IAcceleratorListener listener : listeners)
            listener.onAcceleratorAccuracyChanged(sensor, accuracy);
    }

    /**
     * On triggered event SensorChanged
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - mLastForce) > SHAKE_TIMEOUT) {
            mShakeCount = 0;
        }

        if ((now - mLastTime) > TIME_THRESHOLD) {
            long diff = now - mLastTime;

            float speed = Math.abs(event.values[0] + event.values[1] + event.values[2] - lastX - lastY - lastZ) / diff * 10000;
            if (speed > FORCE_THRESHOLD) {
                if ((++mShakeCount >= SHAKE_COUNT) && (now - mLastShake > SHAKE_DURATION)) {
                    mLastShake = now;
                    mShakeCount = 0;
                    Log.d("AccelrometerManager", "MOTION DETECTED : " + speed);
                    for (IAcceleratorListener listener : listeners)
                        listener.onAcceleratorShakeMotion(speed);
                }
                mLastForce = now;
            }
            mLastTime = now;
            lastX = event.values[0];
            lastY = event.values[1];
            lastZ = event.values[2];
        }

        // trigger change event
        for (IAcceleratorListener listener : listeners)
            listener.onAcceleratorSensorChanged(event);

    }

    /**
     * Register a new listener
     *
     * @param listener
     *            the listener
     */
    public void registerListener(IAcceleratorListener listener) {
		/* Now bind the listener on this */
        if (registered == false && accSensor.size() > 0)
            sensorManager.registerListener(this, accSensor.get(0), rate);
        listeners.add(listener);
    }

    /**
     * Unregister listeners
     */
    public void unregisterAllListener() {
        listeners.clear();
        if (listeners.size() == 0) {
            sensorManager.unregisterListener(this, accSensor.get(0));
        }
    }



}

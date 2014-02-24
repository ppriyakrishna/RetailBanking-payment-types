package org.openintents.fallarm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openintents.fallarm.sensor.Sensor;
import org.openintents.fallarm.sensor.SensorEvent;
import org.openintents.fallarm.sensor.SensorEventListener;
import org.openintents.fallarm.sensor.SensorManagerSimulator;
import org.openintents.fallarm.sensor.SensorNames;
import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Lets user set connection settings to the SensorSimulator and test them.
 *
 * The connection is outbound to the SensorSimulator.
 *
 * Important to connect at least once using this activity, so that IP and port
 * can be stored and used later in another application. This activity is used to
 * see our simulations effects and to test them.
 *
 * @author Neeta Vekariya
 *
 */
public class Main_Activity extends Activity {
	/**
	 * TAG for logging.
	 */

    public static String SERVER_IP_ADDRESS = "192.0.2.10";
    public static final int SERVER_PORT = 8888;
	private static final String TAG = "Main_Activity";

	private SensorManagerSimulator mSensorManager;

	private EditText mEditTextIP;
	private EditText mEditTextSocket;

	// Indicators whether real device or sensor simulator is connected.
	private TextView mTextSensorType;

	private Button mButtonConnect;
	private Button mButtonDisconnect;

	private LinearLayout mSensorsList;

	ArrayList<String> mSupportedSensors = new ArrayList<String>();

	/**
	 * Number of supported sensors.
	 */
	int mNumSensors;

	/**
	 * The list of currently enabled sensors. (Needed to determine which of the
	 * sensors have to be updated regularly by new sensor data).
	 */
	boolean[] mSensorEnabled;

	/**
	 * Keep pointers to SingleSensorView so that we can update sensor data
	 * regularly.
	 */
	SingleSensorView[] mSingleSensorView;

	private Socket socket = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;


    double longitude=0.0, latitude=0.0;
    private String orientationData, accelerationData;
    private int deviceId;

    private EditText txt_deviceid, txt_ipaddress;
    private TextView txt_message, txt_location, txt_address;
    private String address;
    //private double oX,oY,oZ,aX,aY,aZ;
    /**
	 * Called when activity starts.
	 * 
	 * This can either be the first time, or the user navigates back after the
	 * activity has been killed.
	 * 
	 * We do not automatically reconnect to the SensorSimulator, as it may not
	 * be available in the mean-time anymore or the IP address may have changed.
	 * 
	 * (We do not know how long the activity had been dormant).
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);

		setContentView(R.layout.main_layout);

		// add as sensor manager our's sensor manager simulator
		mSensorManager = SensorManagerSimulator.getSystemService(this,
				SENSOR_SERVICE);


		Context context = this;
		// Get the Resources object from our context
		Resources res = context.getResources();



		mButtonConnect = (Button) findViewById(R.id.buttonconnect);
		mButtonConnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                startMonitor();
			}
		});

		mButtonDisconnect = (Button) findViewById(R.id.buttondisconnect);
		mButtonDisconnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                stopMonitor();
			}
		});

        txt_deviceid = (EditText) findViewById(R.id.device_id);
        txt_ipaddress = (EditText) findViewById(R.id.ip_address);

        txt_message = (TextView) findViewById(R.id.message);
        txt_location = (TextView) findViewById(R.id.location);
        txt_address = (TextView) findViewById(R.id.address);
        setButtonState();



		mTextSensorType = (TextView) findViewById(R.id.datatype);


		readAllSensors(); // Basic sensor information

		mSensorsList = (LinearLayout) findViewById(R.id.sensordatalist);

		fillSensorList(); // Fills the sensor list manually, giving us more
							// control

		// if we use simulation of GPS, here we register location manager for
		// the GPS
		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				mlocListener);


	}

	/**
	 * Inner class that represents our Location Listener. Each time we receive
	 * GPS input, Toast message is made and it contains variables which are send
	 * to the emulator. In order for Toast message to display, emulator should
	 * be connected to internet.
	 * 
	 * @author Josip Balic
	 */
	public class MyLocationListener implements LocationListener {

		/**
		 * Method that gets location changes. Once location is received, Toast
		 * message with Latitude, Longitude and Altitude is made. This is just
		 * an example how LocationListener should be used in applications.
		 */
		@Override
		public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
			latitude = location.getLatitude();


            txt_location.setText("Longitude = "
                    + location.getLongitude() + " Latitude = "
                    + location.getLatitude());

            address = getLocation();
            txt_address.setText(address);

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Called when activity comes to foreground. In onResume() method we
	 * register our sensors listeners, important - sensor listeners are not and
	 * should not be registered before, but in on resume method.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(listener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(listener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	/**
	 * Called when activity is stopped. Here we unregister all of currently
	 * existing listeners.
	 */
	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(listener);
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dataOutputStream != null){
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dataInputStream != null){
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		super.onStop();
	}

	/**
	 * Called when another activity is started.
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	/**
	 * Called when the user leaves. Here we store the IP address and port,
	 * unregister existing listeners and close the connection with sensor
	 * simulator.
	 */
	@Override
	protected void onPause() {
		super.onPause();

	}



	/**
	 * This method is used to connect our activity with sensor simulator. If we
	 * already have opened connection, than we unregister all the listeners,
	 * close existing connection and we create new connection.
	 */
	public void startMonitor() {
		Log.i(TAG, "Connect");
        if(txt_ipaddress.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(), "Please enter IP address to listen to sensor \n and connect to Network server", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(txt_deviceid.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(), "Please enter Device ID", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        deviceId = Integer.parseInt(txt_deviceid.getText().toString());
        SERVER_IP_ADDRESS = txt_ipaddress.getText().toString();
		if (!mSensorManager.isConnectedSimulator()) {
			Log.i(TAG, "Not connected yet -> Connect");
			mSensorManager.connectSimulator();
		}

		readAllSensors();

		setButtonState();

		if (mSensorManager.isConnectedSimulator()) {
			mTextSensorType.setText(R.string.sensor_simulator_data);
		} else {
			mTextSensorType.setText("");
		}

		fillSensorList();

        try{

            socket = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

	/**
	 * Method disconnect() is used to close existing connection with sensor
	 * simulator. When disconnect is called, all registered sensors are getting
	 * unregistered.
	 */
	public void stopMonitor() {
		mSensorManager.unregisterListener(listener);
		mSensorManager.disconnectSimulator();

		readAllSensors();

		setButtonState();

		if (mSensorManager.isConnectedSimulator()) {
			mTextSensorType.setText(R.string.sensor_simulator_data);
		} else {
			mTextSensorType.setText("");
		}

		mSensorsList.removeAllViews();
        txt_message.setText("");
     }

	/**
	 * This method is used to set button states. If we are connected with sensor
	 * simulator, than we can't click on button connect anymore and vice versa.
	 */
	public void setButtonState() {
		boolean connected = mSensorManager.isConnectedSimulator();
		mButtonConnect.setEnabled(!connected);
        txt_deviceid.setEnabled(!connected);
        txt_ipaddress.setEnabled(!connected);
        mButtonDisconnect.setEnabled(connected);

		mButtonConnect.invalidate();
		mButtonDisconnect.invalidate();
	}

	/**
	 * Reads information about which sensors are supported and what their rates
	 * and current rates are.
	 */
	public void readAllSensors() {

		ArrayList<Integer> sensors = mSensorManager.getSensors();

		if (sensors != null) {
			mSupportedSensors = SensorNames.getSensorNames(sensors);
		}

		// Now set values that are related to sensor updates:
		if (mSupportedSensors != null) {
			mNumSensors = mSupportedSensors.size();
		}
	}

	/**
	 * Our listener for this application. This listener holds all sensors we
	 * enable. If we are developing application which is using 2 or more
	 * sensors, it's advisable to use only one listener per one sensor.
	 */
	private SensorEventListener listener = new SensorEventListener() {

		/**
		 * onAccuracyChanged must be added, but it doesn't need any editing.
		 */
		@Override
		public void onAccuracyChanged(Sensor sensor, int acc) {
		}

		/**
		 * onSensorChanged method is used to write events of our enabled sensors
		 * in our application.
		 */
		@Override
		public void onSensorChanged(SensorEvent event) {
			int sensor = event.type;
			float[] values = event.values;

            try {

                for (int i = 0; i < mNumSensors; i++) {
				if ((mSingleSensorView[i].mSensorBit == sensor) && (sensor == Sensor.TYPE_ACCELEROMETER || sensor == Sensor.TYPE_ORIENTATION)) {


					// Update this view
					StringBuilder data = new StringBuilder("");
					int num = SensorNames.getNumSensorValues(sensor);

                    for (int j = 0; j < num; j++) {
                        if(j==0){
                            data.append(values[j]);
                        }else{
                            data.append(","+values[j]);
                        }
					}

					mSingleSensorView[i].mSensorDataTextView.setText(data.toString());
                    if(sensor == Sensor.TYPE_ORIENTATION){
                        orientationData = data.toString();
                    }else{
                        if(null != orientationData){//first time orientation data will be null
                            if(null == accelerationData || (!accelerationData.equals(data.toString()))){

                                dataOutputStream.writeUTF(deviceId+","+data.toString()+","+orientationData.toString()+","+longitude+","+latitude+","+address);
                                 txt_message.setText(dataInputStream.readUTF());
                            }
                            accelerationData = data.toString();
                        }
                    }
					break;
				}
			}




            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

	};

    private String getLocation(){
        String addressString = "";

       if(longitude==0.0 && latitude==0.0){
           return addressString;
       }
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = gc.getFromLocation(latitude, longitude, 1);
            StringBuilder sb = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);

                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                    sb.append(address.getAddressLine(i)).append(" ");

                sb.append(address.getLocality()).append(" ");
                sb.append(address.getPostalCode()).append(" ");
                sb.append(address.getCountryName());
            }
            addressString = sb.toString().replace(",","");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving address");
        }
        return addressString;
    }
	/**
	 * Fills the sensor list with currently active sensors.
	 */

	void fillSensorList() {
		if (mSupportedSensors != null) {
			mSensorsList.removeAllViews();

			// Now we fill the list, one by one:
			int max = mSupportedSensors.size();

			mSingleSensorView = new SingleSensorView[max];

			for (int i = 0; i < max; i++) {
				String[] sensorsNames = new String[mSupportedSensors.size()];
				ArrayList<Integer> sensorbit = SensorNames
						.getSensorsFromNames(mSupportedSensors
								.toArray(sensorsNames));
				SingleSensorView ssv = new SingleSensorView(this,
						mSupportedSensors.get(i), sensorbit.get(i), i);
				ssv.setLayoutParams(new LinearLayout.LayoutParams(
						android.view.ViewGroup.LayoutParams.FILL_PARENT,
						android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
				mSensorsList.addView(ssv, i);
				mSingleSensorView[i] = ssv;
			}
		}
	}

	/**
	 * Layout for displaying single sensor.
	 * 
	 * @author Peli
	 * @author Josip Balic
	 */
	private class SingleSensorView extends LinearLayout {

		@SuppressWarnings("unused")
		private TextView mTitle;

		@SuppressWarnings("unused")
		LinearLayout mL1;
		@SuppressWarnings("unused")
		LinearLayout mL1a;
		@SuppressWarnings("unused")
		LinearLayout mL1b;
		@SuppressWarnings("unused")
		LinearLayout mL1c;

		TextView mSensorNameTextView;
		TextView mSensorDataTextView;

		@SuppressWarnings("unused")
		Context mContext;

		@SuppressWarnings("unused")
		int mSensorId;
		String mSensor;
		int mSensorBit;

		/**
		 * Index of the default value in the list (spinner) for the sensor
		 * update rate. (-1 for no default index).
		 */
		@SuppressWarnings("unused")
		int mDefaultValueIndex;

		public SingleSensorView(Context context, String sensor, int sensorbit,
				int sensorId) {
			super(context);

			mContext = context;
			mSensorId = sensorId;
			mSensor = sensor;
			mSensorBit = sensorbit;
			mDefaultValueIndex = -1; // -1 means there is no default index.

			// Build child view from resource:
			LayoutInflater inf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View rowView = inf.inflate(R.layout.sensordatadisplay, null);

			// We set a tag, so that Handler can find this view
			rowView.setTag(mSensor);

			mSensorNameTextView = (TextView) rowView
					.findViewById(R.id.sensor_name);
			mSensorNameTextView.setText(sensor);
			mSensorDataTextView = (TextView) rowView
					.findViewById(R.id.sensor_data);

			addView(rowView, new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.FILL_PARENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

			mSensorManager.registerListener(listener,
					mSensorManager.getDefaultSensor(mSensorBit),
					SensorManager.SENSOR_DELAY_NORMAL); // TODO
		}
	}
}

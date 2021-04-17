package com.ryoga.kotlintest.sensor

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.R
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 * Use the [AccelerationSensorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


//加速度のFragment
class AccelerationSensorFragment : Fragment(), SensorEventListener {
    val handler = Handler()
    var count = 0
    var r: Runnable? = null


    private var mManager: SensorManager by Delegates.notNull<SensorManager>()
    private var mSensor: Sensor by Delegates.notNull<Sensor>()

    var x: Float = 0.0f
    var y: Float = 0.0f
    var z: Float = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_acceleration_sensor, container, false)
        //センサーマネージャーを取得する
        mManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager
//        //加速度計のセンサーを取得する
//        //その他のセンサーを取得する場合には引数を違うものに変更する
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        r = object : Runnable {

            override fun run() {
                // UIスレッド
                view.findViewById<TextView>(R.id.textView_x).setText(x.toString())
                view.findViewById<TextView>(R.id.textView_y).setText(z.toString())
                view.findViewById<TextView>(R.id.textView_z).setText(z.toString())


                handler.postDelayed(this, 300)


            }
        }

        handler.post(r)



        return view
    }

    override fun onResume() {
        super.onResume()
        mManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI)

    }

    //センサーに何かしらのイベントが発生したときに呼ばれる
    override fun onSensorChanged(event: SensorEvent?) {
        //3つの値が配列で入ってくる
        //X軸方法
        x = event?.values!![0]
        //Y軸方法
        y = event?.values!![1]
        //Z軸方法
        z = event?.values!![2]
    }

    //センサー精度が変更されたときに発生するイベント
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYE", "pause")
        mManager.unregisterListener(this)
        handler.removeCallbacks(r)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccelerationSensorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccelerationSensorFragment().apply {

            }
    }
}
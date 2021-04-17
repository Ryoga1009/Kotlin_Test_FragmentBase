package com.ryoga.kotlintest.sensor

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.RemoteException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.R
import org.altbeacon.beacon.*


/**
 * A simple [Fragment] subclass.
 * Use the [BleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BleFragment : Fragment(), BeaconConsumer {

    val handler = Handler()
    var count = 0
    var r: Runnable? = null

    var uuid = Identifier.parse("e7d61ea3-f8dd-49c8-8f2f-f2484c07acb9")
    var major = Identifier.parse("a")
    var minor = Identifier.parse("a")
    var rssi: String = "0"

    var beaconManager: BeaconManager? = null
    lateinit var mRegion: Region

    // ビーコンのフォーマット設定
    private val IBEACON_FORMAT: String = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRegion = Region("aaa", uuid, null, null)


        // ビーコンマネージャのインスタンスを生成
        beaconManager = BeaconManager.getInstanceForApplication(requireContext())

        // BeaconManagerの設定
        beaconManager!!.beaconParsers.add(BeaconParser().setBeaconLayout(IBEACON_FORMAT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_ble, container, false)

        var button_start = view.findViewById<Button>(R.id.button_ble_start)
        var button_stop = view.findViewById<Button>(R.id.button_ble_stop)
        button_start.setOnClickListener {

            button_start.isEnabled = false
            button_stop.isEnabled = true


            uuid = Identifier.parse(view.findViewById<EditText>(R.id.edittext_uuid).text.toString())
            major =
                Identifier.parse(view.findViewById<EditText>(R.id.edittext_major).text.toString())
            minor =
                Identifier.parse(view.findViewById<EditText>(R.id.edittext_minor).text.toString())



            try {

                beaconManager!!.foregroundScanPeriod = 500
                // ビーコン情報の監視を開始、第3,4引数はmajor・minor値を指定する時に使用
                beaconManager!!.startMonitoringBeaconsInRegion(mRegion)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }


        }

        button_stop.setOnClickListener {
            button_start.isEnabled = true
            button_stop.isEnabled = false



            try {
                beaconManager!!.stopMonitoringBeaconsInRegion(mRegion)
                beaconManager!!.stopRangingBeaconsInRegion(mRegion)
                beaconManager!!.unbind(this)
            } catch (e: Exception) {

            }


        }


        r = object : Runnable {

            override fun run() {
                // UIスレッド

                view.findViewById<TextView>(R.id.textView_ble).setText(rssi)

                handler.postDelayed(this, 300)


            }
        }

        handler.post(r)


        return view
    }


    override fun onResume() {
        super.onResume()

// ビーコンサービスの開始
        beaconManager!!.bind(this)
    }

    // onPause
    override fun onPause() {
        super.onPause()


        // ビーコンサービスの停止
        beaconManager!!.unbind(this)
    }


    /**************************************************
     * BeaconConsumer内のメソッドをoverride
     **************************************************/
    // onBeaconServiceConnect
    override fun onBeaconServiceConnect() {


        // モニタリングの通知受取り処理
        beaconManager!!.addMonitorNotifier(object : MonitorNotifier {

            // 領域内に侵入した時に呼ばれる
            override fun didEnterRegion(region: Region) {

                // レンジングの開始
                beaconManager!!.startRangingBeaconsInRegion(region)
            }

            // 領域外に退出した時に呼ばれる
            override fun didExitRegion(region: Region) {

                // レンジングの停止
                beaconManager!!.stopRangingBeaconsInRegion(region)
            }

            // 領域への侵入/退出のステータスが変化した時に呼ばれる
            override fun didDetermineStateForRegion(i: Int, region: Region) {
                //
            }
        })// レンジングの通知受け取り処理
        beaconManager!!.addRangeNotifier(object : RangeNotifier {

            // 範囲内のビーコン情報を受け取る
            override fun didRangeBeaconsInRegion(beacons: Collection<Beacon>, region: Region) {

                var maxMajor: Int?
                var maxMinor: Int?

                // 範囲内の複数のビーコン情報を保持させる変数
                var getMajorList: ArrayList<Int> = ArrayList()
                var getMinorList: ArrayList<Int> = ArrayList()
                var getRssiList: ArrayList<Int> = ArrayList()

                Log.d("MYE", beacons.size.toString())

                // 範囲内にビーコンがある時の処理
                if (beacons.size > 0) {

                    // 範囲内のビーコンの数だけ繰り返す
                    for (beacon in beacons) {
                        if (beacon.id2.equals(major) && beacon.id3.equals(minor)) {
                            rssi = beacon.rssi.toString()
                        }

                    }

                }
            }
        })
    }

    override fun getApplicationContext(): Context {
        return requireContext()
    }

    override fun unbindService(p0: ServiceConnection?) {
        TODO("Not yet implemented")
    }

    override fun bindService(p0: Intent?, p1: ServiceConnection?, p2: Int): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BleFragment().apply {

            }
    }
}
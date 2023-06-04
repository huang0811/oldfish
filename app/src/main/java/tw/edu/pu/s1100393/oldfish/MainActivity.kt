package tw.edu.pu.s1100393.oldfish

import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var txv: TextView
    lateinit var txv1: TextView
    lateinit var mper: MediaPlayer
    lateinit var btn: Button
    lateinit var btn0: Button
    lateinit var btn1: Button
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txv1 = findViewById(R.id.txv1)
        txv1.setTextColor(Color.parseColor("#FFD700"))
        txv1.setBackgroundColor(Color.GRAY)
        txv1.setTypeface(
            Typeface.createFromAsset(
                assets,
                "font/SentyGoldSand.ttf"
            )
        )
        txv1.getBackground().setAlpha(50)

        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#FFD700"))
        txv.setBackgroundColor(Color.GRAY)
        txv.setTypeface(
            Typeface.createFromAsset(
                assets,
                "font/SentyGoldSand.ttf"
            )
        )
        txv.getBackground().setAlpha(50)  //0~255透明度值

        mper = MediaPlayer()
        btn = findViewById(R.id.btn)
        btn.setOnClickListener(this)
        btn0 = findViewById(R.id.btn0)
        btn0.setOnClickListener(this)
        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {  // API 31以上
            val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {  //API < 31
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        if (event?.action == MotionEvent.ACTION_DOWN) {
            counter++
            txv.text = "功德+" + counter.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //API >= 26
                vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(5000)
            }
        }
        else if (event?.action == MotionEvent.ACTION_UP){
            vibrator.cancel()
        }
        return true
    }

    override fun onClick(v: View?) {
        mper.reset()
        when (v) {
            btn -> {
                mper = MediaPlayer.create(this, R.raw.fo)
                mper.start()
            }
            btn1 -> {
                mper = MediaPlayer.create(this, R.raw.fo)
                mper.pause()
            }
        }
        if (v == btn0){
            counter = 0
        }
        else{
            counter++
        }
        txv.text = "功德+" + counter.toString()
    }
}
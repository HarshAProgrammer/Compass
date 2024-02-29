package com.example.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(),SensorEventListener {

    var sensor:Sensor? = null
    var sensorManager: SensorManager? = null
    lateinit var compassImage: ImageView
    lateinit var rotationDegree:TextView

    //to keep track of rotation of the compass
    var currentDegree = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION)


        compassImage = findViewById<ImageView>(R.id.ivCompass)
        rotationDegree = findViewById<TextView>(R.id.tvCompass)





    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL)


    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        var degree = Math.round(event!!.values[0])
        rotationDegree.text = "$degree Degrees"
        var rotateAnimation = RotateAnimation(currentDegree,(-degree).toFloat(),Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotateAnimation.duration = 210
        rotateAnimation.fillAfter = true
        compassImage.startAnimation(rotateAnimation)
        currentDegree = (-degree).toFloat()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}
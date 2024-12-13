package com.example.plantro.ui.detail
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang dikirim dari InputFragment
        val nitrogen = intent.getIntExtra("nitrogen", 0)
        val phosphorus = intent.getIntExtra("phosphorus", 0)
        val potassium = intent.getIntExtra("potassium", 0)
        val temperature = intent.getFloatExtra("temperature", 0f)
        val humidity = intent.getFloatExtra("humidity", 0f)
        val pH_Value = intent.getFloatExtra("pH_Value", 0f)
        val rainfall = intent.getFloatExtra("rainfall", 0f)

        val soilQuality = intent.getStringExtra("soilQuality")
        val crop = intent.getStringExtra("predictions")
        val confidence = intent.getStringExtra("confidence")

        // Tampilkan data di UI
        findViewById<TextView>(R.id.nitrogenOutput).text = "$nitrogen"
        findViewById<TextView>(R.id.fosforOutput).text = "$phosphorus"
        findViewById<TextView>(R.id.kaliumOutput).text = "$potassium"
        findViewById<TextView>(R.id.suhuOutput).text = "$temperature"
        findViewById<TextView>(R.id.kelembapanOutput).text = "$humidity"
        findViewById<TextView>(R.id.phValueOutput).text = "$pH_Value"
        findViewById<TextView>(R.id.curahHujanOutput).text = "$rainfall"

        findViewById<TextView>(R.id.soilQualityOutput).text = "$soilQuality"
        findViewById<TextView>(R.id.predictionCropOutput).text = "$crop"
        findViewById<TextView>(R.id.confidenceOutput).text = "$confidence"


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_EVENT_ID = "event_id"
        const val EXTRA_EVENT_TITLE = "event_title"
        const val EXTRA_EVENT_DESCRIPTION = "event_description"
        const val EXTRA_EVENT_OWNER = "event_owner"
        const val EXTRA_EVENT_BEGIN_TIME = "event_begin_time"
        const val EXTRA_EVENT_QUOTA = "event_quota"
        const val EXTRA_EVENT_IMAGE = "event_image"
        const val EXTRA_EVENT_REGISTER_URL = "event_register_url"
        const val RESULT_FAVORITE_UPDATED = 1
    }
}


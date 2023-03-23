package com.example.opengraphexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.opengraphexample.databinding.ActivityMainBinding
import com.kedia.ogparser.OpenGraphCacheProvider
import com.kedia.ogparser.OpenGraphCallback
import com.kedia.ogparser.OpenGraphParser
import com.kedia.ogparser.OpenGraphResult

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private val url = "https://youtu.be/HztmSwvBu2U"
//    private val url = "https://www.google.com"
//    private val url = "https://www.linkedin.com/help/linkedin/answer/46687/making-your-website-shareable-on-linkedin?lang=en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val openGraphParser = OpenGraphParser(
            callback,
            showNullOnEmpty = true,
            cacheProvider = OpenGraphCacheProvider(this)
        )

        binding.btn.setOnClickListener {
            // To parse the link provided
            openGraphParser.parse(url)

            /*lifecycleScope.launch(
                Dispatchers.Default
            ) {
                val tags = URL(url).getOpenGraphTags()
                Log.d(TAG, "onCreate: Graph Tags: $tags")
            }*/
        }
    }

    private val callback: OpenGraphCallback = object : OpenGraphCallback {
        override fun onError(error: String) {
            Log.d(TAG, "onError: $error")
        }

        override fun onPostResponse(openGraphResult: OpenGraphResult) {
            Log.d(TAG, "onPostResponse: $openGraphResult")
        }
    }
}
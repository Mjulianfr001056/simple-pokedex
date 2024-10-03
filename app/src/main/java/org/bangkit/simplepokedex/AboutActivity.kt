package org.bangkit.simplepokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.bangkit.simplepokedex.databinding.ActivityAboutBinding
import org.bangkit.simplepokedex.databinding.ActivityDetailBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(R.drawable.user)
            .circleCrop()
            .into(binding.ivUser)
    }
}
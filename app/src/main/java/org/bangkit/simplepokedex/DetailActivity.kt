package org.bangkit.simplepokedex

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import org.bangkit.simplepokedex.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_POKEMON = "extra_pokemon"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemon = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_POKEMON, Pokemon::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_POKEMON)
        }

        if (pokemon != null) {
            Glide.with(this)
                .load(pokemon.photo)
                .into(binding.ivPokemon)
            binding.tvId.text = pokemon.id
            binding.tvName.text = pokemon.name
            binding.tvDescription.text = pokemon.description
            binding.statsPokemon.cHeight.text = pokemon.height
            binding.statsPokemon.cWeight.text = pokemon.weight
            binding.statsPokemon.cAbility.text = pokemon.ability
            binding.statsPokemon.cCategory.text = pokemon.category
//            binding.tvType.text = pokemon.type.joinToString(", ")
//            binding.tvWeakness.text = pokemon.weakness.joinToString(", ")
        }
    }
}
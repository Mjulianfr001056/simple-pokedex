package org.bangkit.simplepokedex

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginEnd
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
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

            val layoutParams = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(16, 0, 16, 0)

            pokemon.weakness.forEach {
                val badgeView = LayoutInflater.from(this@DetailActivity).inflate(R.layout.type_badge, null)
                badgeView.findViewById<TextView>(R.id.tv_type).text = it
                badgeView.layoutParams = layoutParams

                val color = getColorForType(it)
                badgeView.findViewById<TextView>(R.id.tv_type).setBackgroundColor(color)

                binding.weaknessBadgesContainer.addView(badgeView)
            }

            pokemon.type.forEach {
                val badgeView = LayoutInflater.from(this@DetailActivity).inflate(R.layout.type_badge, null)
                badgeView.findViewById<TextView>(R.id.tv_type).text = it
                badgeView.layoutParams = layoutParams

                val color = getColorForType(it)
                badgeView.findViewById<TextView>(R.id.tv_type).setBackgroundColor(color)

                binding.typeBadgesContainer.addView(badgeView)
            }

            with(binding){
                tvId.text = pokemon.id
                tvName.text = pokemon.name
                tvDescription.text = pokemon.description
                statsPokemon.cHeight.text = pokemon.height
                statsPokemon.cWeight.text = pokemon.weight
                statsPokemon.cAbility.text = pokemon.ability
                statsPokemon.cCategory.text = pokemon.category
            }
        }
    }

    private fun getColorForType(type: String): Int {
        Log.d("DetailActivity", "getColorForType:${type.lowercase()}")
        return when (type.lowercase()) {
            "normal" -> ContextCompat.getColor(this, R.color.normal_type)
            "fire" -> ContextCompat.getColor(this, R.color.fire_type)
            "water" -> ContextCompat.getColor(this, R.color.water_type)
            "electric" -> ContextCompat.getColor(this, R.color.electric_type)
            "grass" -> ContextCompat.getColor(this, R.color.grass_type)
            "ice" -> ContextCompat.getColor(this, R.color.ice_type)
            "fighting" -> ContextCompat.getColor(this, R.color.fighting_type)
            "poison" -> ContextCompat.getColor(this, R.color.poison_type)
            "ground" -> ContextCompat.getColor(this, R.color.ground_type)
            "flying" -> ContextCompat.getColor(this, R.color.flying_type)
            "psychic" -> ContextCompat.getColor(this, R.color.psychic_type)
            "bug" -> ContextCompat.getColor(this, R.color.bug_type)
            "rock" -> ContextCompat.getColor(this, R.color.rock_type)
            "ghost" -> ContextCompat.getColor(this, R.color.ghost_type)
            "dragon" -> ContextCompat.getColor(this, R.color.dragon_type)
            "dark" -> ContextCompat.getColor(this, R.color.dark_type)
            "steel" -> ContextCompat.getColor(this, R.color.steel_type)
            "fairy" -> ContextCompat.getColor(this, R.color.fairy_type)
            else -> ContextCompat.getColor(this, R.color.black)
        }
    }


}
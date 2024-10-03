package org.bangkit.simplepokedex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.bangkit.simplepokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Pokemon>()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: view created")

        list.addAll(getListPokemon())
        showRecyclerList()
    }

    private fun getListPokemon(): ArrayList<Pokemon> {
        val dataId = resources.getStringArray(R.array.data_id)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataHeight = resources.getStringArray(R.array.data_height)
        val dataWeight = resources.getStringArray(R.array.data_weight)
        val dataType = resources.getStringArray(R.array.data_type)
        val dataWeakness = resources.getStringArray(R.array.data_weakness)
        val dataAbility = resources.getStringArray(R.array.data_ability)
        val dataCategory = resources.getStringArray(R.array.data_category)

        val listPokemon = ArrayList<Pokemon>()
        for (i in dataName.indices) {
            val pokemon = Pokemon(
                dataId[i],
                dataName[i],
                dataDescription[i],
                dataPhoto[i],
                dataHeight[i],
                dataWeight[i],
                dataAbility[i],
                dataCategory[i],
                dataType[i].split(","),
                dataWeakness[i].split(",")
            )
            listPokemon.add(pokemon)
        }
        return listPokemon
    }

    private fun showRecyclerList() {
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        val listPokemonAdapter = ListPokemonAdapter(list) {
            Log.d(TAG, "showRecyclerList: item clicked")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_POKEMON, it)
            startActivity(intent)
        }
        binding.rvPokemon.adapter = listPokemonAdapter
    }
}
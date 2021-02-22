package com.baish.progressocenka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.Observer
import com.baish.progressocenka.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

const val TOTAL_TIME = 100_000L
const val STEP_TIME = 1000L


class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    private val adapter by lazy { ApryselAdapter(){
        pos, last ->
        vm.updateRateAdapter(pos,last)
    } }

    private val vm by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showProgress.progress = 50
        // чтоб линия proggress(процент загрузки пример) выходило , объязательно ставаить в xml внутри <ProggressBar -> style="?android:attr/progressBarStyleHorizontal"
        timer.start()
        setupRecycler()
        vm.listData.observe(this, Observer {
            adapter.submitList(it)
        })
    }


    private fun setupRecycler() {
        binding.recyclerAprisyle.adapter = adapter

    }

    val timer = object  : CountDownTimer(TOTAL_TIME, STEP_TIME){

        override fun onTick(millisUntilFinished: Long) {

            val percent : Int = 100 - (millisUntilFinished/ 1000).toInt()

            binding.showProgress.progress = percent
        }

        override fun onFinish() {
           binding.showProgress.progress = 100
        }

    }
}
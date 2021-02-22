package com.baish.progressocenka

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val listData = MutableLiveData<ArrayList<RateData>>()
    var listSecond = ArrayList<RateData>()

    init {
        generateData()
    }

    fun generateData() {
        val list = arrayListOf<RateData>()
        for (i in 1..5) {
            list.add(RateData(rate = i))
        }
        this.listSecond.addAll(list)
        listData.postValue(list)

    }

    fun updateRateAdapter(pos: Int, last: Int) {
        val localList = ArrayList<RateData>()
        localList.addAll(listSecond)
        if (last >= 0) {  // не равен private var lastSelectedPos = -1
            val item = localList[last].copy()
            item.isChoised = false
            localList[last] = item
        }
        val item = localList[pos].copy()
        item.isChoised = true
        localList[pos] = item
        listData.postValue(localList)
    }

}

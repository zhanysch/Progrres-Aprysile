package com.baish.progressocenka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baish.progressocenka.databinding.ItemAprasyleBinding

class ApryselAdapter(private val listener:(pos : Int, last : Int) -> Unit):ListAdapter<RateData,ApryselViewHolder>(
    diffUtils) {
                            // what for 2 value???  pos : Int, last : Int

    private var lastSelectedPos = -1  // на старте никакя кнопка не выбрана

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApryselViewHolder {
        return ApryselViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ApryselViewHolder, position: Int) {
       holder.bind(getItem(position))
                                                  /// holder.itemView.setOnClick ????
        holder.itemView.setOnClickListener {
            listener.invoke(position,lastSelectedPos)
            lastSelectedPos = position                    // что значит??????
        }
    }


    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<RateData>(){
            override fun areItemsTheSame(oldItem: RateData, newItem: RateData): Boolean {
                return oldItem.isChoised == newItem.isChoised &&
                        oldItem.rate == newItem.rate
            }

            override fun areContentsTheSame(oldItem: RateData, newItem: RateData): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class ApryselViewHolder(private val binding:  ItemAprasyleBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(rateData: RateData) {
         binding.tvTitle.text = rateData.rate.toString()
        binding.root.isSelected = rateData.isChoised

    }

    companion object {
        fun create(parent: ViewGroup) : ApryselViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aprasyle,parent,false)
            val binding = ItemAprasyleBinding.bind(view)
            return ApryselViewHolder(binding)
        }
    }
}

data class RateData(
    val rate : Int,
    var isChoised : Boolean = false /// false просто значение по умолчанию
)
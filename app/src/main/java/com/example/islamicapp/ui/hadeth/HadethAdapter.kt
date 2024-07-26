package com.example.islamicapp.ui.hadeth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islamicapp.databinding.ItemHadethBinding

class HadethAdapter(val hadethList: List<HadethModel>) : Adapter<HadethAdapter.HadethViewHolder>() {

    class HadethViewHolder(val binding: ItemHadethBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethViewHolder {
        val binding = ItemHadethBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HadethViewHolder(binding)
    }

    override fun getItemCount(): Int = hadethList.size


    override fun onBindViewHolder(holder: HadethViewHolder, position: Int) {
        holder.binding.tvHadethTitle.text = hadethList[position].title
    }


}
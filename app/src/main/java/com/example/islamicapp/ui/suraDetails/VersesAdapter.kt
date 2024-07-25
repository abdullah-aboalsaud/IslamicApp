package com.example.islamicapp.ui.suraDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.islamicapp.databinding.ItemVerseBinding

class VersesAdapter(val versesList:List<String>): Adapter<VersesAdapter.VersesViwHolder>() {

    class VersesViwHolder(val binding: ItemVerseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(verse: String,position: Int) {
            binding.tvContent.text = "$verse (${position+1})"
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersesViwHolder {
        val binding = ItemVerseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VersesViwHolder(binding)
    }

    override fun getItemCount(): Int = versesList.size

    override fun onBindViewHolder(holder: VersesViwHolder, position: Int) {
       val verse = versesList[position]
        holder.bind(verse,position)
    }
}
package com.example.islamicapp.ui.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islamicapp.databinding.ItemSurasBinding

class SurasAdapter(val chapters: List<String>) : Adapter<SurasAdapter.ChaptersViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    class ChaptersViewHolder(val binding: ItemSurasBinding) :
        ViewHolder(binding.root) {

        fun bind(chapter: String) {
            binding.tvSoraName.text = chapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaptersViewHolder {
        val binding = ItemSurasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChaptersViewHolder(binding)
    }

    override fun getItemCount() = chapters.size

    override fun onBindViewHolder(holder: ChaptersViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.bind(chapter)

        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,chapter)
            }
        }

    }

    fun interface OnItemClickListener {
        fun onItemClick(position: Int, title: String)
    }

}
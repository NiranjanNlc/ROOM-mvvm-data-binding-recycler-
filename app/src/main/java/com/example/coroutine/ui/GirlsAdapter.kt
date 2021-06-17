package com.example.coroutine.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutine.databinding.RecyclerviewItemBinding
import com.example.coroutine.modal.data.Girls

class GirlsAdapter : ListAdapter<Girls, GirlsAdapter.GirlsViewHolder>(GIRLS_COMPARATOR)
{

    class GirlsViewHolder (var binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
       val GIRLS_COMPARATOR = object : DiffUtil.ItemCallback<Girls>() {
            override fun areItemsTheSame(oldItem: Girls, newItem: Girls): Boolean {
                println(" item same ")
                return oldItem == newItem;
            }

            override fun areContentsTheSame(oldItem: Girls, newItem: Girls): Boolean {
                print(" Content same ")
                return oldItem.name.equals(newItem.name)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlsViewHolder
    {
        println("On view create ")
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater)
        return GirlsViewHolder(binding)
     }

    override fun onBindViewHolder(holder: GirlsViewHolder, position: Int)
    {
        println("On view bind"+getItem(position+1).name)
        holder.binding.girls = getItem(position)
        holder.binding.executePendingBindings()
    }

}
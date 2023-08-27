package com.otta.sneakerslist.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otta.common.domain.model.Sneaker
import com.otta.sneakerslist.databinding.RecyclerviewItemSneakerBinding

class SneakersListAdapter(
    private var sneakersList: List<Sneaker> = emptyList(),
    private var onSneakerSelect: (Sneaker) -> Unit
) : RecyclerView.Adapter<SneakersListAdapter.ItemViewHolder>() {

    fun setList(sneakersList: List<Sneaker>) {
        this.sneakersList = sneakersList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val dataBinding = RecyclerviewItemSneakerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            sneaker = sneakersList[position],
            onSneakerSelect = onSneakerSelect
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return sneakersList.size
    }

    inner class ItemViewHolder(_binding: RecyclerviewItemSneakerBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding = _binding
        fun bind(sneaker: Sneaker, onSneakerSelect: (Sneaker) -> Unit) {

            binding.sneakerName.text = sneaker.name
            binding.sneakerPrice.text = "$ ${sneaker.retailPrice}"

            binding.sneakerName.isSelected = true
            binding.root.setOnClickListener {
                onSneakerSelect(sneaker)
            }
        }
    }
}
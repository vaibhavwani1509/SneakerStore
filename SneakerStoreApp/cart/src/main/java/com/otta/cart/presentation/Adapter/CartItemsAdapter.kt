package com.otta.sneakerslist.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otta.cart.databinding.RecyclerviewCartItemBinding
import com.otta.common.domain.model.Sneaker

class CartItemsAdapter(
    private var cartItemsList: List<Sneaker> = emptyList(),
    private var onRemoveClick: (Sneaker) -> Unit
) : RecyclerView.Adapter<CartItemsAdapter.ItemViewHolder>() {

    fun setList(cartItemsList: List<Sneaker>) {
        this.cartItemsList = cartItemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val dataBinding = RecyclerviewCartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            sneaker = cartItemsList[position],
            onRemoveClick = onRemoveClick
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return cartItemsList.size
    }

    inner class ItemViewHolder(_binding: RecyclerviewCartItemBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding = _binding
        fun bind(sneaker: Sneaker, onRemoveClick: (Sneaker) -> Unit) {
            binding.sneakerName.text = sneaker.name
            binding.sneakerPrice.text = "$ ${sneaker.retailPrice}"
            binding.removeFromCartButton.setOnClickListener {
                onRemoveClick(sneaker)
            }
        }
    }
}
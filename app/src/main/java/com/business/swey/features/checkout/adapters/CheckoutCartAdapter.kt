package com.business.swey.features.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.CartItemDto
import com.business.swey.core.utils.Enum
import com.business.swey.databinding.ItemCartBinding
import com.business.swey.features.checkout.fragments.CheckoutCartFragment.CartType

class CheckoutCartAdapter : ListAdapter<CartItemDto, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    private var adapterType = CartType.MY_CART
    private var callback : ((Int, CartItemDto)-> Unit) ?= null

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CartItemDto -> Enum.CartItemType.CART_ITEM.value
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Enum.CartItemType.CART_ITEM.value -> CartItemViewHolder(
                ItemCartBinding.inflate(LayoutInflater.from(parent.context))
            )

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Enum.CartItemType.CART_ITEM.value -> {
                val viewHolder = holder as? CartItemViewHolder?
                viewHolder?.bind(
                    getItem(position),
                    adapterType,
                    callback
                )
            }
        }
    }

    fun setAdapterCartType(cartType: CartType, callback: (Int, CartItemDto) -> Unit) {
        adapterType = cartType
        this.callback = callback
    }

    fun removeItem(position: Int) {
        if (position < itemCount) {
            val newList = currentList.toMutableList()
            newList.removeAt(position)
            submitList(newList)
        }
    }

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class CartItemViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CartItemDto, cartType: CartType, onItemAction: ((Int, CartItemDto) -> Unit)?) {
            binding.item = data

            binding.btnMinus.setOnClickListener {
                if (data.count > 1) {
                    data.count -= 1
                    notifyItemChanged(adapterPosition)
                }
            }

            binding.btnPlus.setOnClickListener {
                data.count += 1
                notifyItemChanged(adapterPosition)
            }

            when(cartType){
                CartType.MY_CART -> {
                    binding.apply {
                        btnRemove.isVisible = true
                        checkBoxAddCart.isVisible = false
                        btnRemove.setOnClickListener {
                            onItemAction?.invoke(adapterPosition, data)
                        }
                    }
                }
                CartType.FRIENDS_CART -> {
                    binding.apply {
                        btnRemove.isVisible = false
                        checkBoxAddCart.isVisible = true
                        root.setOnClickListener {
                            onItemAction?.invoke(adapterPosition, data)
                            checkBoxAddCart.isChecked = !checkBoxAddCart.isChecked
                        }
                        checkBoxAddCart.setOnCheckedChangeListener { buttonView, isChecked ->
                            if (isChecked)
                                onItemAction?.invoke(adapterPosition, data)
                        }
                    }
                }
            }
        }
    }
}

class ChatDiffCallback : DiffUtil.ItemCallback<CartItemDto>() {
    override fun areItemsTheSame(oldItem: CartItemDto, newItem: CartItemDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CartItemDto, newItem: CartItemDto): Boolean {
        return oldItem.id == newItem.id
    }
}

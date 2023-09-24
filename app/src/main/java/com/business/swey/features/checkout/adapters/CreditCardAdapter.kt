package com.business.swey.features.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.R
import com.business.swey.core.models.CreditCardDto
import com.business.swey.core.utils.Enum
import com.business.swey.databinding.ItemChooseCardBinding

class CreditCardAdapter: ListAdapter<CreditCardDto, RecyclerView.ViewHolder>(CardsDiffCallback()) {

    private var selectedCard: Int? = null
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CreditCardDto -> Enum.CreditCardItemType.CARD.value
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Enum.CreditCardItemType.CARD.value -> CreditCartItemViewHolder(
                ItemChooseCardBinding.inflate(LayoutInflater.from(parent.context))
            )

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Enum.CreditCardItemType.CARD.value -> {
                val viewHolder = holder as? CreditCartItemViewHolder?
                viewHolder?.bind(
                    getItem(position),
                    ::onItemSelected
                )
            }
        }
    }

    private fun onItemSelected(index: Int, data: CreditCardDto){
        if (selectedCard == index) {
            selectedCard = null
        } else {
            val previousSelected = selectedCard
            selectedCard = null
            previousSelected?.let { notifyItemChanged(previousSelected) }
            selectedCard = index
        }
        notifyItemChanged(index)
    }

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class CreditCartItemViewHolder(private val binding: ItemChooseCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CreditCardDto, onItemSelected: (Int, CreditCardDto) -> Unit) {
            binding.tvCardTitle.text = "Chase Sapphire ${data.network}"
            binding.tvCardDigits.text = "••••${data.lastFourDigits}"
            binding.tvCardCredit.text = "$${data.amount} Available"
            binding.parent.setOnClickListener {
                onItemSelected.invoke(adapterPosition, data)
            }
            if (selectedCard == adapterPosition) {
                binding.ivCheck.setImageResource(R.drawable.ic_filled_mark)
                binding.parent.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_credit_card_border_checked)
            } else {
                binding.ivCheck.setImageResource(R.drawable.ic_unfilled_mark)
                binding.parent.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_credit_card_border_unchecked)
            }
        }
    }
}

class CardsDiffCallback : DiffUtil.ItemCallback<CreditCardDto>() {
    override fun areItemsTheSame(oldItem: CreditCardDto, newItem: CreditCardDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CreditCardDto, newItem: CreditCardDto): Boolean {
        return oldItem.id == newItem.id
    }
}

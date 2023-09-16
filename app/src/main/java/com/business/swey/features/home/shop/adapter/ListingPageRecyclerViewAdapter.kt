package com.business.swey.features.home.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.business.swey.databinding.NormalInvertedProductSectionItemBinding
import com.business.swey.databinding.NormalProductSectionItemBinding
import com.business.swey.databinding.VerticalProductSectionItemBinding
import com.business.swey.core.models.ListingPageProductDTO
import com.business.swey.core.models.ListingPageProductSectionDTO
import com.business.swey.core.utils.Enum


class ListingPageRecyclerViewAdapter(itemsDataSource: ArrayList<ListingPageProductSectionDTO>, itemLongClickListener: ((product: ListingPageProductDTO) -> Unit)?, itemClickListener: ((product: ListingPageProductDTO) -> Unit)?): Adapter<RecyclerView.ViewHolder>() {

    private var mItemsDataSource = itemsDataSource
    private var mProductLongClickListener = itemLongClickListener
    private var mProductClickListener = itemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Enum.ListingPageSectionType.verticalProductSection.value -> {
                val itemViewBinding = VerticalProductSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListingPageItemVerticalViewHolder(itemViewBinding)
            }
            Enum.ListingPageSectionType.normalProductSection.value -> {
                val itemViewBinding = NormalProductSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListingPageItemNormalViewHolder(itemViewBinding)
            }
            Enum.ListingPageSectionType.normalInvertedProductSection.value -> {
                val itemViewBinding = NormalInvertedProductSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListingPageItemNormalInvertedViewHolder(itemViewBinding)
            }
            Enum.ListingPageSectionType.horizontalProductSection.value -> {

            }
            Enum.ListingPageSectionType.categoriesSection.value -> {

            }
        }
        val itemViewBinding = NormalProductSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingPageItemNormalViewHolder(itemViewBinding)
    }

    override fun getItemViewType(position: Int): Int {
        val itemData = mItemsDataSource[position]
        return itemData.sectionType.value
    }

    override fun getItemCount(): Int {
        return mItemsDataSource.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = mItemsDataSource[position]
        if (holder is ListingPageItemNormalViewHolder) {
            holder.itemViewBinding.firstImage.setImageResource(itemData.products[0].imageResId)
            holder.itemViewBinding.secondProduct.setImageResource(itemData.products[1].imageResId)
            holder.itemViewBinding.firstImage.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[0])
                true
            }
            holder.itemViewBinding.firstImage.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[0])
            }
            holder.itemViewBinding.secondProduct.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[1])
                true
            }
            holder.itemViewBinding.secondProduct.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[1])
            }
        }
        else if (holder is ListingPageItemNormalInvertedViewHolder) {
            holder.itemViewBinding.firstImage.setImageResource(itemData.products[0].imageResId)
            holder.itemViewBinding.secondProduct.setImageResource(itemData.products[1].imageResId)
            holder.itemViewBinding.firstImage.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[0])
                true
            }
            holder.itemViewBinding.firstImage.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[0])
            }
            holder.itemViewBinding.secondProduct.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[1])
                true
            }
            holder.itemViewBinding.secondProduct.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[1])
            }
        }
        else if (holder is ListingPageItemVerticalViewHolder) {
            holder.itemViewBinding.firstVerticalImage.setImageResource(itemData.products[0].imageResId)
            holder.itemViewBinding.secondVerticalImage.setImageResource(itemData.products[1].imageResId)
            holder.itemViewBinding.verticalProductImage.setImageResource(itemData.products[2].imageResId)
            holder.itemViewBinding.firstVerticalImage.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[0])
                true
            }
            holder.itemViewBinding.firstVerticalImage.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[0])
            }
            holder.itemViewBinding.secondVerticalImage.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[1])
                true
            }
            holder.itemViewBinding.secondVerticalImage.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[1])
            }
            holder.itemViewBinding.verticalProductImage.setOnLongClickListener {
                mProductLongClickListener?.invoke(itemData.products[2])
                true
            }
            holder.itemViewBinding.verticalProductImage.setOnClickListener {
                mProductClickListener?.invoke(itemData.products[2])
            }
        }
    }

    inner class ListingPageItemNormalViewHolder(val itemViewBinding: NormalProductSectionItemBinding): RecyclerView.ViewHolder(itemViewBinding.root) {

    }

    inner class ListingPageItemNormalInvertedViewHolder(val itemViewBinding: NormalInvertedProductSectionItemBinding): RecyclerView.ViewHolder(itemViewBinding.root) {

    }

    inner class ListingPageItemVerticalViewHolder(val itemViewBinding: VerticalProductSectionItemBinding): RecyclerView.ViewHolder(itemViewBinding.root) {

    }
}
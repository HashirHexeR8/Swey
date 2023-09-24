package com.business.swey.features.checkout.fragments

import android.os.Bundle
import androidx.core.view.isVisible
import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.core.models.CartItemDto
import com.business.swey.core.models.ProductDto
import com.business.swey.databinding.FragmentCheckoutCartBinding
import com.business.swey.features.checkout.adapters.CheckoutCartAdapter

class CheckoutCartFragment : FullScreenDialogBindingFragment<FragmentCheckoutCartBinding>() {

    private val itemsList: ArrayList<CartItemDto> = getDate()

    private val adapter = CheckoutCartAdapter()

    override fun getLayout() = R.layout.fragment_checkout_cart

    override fun initViews(binding: FragmentCheckoutCartBinding) {
        (arguments?.getSerializable(TYPE) as? CartType)?.let {
            when (it){
                    CartType.MY_CART -> {
                        adapter.setAdapterCartType(it, ::onItemRemoved)
                        binding.tvName.text = "Total Cart"
                        binding.layoutCheckoutForAddCart.isVisible = false
                        binding.btnCheckoutBottom.isVisible = true
                        binding.tvBarTitle.text = "Go Shopping"
                    }
                    CartType.FRIENDS_CART -> {
                        adapter.setAdapterCartType(it, ::onItemChecked)
                        binding.tvName.text = "Marcia's Cart"
                        binding.layoutCheckoutForAddCart.isVisible = true
                        binding.btnCheckoutBottom.isVisible = false
                        binding.tvBarTitle.text = "Chats"
                    }
            }
        }
        adapter.submitList(getDate())
        binding.recyclerView.adapter = adapter
        binding.layoutBadge.count = adapter.currentList.size.toString()
    }

    override fun setListeners(binding: FragmentCheckoutCartBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }

        binding.btnCheckoutFloat.setOnClickListener {
            openDialogSheet(PaymentDetailsFragment.getInstance(), PaymentDetailsFragment.TAG)
        }

        binding.btnCheckout.setOnClickListener {
            openDialogSheet(PaymentDetailsFragment.getInstance(), PaymentDetailsFragment.TAG)
        }

        binding.btnCheckoutBottom.setOnClickListener {
            openDialogSheet(PaymentDetailsFragment.getInstance(), PaymentDetailsFragment.TAG)
        }
    }

    private fun onItemRemoved(index: Int, itemDto: CartItemDto) {
        adapter.removeItem(index)
        itemsList.removeAt(index)
        _binding.layoutBadge.count = itemsList.size.toString()
    }

    private fun onItemChecked(index: Int, cartItemDto: CartItemDto) {

    }

    private fun getDate(): ArrayList<CartItemDto> {
        return arrayListOf(
            CartItemDto(id = 1, count = 1, ProductDto("https://cld.accentuate.io/5353320710301/1663094662150/Madrid_Heathered-Grey_Feature-Float-1380.png?v=1669223444554&options=w1000")),
            CartItemDto(id = 2, count = 2, ProductDto("https://cdn.thewirecutter.com/wp-content/media/2021/06/watershoes-2048px-5505-nocap.jpg?auto=webp&quality=75&width=1024")),
            CartItemDto(id = 3, count = 3, ProductDto("https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/d455e5b6-23e9-4f06-8b86-32610d838937/renew-run-3-road-running-shoes-395S7c.png")),
            CartItemDto(id = 4, count = 4, ProductDto("https://www.jiomart.com/images/product/original/rvnwu5aqz8/bruton-trendy-sports-shoes-for-men-blue-product-images-rvnwu5aqz8-0-202209021248.jpg?im=Resize=(330,410)")),
            CartItemDto(id = 5, count = 5, ProductDto("https://images.unsplash.com/photo-1542291026-7eec264c27ff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c2hvZXN8ZW58MHx8MHx8fDA%3D&w=1000&q=80")),
        )
    }


    companion object {
        const val TAG = "CheckoutCartFragment"
        fun getInstance(cartType: CartType = CartType.MY_CART): CheckoutCartFragment = CheckoutCartFragment().apply {
            val bundle = Bundle()
            bundle.putSerializable(TYPE, cartType)
            arguments = bundle
        }
        private const val TYPE = "cart_type"
    }
    enum class CartType{
        MY_CART,
        FRIENDS_CART
    }
}
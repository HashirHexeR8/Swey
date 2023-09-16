package com.business.swey.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BindingFragment<DB : ViewDataBinding> :
    Fragment() {

    private lateinit var _binding: DB

    @LayoutRes
    abstract fun getLayout(): Int
    abstract fun initViews(binding: DB)
    abstract fun setListeners(binding: DB)
    open fun setObservers() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayout(), null, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(_binding)
        setListeners(_binding)
        setObservers()
    }

    fun <DB : ViewDataBinding> openDialogSheet(
        sheet: FullScreenDialogBindingFragment<DB>,
        tag: String
    ) {
        sheet.show(childFragmentManager, tag)
    }
}

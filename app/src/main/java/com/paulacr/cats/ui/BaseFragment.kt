package com.paulacr.cats.ui

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM : ViewModel, B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B
    protected var TAG = this.javaClass.canonicalName
    abstract val viewModel: VM
}

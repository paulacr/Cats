package com.paulacr.cats.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.paulacr.cats.R
import com.paulacr.cats.databinding.FragmentCatsListBinding
import com.paulacr.cats.ui.BaseFragment

class CatsListFragment : BaseFragment<CatsListViewModel, FragmentCatsListBinding>() {

    override val viewModel: CatsListViewModel
        get() = CatsListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cats_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        Log.i("Current Fragment ", fragmentName)
    }
}
package com.paulacr.cats.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.paulacr.cats.R
import com.paulacr.cats.databinding.FragmentCatsListBinding
import com.paulacr.cats.ui.CatsApplication
import javax.inject.Inject

class CatsListFragment : Fragment() {

    @Inject
    lateinit var viewModel: CatsListViewModel
    private lateinit var binding: FragmentCatsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as CatsApplication).appComponent.inject(this)
    }

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
        Log.i("Log Current viewmodel ", viewModel.diTest)
    }
}
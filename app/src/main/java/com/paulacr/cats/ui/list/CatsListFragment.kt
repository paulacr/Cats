package com.paulacr.cats.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulacr.cats.R
import com.paulacr.cats.data.model.Cat
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
        setupObservers()
        viewModel.getCatsList()
    }

    private fun setupObservers() {
        viewModel.catsListLiveData.observe(viewLifecycleOwner, Observer {
            setupCatsList(it)
        })
    }

    private fun setupCatsList(catsList: List<Cat>) {
        val adapter = CatAdapter(catsList, onCatClicked())
        binding.rvCatsList.layoutManager = LinearLayoutManager(context)
        binding.rvCatsList.adapter = adapter
    }

    private fun onCatClicked(): (Int, Cat) -> Unit =
        { position: Int, cat: Cat ->
        }
}

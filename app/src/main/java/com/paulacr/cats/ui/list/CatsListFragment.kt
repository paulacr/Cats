package com.paulacr.cats.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as CatsApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        view.setContent {
            ListOfCats(viewModel)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

@Composable
private fun ListOfCats(viewModel: CatsListViewModel) {
    val dataExample by viewModel.getRandomCat().subscribeAsState(initial = null)
    if (dataExample != null) {
        AsyncImage(
            model = dataExample!!.url,
            contentDescription = null
        )
    }
}




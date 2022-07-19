package com.paulacr.cats.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.paulacr.cats.data.model.Cat
import com.paulacr.cats.ui.CatsApplication
import com.paulacr.cats.ui.ViewState
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
            val dataExample by viewModel.getCatsList().subscribeAsState(initial = ViewState.Loading)
            ListOfCats(dataExample)
        }
        return view
    }

    private fun onCatClicked(): (Int, Cat) -> Unit =
        { position: Int, cat: Cat ->
        }
}


@Composable
private fun ListOfCats(dataExample: ViewState) {

    if (dataExample is ViewState.Success) {
        LazyColumn {
            items ((dataExample as ViewState.Success).data.size) { index ->
                Text(text = "item $index ${(dataExample as ViewState.Success).data[index].id}")
            }

        }
    }

//    when(dataExample) {
//        is ViewState.Success<*> -> { data: List<Cat> ->
//            LazyColumn {
//
//
//                items (5) { index ->
//                    Text(text = "item $index $data")
//                }
//
//            }
//
//        }
//        else -> {
//            Text(text = "error ")
//        }
    }

//    if (dataExample != null) {
//        AsyncImage(
//            model = dataExample!!.url,
//            contentDescription = null
//        )
//    }
//}




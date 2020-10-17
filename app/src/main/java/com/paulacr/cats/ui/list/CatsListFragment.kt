package com.paulacr.cats.ui.list

import com.paulacr.cats.databinding.FragmentCatsListBinding
import com.paulacr.cats.ui.BaseFragment

class CatsListFragment : BaseFragment<CatsListViewModel, FragmentCatsListBinding>() {

    override val viewModel: CatsListViewModel
        get() = CatsListViewModel()
}
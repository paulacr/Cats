package com.paulacr.cats.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.cats.data.model.Cat
import com.paulacr.cats.databinding.CatViewBinding
import com.paulacr.cats.ui.BaseViewHolder

class CatAdapter(private val catsList: List<Cat>, listener: (Int, Cat) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CatViewBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as CatViewHolder).bind(catsList[position])
    }

    class CatViewHolder(private val binding: CatViewBinding) : BaseViewHolder<Cat>(binding) {

        override fun bind(item: Cat) {
            binding.cat = item
            binding.executePendingBindings()
        }
    }
}

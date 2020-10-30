package com.paulacr.cats.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.cats.R
import com.paulacr.cats.data.model.CatImage
import com.paulacr.cats.ui.BaseViewHolder
import com.squareup.picasso.Picasso

class CatAdapter(private val catsList: List<CatImage>, listener: (Int, CatImage) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_view, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as CatViewHolder).bind(catsList[position])
    }

    class CatViewHolder(itemView: View) : BaseViewHolder<CatImage>(itemView) {

        private val catImage: ImageView = itemView.findViewById(R.id.catImage)

        override fun bind(item: CatImage) {
            Picasso.with(itemView.context)
                .load(item.url)
                .placeholder(R.mipmap.ic_launcher)
                .into(catImage)
        }
    }
}

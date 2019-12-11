package com.example.databindingrecycler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingrecycler.databinding.RecyclerItemCatModelBinding

class FragmentRecyclerCat : Fragment() {

    private lateinit var imageResourceIdSet: IntArray
    private lateinit var nameSet: Array<String>
    private lateinit var descriptionSet: Array<String>
    private lateinit var urlSet: Array<String>
    private lateinit var listener: CatSelected

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CatSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement CatSelected.")
        }

        initDataSet(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cat_recycler_view, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = CatRecyclerAdapter(activity)

        return view
    }

    private fun initDataSet(context: Context) {
        val resources = context.resources
        nameSet = resources.getStringArray(R.array.names)
        descriptionSet = resources.getStringArray(R.array.descriptions)
        urlSet = resources.getStringArray(R.array.urls)

        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = nameSet.size
        imageResourceIdSet = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResourceIdSet[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    companion object {
        fun newInstance(): FragmentRecyclerCat {
            return FragmentRecyclerCat()
        }
    }

    internal inner class CatRecyclerAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerCatModelBinding =
                RecyclerItemCatModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerCatModelBinding.root, recyclerCatModelBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val cat = CatModel(
                imageResourceIdSet[position],
                nameSet[position],
                descriptionSet[position],
                urlSet[position]
            )
            viewHolder.setData(cat)
            viewHolder.itemView.setOnClickListener { listener.onCatClicked(cat) }
        }

        override fun getItemCount() = nameSet.size
    }

    internal inner class ViewHolder constructor(
        itemView: View,
        private val recyclerItemCatBinding: RecyclerItemCatModelBinding
    ) : RecyclerView.ViewHolder(itemView) {

        fun setData(catModel: CatModel) {
            recyclerItemCatBinding.catModel = catModel
        }
    }
}
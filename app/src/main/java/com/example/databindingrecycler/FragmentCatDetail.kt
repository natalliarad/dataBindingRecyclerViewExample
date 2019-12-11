package com.example.databindingrecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databindingrecycler.databinding.FragmentCatDetailBinding

class FragmentCatDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentCatDetailBinding = FragmentCatDetailBinding.inflate(inflater, container, false)
        val model = requireArguments().getSerializable(CAT_MODEL) as CatModel
        fragmentCatDetailBinding.catModel = model
        model.text =
            String.format(getString(R.string.description_format), model.description, model.url)
        return fragmentCatDetailBinding.root
    }

    companion object {
        private const val CAT_MODEL = "model"

        fun newInstance(catModel: CatModel): FragmentCatDetail {
            val args = Bundle()
            args.putSerializable(CAT_MODEL, catModel)
            val fragment = FragmentCatDetail()
            fragment.arguments = args

            return fragment
        }
    }
}

package com.example.databindingrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), CatSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.root_layout, FragmentRecyclerCat.newInstance(), "catRecyclerViewFragment")
                .commit()
        }
    }

    override fun onCatClicked(catModel: CatModel) {
        val detailFragment = FragmentCatDetail.newInstance(catModel)
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, detailFragment, "catDetail")
            .addToBackStack(null)
            .commit()
    }
}

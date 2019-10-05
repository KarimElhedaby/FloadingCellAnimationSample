package com.baianat.floadingcellanimationsample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.baianat.floadingcellanimationsample.adapter.FloadingAdapter
import com.baianat.floadingcellanimationsample.model.Item
import com.baianat.floadingcellanimationsample.utils.GridSpacingItemDecoration
import com.baianat.floadingcellanimationsample.utils.ViewUtils
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FloadingAdapter.EventClick {

    lateinit var adapter: FloadingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floadingRV.layoutManager = GridLayoutManager(this, 1)
        floadingRV.addItemDecoration(
            GridSpacingItemDecoration(1, ViewUtils.dpToPx(12F), true, 0)
        )

        adapter = FloadingAdapter(Item.testingList, this)
        floadingRV.adapter = adapter
    }

    override fun onEventClickListener(event: Item, position: Int, view: View) {
        // toggle clicked cell state
        (view as FoldingCell).toggle(false)
        // register in adapter that state for selected cell is toggled
        adapter.registerToggle(position)
    }

    override fun onRequestItemClickListener(event: Item, position: Int) {
        Toast.makeText(this, "You request item at position $position", Toast.LENGTH_SHORT).show()
    }

}

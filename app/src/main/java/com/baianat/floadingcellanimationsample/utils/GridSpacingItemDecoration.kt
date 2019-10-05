package com.baianat.floadingcellanimationsample.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Karim Elhedaby on 21/03/2018.
 */
public class GridSpacingItemDecoration(var spanCount: Int, var spacing: Int,
                                       var includeEdge: Boolean, var headerNum: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) - headerNum // item position

        if (position >= 0) {
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}

class LinearSpacingItemDecoration(var spacing: Int, var includeEdge: Boolean,
                                  var headerNum: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) - headerNum // item position

        if (position >= 0) {
            val column = position  // item column

            if (includeEdge) {
                outRect.left = spacing / 2
                outRect.right = spacing / 2

                outRect.top = spacing
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing // spacing - (column + 1) * ((1f /    spanCount) * spacing)

                outRect.top = spacing // item top
            }
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}

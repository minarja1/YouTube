package cz.minarik.youtube.util

import android.content.res.Resources
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView


//todo move to Base

fun RecyclerView.dividerMedium() {
    addItemDecoration(SpacesItemDecoration(4.dpToPx))
}

fun RecyclerView.horizontalDividerMedium() {
    addItemDecoration(HorizontalSpacesItemDecoration(4.dpToPx))
}

fun RecyclerView.horizontalDividerLarge() {
    addItemDecoration(HorizontalSpacesItemDecoration(16.dpToPx))
}

fun RecyclerView.dividerLarge() {
    addItemDecoration(SpacesItemDecoration(16.dpToPx))
}


class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.bottom = space
    }
}

class HorizontalSpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.right = space
    }
}


val Int.pxToDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
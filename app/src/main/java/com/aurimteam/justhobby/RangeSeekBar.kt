package com.aurimteam.justhobby

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar

class RangeSeekBar : CrystalRangeSeekbar {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun getBarHeight(): Float {
        return 8f
    }

    override fun getCornerRadius(typedArray: TypedArray?): Float {
        return 8f
    }

    override fun getThumbWidth(): Float {
        return 30f
    }

    override fun getThumbHeight(): Float {
        return 30f
    }
}
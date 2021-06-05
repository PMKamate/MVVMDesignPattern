package com.manektech.ui.restaurantdetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.manektech.data.entities.Image

class ViewPagerAdapter internal constructor(fm: FragmentManager,imageList: ArrayList<Image>) :
    FragmentPagerAdapter(fm) {
    val newArrayList: ArrayList<Image> = imageList
    lateinit var viewPagerMVPFragment: ViewPagerMVPFragment
    override fun getItem(position: Int): Fragment {
        var bundel: Bundle = Bundle()
        bundel.putSerializable("obj", newArrayList[position])
        viewPagerMVPFragment = ViewPagerMVPFragment()
        viewPagerMVPFragment.arguments = bundel
        return viewPagerMVPFragment
    }

    override fun getCount(): Int {
        return newArrayList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ""
    }
}

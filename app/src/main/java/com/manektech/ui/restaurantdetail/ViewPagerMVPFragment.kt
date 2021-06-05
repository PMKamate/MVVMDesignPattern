package com.manektech.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.manektech.data.entities.Image
import com.manektech.databinding.FragmentViewpagerBinding
import com.manektech.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerMVPFragment : Fragment() {
    private var binding: FragmentViewpagerBinding by autoCleared()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val obj: Image = arguments?.getSerializable("obj") as Image

        Glide.with(binding.root)
            .load(obj.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageView1)

    }
}
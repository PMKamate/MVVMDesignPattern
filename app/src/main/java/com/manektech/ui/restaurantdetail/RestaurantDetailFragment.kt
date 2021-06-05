package com.manektech.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manektech.data.entities.RestaurantItem
import com.manektech.databinding.RestaurantdetailsFragmentBinding
import com.manektech.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private var binding: RestaurantdetailsFragmentBinding by autoCleared()
    private val viewModel: RestaurantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RestaurantdetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()?.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }
    private fun setupObservers() {
       /* viewModel.restaurantItem.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindData(it.data!!)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {

                }
            }
        })

        viewModel.imageListItem.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.d("Test:image ", "" + it.data)
                    binding.viewpagerLayout.adapter = ViewPagerAdapter(
                        this.childFragmentManager,
                        it.data as ArrayList<Image>
                    )
                    binding.tabslayout.setupWithViewPager(binding.viewpagerLayout, true)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {

                }
            }
        })*/
    }
    private fun bindData(restaurantItem: RestaurantItem) {
        binding.tvTitle.text = restaurantItem.title
        binding.tvMobileno.text = restaurantItem.phone_no.toString()
        restaurantItem.rating?.let {
            binding.ratingBar.rating= it.toFloat()
        }?:kotlin.run {
            binding.ratingBar.rating= 0.0f
        }
        binding.tvDescriptionText.text = restaurantItem.description
        binding.tvAddressText.text = restaurantItem.address
       /* Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.image)*/
    }
}

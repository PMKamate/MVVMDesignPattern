package com.manektech.ui.restaurantList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.manektech.utils.Resource
import com.manektech.utils.autoCleared
import com.manektech.R
import com.manektech.data.entities.RestaurantItem
import com.manektech.databinding.RestaurantlistFragmentBinding
import com.manektech.ui.restaurantdetail.RestaurantDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment(), RestaurantListAdapter.RestaurantItemListener {

    private var binding: RestaurantlistFragmentBinding by autoCleared()
    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var adapter: RestaurantListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RestaurantlistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = RestaurantListAdapter(this)
        binding.rvRestaurantlist.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRestaurantlist.adapter = adapter
    }

    private fun setupObservers() {
        getActivity()?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        viewModel.restaurant.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedRestaurant(restaurantItem: RestaurantItem, isMapclick: Boolean) {
        if(isMapclick){
            Log.d("Test: ",""+restaurantItem)
            val bundle = Bundle()
            bundle.putString("lat", restaurantItem.lat)
            bundle.putString("longg", restaurantItem.longdb)
            bundle.putString("title", restaurantItem.title)
            findNavController().navigate(
                R.id.action_restaurantFragment_to_mapsFragment,
                bundle
            )
        }else {
           /* findNavController().navigate(
                R.id.action_restaurantFragment_to_restaurantDetailFragment,
                bundleOf("id" to restaurantItem.id)
            )*/
            val intent = Intent(activity, RestaurantDetailsActivity::class.java)
            intent.putExtra("id",restaurantItem.id)
            startActivity(intent)
        }
    }
}

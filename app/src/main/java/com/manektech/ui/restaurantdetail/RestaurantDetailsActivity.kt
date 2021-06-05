package com.manektech.ui.restaurantdetail

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView.BufferType
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.manektech.R
import com.manektech.data.entities.Image
import com.manektech.data.entities.RestaurantItem
import com.manektech.databinding.ActivityRestaurantDetailBinding
import com.manektech.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_restaurant_detail.*


@AndroidEntryPoint
class RestaurantDetailsActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private var isHideToolbarView = false
    private val viewModel: RestaurantDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRestaurantDetailBinding = ActivityRestaurantDetailBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.setTitle("")
        appbar.addOnOffsetChangedListener(this)
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
       //  window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorWhiteTenOpacity));
       // window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val extras = intent.extras
        val str = extras?.getInt("id")
        str?.let { viewModel.start(it) }
        setupObservers(binding)
        setSpannableStringBuilder()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

   override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScroll: Int = appBarLayout.getTotalScrollRange()
        val percentage = Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()
        if (percentage == 1f && isHideToolbarView) {
            isHideToolbarView = !isHideToolbarView
        } else if (percentage < 1f && !isHideToolbarView) {
            isHideToolbarView = !isHideToolbarView
        }
    }

    private fun setupObservers(binding: ActivityRestaurantDetailBinding) {
        viewModel.restaurantItem.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindData(it.data!!, binding)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {

                }
            }
        })

        viewModel.imageListItem.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.viewpagerLayout.adapter = ViewPagerAdapter(
                        getSupportFragmentManager(),
                        it.data as ArrayList<Image>
                    )
                    binding.tabslayout.setupWithViewPager(binding.viewpagerLayout, true)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {

                }
            }
        })
    }
    private fun bindData(restaurantItem: RestaurantItem, binding: ActivityRestaurantDetailBinding) {
        binding.tvTitle.text = restaurantItem.title
        binding.tvMobileno.text = restaurantItem.phone_no.toString()
        restaurantItem.rating?.let {
            binding.ratingBar.rating= it.toFloat()
        }?:kotlin.run {
            binding.ratingBar.rating= 0.0f
        }
        binding.tvDescriptionText.text = restaurantItem.description
        binding.tvAddressText.text = restaurantItem.address
    }

    fun setSpannableStringBuilder(){
        val builder: SpannableStringBuilder = SpannableStringBuilder()

        val red = "("
        val redSpannable = SpannableString(red)
        redSpannable.setSpan(ForegroundColorSpan(Color.BLACK), 0, red.length, 0)
        builder.append(redSpannable)

        val white = " 1000 "
        val whiteSpannable = SpannableString(white)
        whiteSpannable.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this,
                    R.color.star
                )
            ), 0, white.length, 0
        )
        builder.append(whiteSpannable)

        val blue = "Reviews )"
        val blueSpannable = SpannableString(blue)
        blueSpannable.setSpan(ForegroundColorSpan(Color.BLACK), 0, blue.length, 0)
        builder.append(blueSpannable)

        tv_reviews.setText(builder, BufferType.SPANNABLE)
    }
}

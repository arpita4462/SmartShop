package com.example.harishkumar.smartshop.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.harishkumar.smartshop.MainActivity
import com.example.harishkumar.smartshop.utility.ImageUrlUtils
import com.facebook.drawee.view.SimpleDraweeView


class ImageListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as MainActivity?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rv = inflater.inflate(R.layout.layout_recylerview_list, container, false) as RecyclerView
        setupRecyclerView(rv)
        return rv
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        /*  if (ImageListFragment.this.getArguments().getInt("type") == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        } else if (ImageListFragment.this.getArguments().getInt("type") == 2) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }*/
        var items: Array<String>? = null
    /*    if (this@ImageListFragment.arguments!!.getInt("type") == 1) {
            items = ImageUrlUtils.getOffersUrls()
        } else if (this@ImageListFragment.arguments!!.getInt("type") == 2) {
            items = ImageUrlUtils.getElectronicsUrls()
        } else if (this@ImageListFragment.arguments!!.getInt("type") == 3) {
            items = ImageUrlUtils.getLifeStyleUrls()
        } else if (this@ImageListFragment.arguments!!.getInt("type") == 4) {
            items = ImageUrlUtils.getHomeApplianceUrls()
        } else if (this@ImageListFragment.arguments!!.getInt("type") == 5) {
            items = ImageUrlUtils.getBooksUrls()
        } else {
            items = ImageUrlUtils.getImageUrls()
        }*/
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = SimpleStringRecyclerViewAdapter(recyclerView, items!!)
    }

    class SimpleStringRecyclerViewAdapter(private val mRecyclerView: RecyclerView, private val mValues: Array<String>) : RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>() {

        class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            val mImageView: SimpleDraweeView
            val mLayoutItem: LinearLayout
            val mImageViewWishlist: ImageView

            init {
                mImageView = mView.findViewById(R.id.image1) as SimpleDraweeView
                mLayoutItem = mView.findViewById(R.id.layout_item) as LinearLayout
                mImageViewWishlist = mView.findViewById(R.id.ic_wishlist) as ImageView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onViewRecycled(holder: ViewHolder) {
            if (holder.mImageView.getController() != null) {
                holder.mImageView!!.getController()!!.onDetach()
            }
            if (holder.mImageView.getTopLevelDrawable() != null) {
                holder.mImageView!!.getTopLevelDrawable()!!.setCallback(null)
                //                ((BitmapDrawable) holder.mImageView.getTopLevelDrawable()).getBitmap().recycle();
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            /* FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.mImageView.getLayoutParams();
            if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                layoutParams.height = 200;
            } else if (mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                layoutParams.height = 600;
            } else {
                layoutParams.height = 800;
            }*/
            val uri = Uri.parse(mValues[position])
            holder.mImageView.setImageURI(uri)
/*
            holder.mLayoutItem.setOnClickListener {
                val intent = Intent(mActivity, ItemDetailsActivity::class.java)
                intent.putExtra(STRING_IMAGE_URI, mValues[position])
                intent.putExtra(STRING_IMAGE_POSITION, position)
                mActivity!!.startActivity(intent)
            }
*/

            //Set click action for wishlist
            holder.mImageViewWishlist.setOnClickListener {
                val imageUrlUtils = ImageUrlUtils()
                imageUrlUtils.addWishlistImageUri(mValues[position])
                holder.mImageViewWishlist.setImageResource(R.drawable.ic_favorite_black_18dp)
                notifyDataSetChanged()
                Toast.makeText(mActivity, "Item added to wishlist.", Toast.LENGTH_SHORT).show()
            }

        }

        override fun getItemCount(): Int {
            return mValues.size
        }
    }

    companion object {

        val STRING_IMAGE_URI = "ImageUri"
        val STRING_IMAGE_POSITION = "ImagePosition"
        private var mActivity: MainActivity? = null
    }
}

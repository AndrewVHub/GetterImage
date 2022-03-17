package com.example.alefimage.views

import android.R.bool
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.alefimage.adapters.ImageAdapter
import com.example.alefimage.adapters.decoration.GridSpacingItemDecoration
import com.example.alefimage.databinding.FragmentHomeBinding
import com.example.alefimage.util.toDp
import com.example.alefimage.viewmodels.ImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ImageViewModel by viewModel()
    private var isTabletModeDetermined = false
    private lateinit var linearLayout: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterImage = ImageAdapter(viewModel.ld){itemImage ->
            val action = HomeFragmentDirections.actionHomeFragmentToImageFragment(itemImage)
            Navigation.findNavController(view).navigate(action)

        }

        var spanCount = 0
        context?.let {
            if (isTablet(it) ){
                spanCount = 3
            }else{
                spanCount = 2
            }
        }
        linearLayout = GridLayoutManager(context, spanCount)
        val marginGrid = 25.toDp(binding.recyclerImage.context)
        binding.recyclerImage.addItemDecoration(GridSpacingItemDecoration(spanCount, marginGrid, true, 0))

        binding.recyclerImage.layoutManager = linearLayout
        binding.recyclerImage.adapter = adapterImage

        viewModel.ld.observe(viewLifecycleOwner){
            adapterImage.notifyDataSetChanged()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getImages()
        }
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ImageViewModel.ImageAction.HideLoader -> binding.swipeRefresh.isRefreshing =
                    false
                is ImageViewModel.ImageAction.ShowError -> Toast.makeText(
                    context,
                    action.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

   private fun isTablet(context: Context): Boolean {
        if (!isTabletModeDetermined) {
            if (context.getResources().getConfiguration().smallestScreenWidthDp>= 600)
                return true
        }
       return false
    }
}
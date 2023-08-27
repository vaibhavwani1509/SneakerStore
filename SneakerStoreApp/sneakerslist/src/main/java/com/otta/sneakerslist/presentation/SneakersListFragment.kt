package com.otta.sneakerslist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.otta.sneakerslist.R
import com.otta.sneakerslist.databinding.FragmentSneakersListBinding
import com.otta.sneakerslist.presentation.Adapter.SneakersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SneakersListFragment : Fragment() {

    /**Data Binding*/
    private lateinit var binding : FragmentSneakersListBinding

    /**ViewModel*/
    lateinit var viewModel: SneakersListViewModel

    /**Adapter*/
    lateinit var sneakersRecyclerViewAdapter : SneakersListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sneakers_list, container, false)
        binding.sneakerListFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Init the viewModel*/
        viewModel = ViewModelProvider(requireActivity())[SneakersListViewModel::class.java]

        /**RecyclerView Adapter with select listener*/
        sneakersRecyclerViewAdapter = SneakersListAdapter{
            val selectedSneakerId = Bundle()
            selectedSneakerId.putString("sneakerId" , it.id)
            requireView().findNavController().navigate(R.id.action_sneakersListFragment_to_sneakerDetailsFragment, selectedSneakerId)
        }

        /**Recyclerview UI Init*/
        binding.sneakersRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
            adapter = sneakersRecyclerViewAdapter
        }

        /**Listen for the Sneakers list*/
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSneakerList()
                viewModel.sneakersList.collect {
                    sneakersRecyclerViewAdapter.setList(it)
                }
            }
        }
    }

    fun openSearch() {
        requireView().findNavController().navigate(R.id.action_sneakersListFragment_to_searchFragment2)
    }
}
package com.example.notesappydh.ui.adapter.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappydh.R
import com.example.notesappydh.databinding.FragmentHomeBinding
import com.example.notesappydh.model.Notes
import com.example.notesappydh.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var myNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        viewModel.getNotes().observe(viewLifecycleOwner) { notes ->
            myNotes = notes as ArrayList<Notes>
            binding.rvHomeFragment.layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
            adapter =  NotesAdapter(requireContext(), notesList = notes)
            binding.rvHomeFragment.adapter = adapter
        }


        binding.filterAll.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notes ->
                myNotes = notes as ArrayList<Notes>

                binding.rvHomeFragment.layoutManager = StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL)
                adapter =  NotesAdapter(requireContext(), notesList = notes)
                binding.rvHomeFragment.adapter = adapter
            }
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notes ->
                myNotes = notes as ArrayList<Notes>

                binding.rvHomeFragment.layoutManager = StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL)
                adapter =  NotesAdapter(requireContext(), notesList = notes)
                binding.rvHomeFragment.adapter = adapter
            }
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notes ->
                myNotes = notes as ArrayList<Notes>

                binding.rvHomeFragment.layoutManager = StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL)
                adapter =  NotesAdapter(requireContext(), notesList = notes)
                binding.rvHomeFragment.adapter = adapter
            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notes ->
                myNotes = notes as ArrayList<Notes>

                binding.rvHomeFragment.layoutManager = StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL)
                adapter =  NotesAdapter(requireContext(), notesList = notes)
                binding.rvHomeFragment.adapter = adapter
            }
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_createNoteFragment2)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.menuSearch)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes here..."
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in myNotes){
            if (i.title.contains(p0!!) || i.subTitle.contains(p0)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}
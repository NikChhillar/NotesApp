package com.example.notesappydh.ui.adapter.fragment


import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.notesappydh.R
import com.example.notesappydh.databinding.FragmentCreateNoteBinding
import com.example.notesappydh.model.Notes
import com.example.notesappydh.viewModel.NotesViewModel
import java.lang.String.format


import java.text.MessageFormat.format
import java.util.*


class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNoteBinding.inflate(layoutInflater,container,false)
        binding.ivGreenFC.setImageResource(R.drawable.ic_baseline_done_24)

        binding.btnSaveBtn.setOnClickListener {
            createNotes(it)
        }
        binding.ivGreenFC.setOnClickListener {
            priority = "1"
            binding.ivGreenFC.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivRedFC.setImageResource(0)
            binding.ivYellowFC.setImageResource(0)
        }
        binding.ivRedFC.setOnClickListener {
            priority = "3"
            binding.ivRedFC.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivGreenFC.setImageResource(0)
            binding.ivYellowFC.setImageResource(0)
        }
        binding.ivYellowFC.setOnClickListener {
            priority = "2"
            binding.ivYellowFC.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivRedFC.setImageResource(0)
            binding.ivGreenFC.setImageResource(0)
        }


        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.etTitleFC.text.toString()
        val subTitle = binding.etSubTitleFC.text.toString()
        val notes = binding.etNotesFC.text.toString()

        val d = Date()
        val notesDate: CharSequence= DateFormat.format("MMMM d, yyyy ",d.time)

        val notesData = Notes(null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority = priority
        )
        if (binding.etTitleFC.text.toString().isEmpty()){
            Toast.makeText(requireContext(), "Title mustn't be empty", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.addNotes(notesData)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

       // requireActivity()
        //            .onBackPressedDispatcher
        //            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
        //                override fun handleOnBackPressed() {
        //                    Log.d(TAG, "Fragment back pressed invoked")
        //                    // Do custom work here
        //
        //                    // if you want onBackPressed() to be called as normal afterwards
        //                    if (isEnabled) {
        //                        isEnabled = false
        //                        requireActivity().onBackPressed()
        //                    }
        //                }
        //            }
        //            )
       // Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment2_to_homeFragment2)
    }



}
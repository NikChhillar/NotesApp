package com.example.notesappydh.ui.adapter.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesappydh.R
import com.example.notesappydh.databinding.FragmentEditNotesBinding
import com.example.notesappydh.model.Notes
import com.example.notesappydh.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    var priority = "1"
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    val viewModel : NotesViewModel by viewModels()
    lateinit var binding: FragmentEditNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding.etTitleFEN.setText(oldNotes.data.title)
        binding.etSubTitleFEN.setText(oldNotes.data.subTitle)
        binding.etNotesFEN.setText(oldNotes.data.notes)


        when(oldNotes.data.priority){
            "1" -> {
                binding.ivGreenFEN.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivRedFEN.setImageResource(0)
                binding.ivYellowFEN.setImageResource(0)
            }
            "2" -> {
                binding.ivYellowFEN.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivRedFEN.setImageResource(0)
                binding.ivGreenFEN.setImageResource(0)
            }
            "3" -> {
                binding.ivRedFEN.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivGreenFEN.setImageResource(0)
                binding.ivYellowFEN.setImageResource(0)
            }
        }

        binding.ivGreenFEN.setOnClickListener {
            priority = "1"
            binding.ivGreenFEN.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivRedFEN.setImageResource(0)
            binding.ivYellowFEN.setImageResource(0)
        }
        binding.ivRedFEN.setOnClickListener {
            priority = "3"
            binding.ivRedFEN.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivGreenFEN.setImageResource(0)
            binding.ivYellowFEN.setImageResource(0)
        }
        binding.ivYellowFEN.setOnClickListener {
            priority = "2"
            binding.ivYellowFEN.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivRedFEN.setImageResource(0)
            binding.ivGreenFEN.setImageResource(0)
        }

        binding.btnEditSaveBtn.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.etTitleFEN.text.toString()
        val subTitle = binding.etSubTitleFEN.text.toString()
        val notes = binding.etNotesFEN.text.toString()

        val d = Date()
        val notesDate: CharSequence= DateFormat.format("MMMM d, yyyy ",d.time)

        val notesData = Notes(
            oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority = priority
        )
        if (binding.etTitleFEN.text.toString().isEmpty()){
            Toast.makeText(requireContext(), "Title mustn't be empty", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.updateNotes(notesData)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

       // Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment2_to_homeFragment2)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menuDelete){
            val bottomSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogDeleteYes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogDeleteNo)

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
                bottomSheet.dismiss()

            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}
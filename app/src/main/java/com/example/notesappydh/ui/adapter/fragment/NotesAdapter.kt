package com.example.notesappydh.ui.adapter.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappydh.R
import com.example.notesappydh.databinding.ItemsNotesBinding
import com.example.notesappydh.model.Notes

class NotesAdapter(val requireContext : Context, var notesList : List<Notes>):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    inner class NotesViewHolder(val binding : ItemsNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemsNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(notesList[position].priority){
            "1" -> {
                holder.binding.notesPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.notesPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" -> {
                holder.binding.notesPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragment2ToEditNotesFragment2(data = data)
            Navigation.findNavController(it).navigate(action)
            //Toast.makeText(requireContext, "Opening " + data.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
       return  notesList.size
    }
}
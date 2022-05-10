package com.example.notesappydh.repository

import androidx.lifecycle.LiveData
import com.example.notesappydh.dao.NotesDao
import com.example.notesappydh.model.Notes

class NotesRepository(val dao:NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()


    fun insertNotes(notes:Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes:Notes){
        dao.updateNotes(notes)
    }


}
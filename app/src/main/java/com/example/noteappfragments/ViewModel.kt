package com.example.noteappfragments
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModel(activity: Application) : AndroidViewModel(activity){
    private val notes : LiveData<List<Notes>>

    // object
    val objndao = NotesDatabase.getInstance(activity).NotesDao()

    init {
        notes = objndao.getallnotes()
    }

    fun getnotes(): LiveData<List<Notes>>{
        return notes
    }

    fun addNote(note : Notes){
        GlobalScope.launch(Main){
            objndao.insertNote(note)
        }
    }

    fun updtNote(note: Notes){
        GlobalScope.launch(Main){
            objndao.updtNote(note)
        }
    }

    fun delNote(note: Notes){
        GlobalScope.launch(Main){
            objndao.delNote(note)
        }
    }

}
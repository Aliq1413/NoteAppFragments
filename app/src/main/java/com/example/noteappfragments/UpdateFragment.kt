package com.example.noteappfragments

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.os.TestLooperManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
class UpdateFragment : Fragment() {
    private val myViewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }

    //Declaring UI
    lateinit var tvUpdate : TextView
    lateinit var edtUpdt: EditText
    lateinit var updtBtn: Button

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        //init UI
        sharedPreferences = requireActivity().getSharedPreferences("Notes", Context.MODE_PRIVATE)
        tvUpdate = view.findViewById(R.id.tvUpdate)
        tvUpdate.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_updateFragment_to_homeFragment)

        }
        edtUpdt = view.findViewById(R.id.edtUpdt)
        updtBtn=view.findViewById(R.id.updtBtn)
        updtBtn.setOnClickListener {
            var newNote = edtUpdt.text.toString()
            update(Notes(sharedPreferences.getInt("NoteID",0), newNote))
            tvUpdate.text = edtUpdt.text.toString()
            edtUpdt.text.clear()
            edtUpdt.clearFocus()
        }

        return view
    }
    fun update(note: Notes){
        val newNote = edtUpdt.text.toString()
        if(newNote.isNotEmpty()){
            myViewModel.updtNote(Notes(note.id, newNote))
        }
        else{
            Toast.makeText(requireContext(), "You cannot update it with empty!", Toast.LENGTH_SHORT).show()
        }
    }


}
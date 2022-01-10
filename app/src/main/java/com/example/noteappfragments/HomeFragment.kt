package com.example.noteappfragments

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
class HomeFragment : Fragment() {
    //Declaring ViewModel
    private val myViewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }
    //Declaring RV
    lateinit var rvNotes: RecyclerView
    lateinit var rvNotesAdapter: RVAdapter
    lateinit var notesList: List<Notes>
    lateinit var sharedPreferences: SharedPreferences
    //Declaring UI
    lateinit var edtNote: EditText
    lateinit var subBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        // init RV
        rvNotes = view.findViewById(R.id.rvNotes)
        notesList= arrayListOf()

        rvNotesAdapter = RVAdapter(this,notesList)
        rvNotes.adapter = rvNotesAdapter
        rvNotes.layoutManager = GridLayoutManager(requireContext(),2)
        sharedPreferences = requireActivity().getSharedPreferences("Notes", Context.MODE_PRIVATE)

        myViewModel.getnotes().observe(viewLifecycleOwner,
            { notesArray -> rvNotesAdapter.rvChange(notesArray)})

        // init UI
        edtNote = view.findViewById(R.id.edtNote)
        subBtn = view.findViewById(R.id.submBtn)
        subBtn.setOnClickListener {
            val nota = edtNote.text.toString()
            if (nota.isNotEmpty()){
                myViewModel.addNote(Notes(0,nota))
                Toast.makeText(requireContext(), "note added successfully", Toast.LENGTH_LONG).show()
                edtNote.text.clear()
                edtNote.clearFocus()
            }
            else{
                Toast.makeText(requireContext(),"please add note first", Toast.LENGTH_LONG).show()
            }
        }

        return view

    }

    fun UpdateGo(){
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_updateFragment)
    }



    fun DialogDel(note : Notes ){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Are you sure?")
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id-> myViewModel.delNote(note)

            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Delete Note")
        alert.show()
    }

}
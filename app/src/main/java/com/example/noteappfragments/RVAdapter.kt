package com.example.noteappfragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappfragments.databinding.ItemRowBinding

class RVAdapter(val Fragment: HomeFragment,var notesList: List<Notes>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(var binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val aNote = notesList[position]

        holder.binding.apply {
            tvNote.text = aNote.note

            edtNote.setOnClickListener{
                with(Fragment.sharedPreferences.edit()){
                    putInt("NoteID",aNote.id)
                    apply()
                }
                Fragment.UpdateGo()
            }

            delNote.setOnClickListener{
                Fragment.DialogDel(aNote)
            }
        }
    }

    override fun getItemCount(): Int = notesList.size

    fun rvChange(notes: List<Notes>) {
        this.notesList = notes
        notifyDataSetChanged()
    }

}
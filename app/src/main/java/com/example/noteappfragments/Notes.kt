package com.example.noteappfragments
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "Note") val note: String
)
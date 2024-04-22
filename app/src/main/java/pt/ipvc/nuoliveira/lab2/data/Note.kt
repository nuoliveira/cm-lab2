package pt.ipvc.nuoliveira.lab2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoteId")
    var id: Int,
    @ColumnInfo(name = "Text")
    var text: String,
)

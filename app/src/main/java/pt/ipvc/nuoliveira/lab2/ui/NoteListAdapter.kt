package pt.ipvc.nuoliveira.lab2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.ipvc.nuoliveira.lab2.R
import pt.ipvc.nuoliveira.lab2.data.Note

class NoteListAdapter(private val onClickListener: (Note) -> Unit) : ListAdapter<Note, NoteListAdapter.ViewHolder>(NoteDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView, onClickListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(itemView: View, onClickListener: (Note) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private var currentNote: Note? = null
        private val textText: TextView = itemView.findViewById(R.id.text_text)

        init {
            itemView.setOnClickListener {
                currentNote?.let {
                    onClickListener(it)
                }
            }
        }

        fun bind(note: Note) {
            currentNote = note
            textText.text = note.text
        }
    }
}

object NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(old: Note, new: Note): Boolean = old.id == new.id
    override fun areContentsTheSame(old: Note, new: Note): Boolean = old == new
}

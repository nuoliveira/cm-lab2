package pt.ipvc.nuoliveira.lab2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipvc.nuoliveira.lab2.R
import pt.ipvc.nuoliveira.lab2.data.Note
import pt.ipvc.nuoliveira.lab2.data.NotesViewModel

class EditNoteFragment : Fragment(), MenuProvider {
    private val viewModel: NotesViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var textInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner)
        navController = view.findNavController()
        textInput = view.findViewById<EditText>(R.id.text_input).apply {
            setText(viewModel.note.text)
        }
    }

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_update -> {
                val text = textInput.text.toString()
                if (text.length < 5) {
                    Snackbar.make(requireView(), R.string.fragment_new_note_error, Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(requireView(), R.string.fragment_edit_note_success, Snackbar.LENGTH_SHORT).show()
                    viewModel.note.text = text
                    viewModel.updateNote()
                    navController.navigateUp()
                }
                return true
            }

            R.id.action_delete -> {
                viewModel.deleteNote()
                navController.navigateUp()
                return true
            }
        }
        return false
    }
}

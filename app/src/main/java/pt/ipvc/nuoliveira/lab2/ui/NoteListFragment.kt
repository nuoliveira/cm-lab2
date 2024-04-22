package pt.ipvc.nuoliveira.lab2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pt.ipvc.nuoliveira.lab2.R
import pt.ipvc.nuoliveira.lab2.data.NotesViewModel

class NoteListFragment : Fragment(), MenuProvider {
    private val viewModel: NotesViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner)
        navController = view.findNavController()

        val noteListAdapter = NoteListAdapter {
            viewModel.note.id = it.id
            viewModel.note.text = it.text
            navController.navigate(NoteListFragmentDirections.actionNoteListToEditNote())
        }

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
//            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.allNotes.observe(viewLifecycleOwner) {
            noteListAdapter.submitList(it)
        }
    }

    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note_list, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_new -> {
                navController.navigate(NoteListFragmentDirections.actionNoteListToNewNote())
                return true
            }
        }
        return false
    }
}

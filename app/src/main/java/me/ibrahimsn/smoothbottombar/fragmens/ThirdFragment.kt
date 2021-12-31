package me.ibrahimsn.smoothbottombar.fragmens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_third.*
import kotlinx.android.synthetic.main.fragment_third.view.*
import me.ibrahimsn.smoothbottombar.R
import me.ibrahimsn.smoothbottombar.ui.model.notes
import me.ibrahimsn.smoothbottombar.viewmodel.AddViewModel

/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : Fragment() {

    private lateinit var viewModel : AddViewModel

    private val args by navArgs<ThirdFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        view.update_head.setText(args.currentUser.head)
        view.update_description.setText(args.currentUser.desciription)

        view.update_button.setOnClickListener {
            updateItem(args.currentUser)
            Toast.makeText(context,"Successfully updated!",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_third_fragment_to_first_fragment)
        }

        view.delete_note.setOnClickListener {
            deleteItem(args.currentUser)
            Toast.makeText(context,"Note Deleted!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_third_fragment_to_first_fragment)

        }

        return view
    }

    private fun deleteItem(deleteNote: notes) {
        viewModel.deleteNote(deleteNote)
    }

    private fun updateItem(updateNote: notes) {

        val head = update_head.text.toString()
        val des = update_description.text.toString()

        val updateNote = notes(args.currentUser.id,head,des)

        viewModel.updateNote(updateNote)

    }
}

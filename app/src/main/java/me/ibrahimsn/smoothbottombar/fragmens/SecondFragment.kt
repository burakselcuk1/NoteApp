package me.ibrahimsn.smoothbottombar.fragmens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import me.ibrahimsn.smoothbottombar.R
import me.ibrahimsn.smoothbottombar.ui.model.notes
import me.ibrahimsn.smoothbottombar.viewmodel.AddViewModel


/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {

    private lateinit var viewModel: AddViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)


        view.add_btn.setOnClickListener {
            insertDataToDatabase()
            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)

        }


        return view
    }



    private fun insertDataToDatabase() {
        val description = description.text.toString()
        val head = head.text.toString()

        val name = notes(0,head,description)
        viewModel.addNote(name)
        Toast.makeText(context,"Successfully added!",Toast.LENGTH_SHORT).show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Add New Note"



    }

}

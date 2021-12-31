package me.ibrahimsn.smoothbottombar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*
import me.ibrahimsn.smoothbottombar.R
import me.ibrahimsn.smoothbottombar.fragmens.FirstFragmentDirections
import me.ibrahimsn.smoothbottombar.ui.model.notes


class CustomAdapter(private val dataSet: ArrayList<notes>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {




    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val head: TextView
        val id: TextView



        init {
            // Define click listener for the ViewHolder's View.
            head = view.findViewById(R.id.firstName_txt)
            id = view.findViewById(R.id.id_txt)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.custom_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentItem = dataSet[position]
        viewHolder.head.text = dataSet[position].head
        viewHolder.id.text = dataSet[position].id.toString()

        viewHolder.itemView.rowLayout.setOnClickListener {

            val action = FirstFragmentDirections.actionFirstFragmentToThirdFragment(currentItem)
            viewHolder.itemView.findNavController().navigate(action)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(note: ArrayList<notes>){
        dataSet.clear()
        dataSet.addAll(note)
        notifyDataSetChanged()
    }






    }

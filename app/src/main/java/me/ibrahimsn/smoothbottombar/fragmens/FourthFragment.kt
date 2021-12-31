package me.ibrahimsn.smoothbottombar.fragmens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_fourth.*
import me.ibrahimsn.smoothbottombar.R


class FourthFragment : Fragment() {


    private lateinit var database: DatabaseReference
    private lateinit var auth : FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        val ordersRef = database.child("$uid")




        var getData = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var username = snapshot.child("username").getValue()
                usernamee.setText(username.toString())
                var email = snapshot.child("email").getValue()
                emaill.setText(email.toString())
                var phone = snapshot.child("phone").getValue()
                phonee.setText(phone.toString())


                usernameee.setText(username.toString())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        ordersRef.addValueEventListener(getData)
        ordersRef.addListenerForSingleValueEvent(getData)

        updateUser.setOnClickListener {
            val action = FourthFragmentDirections.actionFourthFragmentToUpdataProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }


    }


}

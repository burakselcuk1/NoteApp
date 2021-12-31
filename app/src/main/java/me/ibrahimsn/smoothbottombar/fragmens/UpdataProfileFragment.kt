package me.ibrahimsn.smoothbottombar.fragmens

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_updata_profile.*
import me.ibrahimsn.smoothbottombar.R
import me.ibrahimsn.smoothbottombar.ui.model.UserProfile
import java.text.SimpleDateFormat
import java.util.*


class UpdataProfileFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var auth : FirebaseAuth
    lateinit var ImageUrl : Uri



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_updata_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Update Profile"


        // updata profile progress
        updateuserbutton.setOnClickListener {
            uploadImageToFirebaseStorage()
        }

        uploadimage.setOnClickListener {
            selectImage()
        }
}

    private fun uploadImageToFirebaseStorage() {
        var username1 = update_user_username.text.toString()
        var email1 = update_user_email.text.toString()
        var phone1 = update_user_phone.text.toString()

        //Checking Email is Empty or not
        if (email1.isEmpty()){
            Toast.makeText(context,"Please Enter An Email",Toast.LENGTH_SHORT).show()
        }
        //Checking Phone is Empty or not
        else if (phone1.isEmpty()){
            Toast.makeText(context,"Please Enter An Phone Number",Toast.LENGTH_SHORT).show()

        }
        //Checking Username is Empty or not
        else if (username1.isEmpty()){
            Toast.makeText(context,"Please Enter An UserName",Toast.LENGTH_SHORT).show()

        }
        //Checking Image is Empty or not
        else if (circleImage.getDrawable() == null){
            Toast.makeText(context,"Please select a photo",Toast.LENGTH_SHORT).show()

        }
        else{
            val progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Uploading file...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
            val now = Date()
            val fileName = formatter.format(now)
            val storage = FirebaseStorage.getInstance().getReference("images/$fileName")

            storage.putFile(ImageUrl)
                .addOnSuccessListener {

                    uploadimage.setImageURI(null)
                    Toast.makeText(context,"SUSCESSFULLY ADDED!",Toast.LENGTH_SHORT).show()
                    if (progressDialog.isShowing) progressDialog.dismiss()
                    storage.downloadUrl.addOnSuccessListener {
                        Log.d("UpdateProfileFragment","File Location: $it")
                        saveUserToFirebaseDatabase(it.toString())
                    }

                }.addOnFailureListener{
                    if (progressDialog.isShowing) progressDialog.dismiss()

                    Toast.makeText(context,"FAIL",Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        database = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        var username = update_user_username.text.toString()
        var email = update_user_email.text.toString()
        var phone = update_user_phone.text.toString()

        if (uid!= null){

            database.child(uid).setValue(UserProfile(username,phone, profileImageUrl.toString(),email)).addOnSuccessListener {

                Toast.makeText(context,"SUSCESSFULLY ADDED NAME!", Toast.LENGTH_SHORT).show()

                val action = UpdataProfileFragmentDirections.actionUpdataProfileFragmentToFourthFragment()
                //  Navigation.findNavController(view).navigate(action)

            }.addOnFailureListener{
                Toast.makeText(context,"FAILADANA!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type="image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==0 && resultCode == RESULT_OK){

            ImageUrl = data?.data!!
            circleImage.setImageURI(ImageUrl)
            uploadimage.visibility = View.GONE
        }
    }

}
package com.miu.bethel.cv

import Contact
import ContactDialog
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.bethel.cv.R
import com.miu.bethel.cv.adapter.ContactAdapter
import com.miu.bethel.cv.adapter.WorkAdapter
import com.miu.bethel.cv.dialog.WorkDialog

class ContactFragment : Fragment(R.layout.fragment_contact) {
    private var contactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.phone).setOnClickListener { onPhoneClicked() }
        view.findViewById<LinearLayout>(R.id.email).setOnClickListener { onEmailClicked() }


//        recyclerView = view.findViewById(R.id.recycler_view)
//        if (context != null) {
//            contactList = mutableListOf(
//                Contact(
//                    getString(R.string.krisma),
//                    getString(R.string.sr_full_stack_engineer),
//                    R.drawable.work_icog
//                ),
//                Contact(
//                    getString(R.string.apple),
//                    getString(R.string.sde_ii),
//                    R.drawable.apple
//                )
//            )
//            setupRecyclerView()
//        }

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { showContactDialog() }
    }

    private fun onPhoneClicked() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(getString(R.string.my_phone))
        startActivity(intent)
    }

    private fun onEmailClicked() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse(getString(R.string.intent_filter))
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.my_email))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))
        startActivity(intent)
    }

    private fun onLinkedInClicked() {
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(getString(R.string.intent_key), getString(R.string.my_linkedin))
        startActivity(intent)
    }

    private fun showContactDialog() {
        val dialog = ContactDialog()
        dialog.show(parentFragmentManager, ContactDialog::class.java.name)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onAddContact(work: Contact) {
        contactList.add(work)
        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        } else {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        if (::recyclerView.isInitialized) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter(requireContext(), contactList)
            recyclerView.adapter = adapter
        }
    }


}
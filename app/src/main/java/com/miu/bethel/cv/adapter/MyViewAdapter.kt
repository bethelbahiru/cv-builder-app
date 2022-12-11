package com.miu.bethel.cv.adapter

import Contact
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miu.bethel.cv.*

class MyViewAdapter(fm:FragmentManager,lc:Lifecycle) : FragmentStateAdapter(fm,lc) {
    override fun getItemCount(): Int = 4
    val workFragment = WorkFragment()
    val contactFragment = ContactFragment()
    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0-> HomeFragment()
            1-> AboutFragment()
            2-> workFragment
            3-> contactFragment
            else-> Fragment()
        }
    }

    fun addWork(work: Work){
        workFragment.onAddWOrk(work)
    }

    fun addContact(work: Contact){
        contactFragment.onAddContact(work)
    }
}


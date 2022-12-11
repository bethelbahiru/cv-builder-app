package com.miu.bethel.cv

import Contact
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.bethel.cv.adapter.MyViewAdapter
import com.miu.bethel.cv.databinding.ActivityMainBinding
import com.miu.bethel.cv.dialog.ContactDialogCommunicator
import com.miu.bethel.cv.dialog.DialogCommunicator
import com.miu.bethel.cv.dialog.WorkDialogCommunicator
import com.miu.bethel.cv.util.AppUtils

class MainActivity : AppCompatActivity(), DialogCommunicator, WorkDialogCommunicator, ContactDialogCommunicator {

    private lateinit var binding: ActivityMainBinding
    private var sharedPref: SharedPreferences = AppUtils.getSharedPref()
    private lateinit var adapter: MyViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val theme = AppUtils.getPref(getString(R.string.saved_theme))
        val user = AppUtils.getPref(getString(R.string.login_user_key))
        if (theme != null) AppUtils.decideTheme(theme)

        showWorkDialog()
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        binding.tabLayout.setTabTextColors(Color.BLACK,Color.BLUE);
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.home_menu)
                1 -> tab.text = getString(R.string.about_me_menu)
                2 -> tab.text = getString(R.string.work_menu)
                3 -> tab.text = getString(R.string.contact_menu)
            }
        }.attach()


        user?.apply { binding.toolbar.title = "$user's CV" }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_main_logout -> {
                    finish()
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
    }


    override fun onChangeTheme(theme: String) {
        with(sharedPref.edit()) {
            putString(getString(R.string.saved_theme), theme)
            apply()
        }
        AppUtils.decideTheme(theme)
    }

    private fun showWorkDialog() {
        adapter = MyViewAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = adapter
    }

    override fun onAddWOrk(work: Work) {
        if (::adapter.isInitialized) {
            adapter.addWork(work)
        } else {
            showWorkDialog()
            adapter.addWork(work)
        }
    }

    override fun onAddContact(work: Contact) {
        if (::adapter.isInitialized) {
            adapter.addContact(work)
        } else {
            showWorkDialog()
            adapter.addContact(work)
        }
    }

}
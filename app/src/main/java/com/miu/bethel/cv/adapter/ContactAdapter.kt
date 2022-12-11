package com.miu.bethel.cv.adapter


import Contact
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.bethel.cv.R
import com.miu.bethel.cv.Work


class ContactAdapter(val context: Context, val workList: MutableList<Contact>) :
    RecyclerView.Adapter<BaseViewHolder?>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        val itemEvents: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_work, viewGroup, false)
        return AssignedTasksViewHolder(itemEvents)
    }

    fun addContact(contact: Contact){
        workList.add(contact)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
        baseViewHolder.onBind(i)
    }

    override fun getItemCount(): Int = workList.size

    inner class AssignedTasksViewHolder(view: View?) : BaseViewHolder(view) {
        var type: TextView? = view?.findViewById(R.id.rv_work_title)
        var image: ImageView? = view?.findViewById(R.id.rv_work_logo)
        var link: TextView? = view?.findViewById(R.id.rv_work_position)

        @SuppressLint("NotifyDataSetChanged")
        override fun onBind(position: Int) {
            super.onBind(position)
            val product = workList[position]

            image?.setBackgroundResource(product.image)
            type?.text = product.type
            link?.text = product.link
        }
    }
}
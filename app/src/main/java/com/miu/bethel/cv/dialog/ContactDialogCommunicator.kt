package com.miu.bethel.cv.dialog

import Contact
import com.miu.bethel.cv.Work

interface ContactDialogCommunicator {
    fun onAddContact(work: Contact)
}
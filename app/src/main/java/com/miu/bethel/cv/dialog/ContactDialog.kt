import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.miu.bethel.cv.R
import com.miu.bethel.cv.dialog.ContactDialogCommunicator

class ContactDialog : DialogFragment() {
    private lateinit var communicator: ContactDialogCommunicator
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_contact, null)

            builder.setView(view).apply {
                view.findViewById<Button>(R.id.btn_cancel)?.setOnClickListener {
                    dismiss()
                }

                view.findViewById<Button>(R.id.btn_add)?.setOnClickListener {
                    val title = view.findViewById<EditText>(R.id.et_dialog_company).text.toString().trim()
                    val position = view.findViewById<EditText>(R.id.et_dialog_position).text.toString().trim()
                    validate(title, position)
                    communicator.onAddContact(Contact(title, position, R.drawable.ic_work_placeholder))
                    dismiss()
                }

            }
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.something_went_wrong))

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun validate(title: String, position: String){
        if(title.isEmpty()){
            Toast.makeText(context, "Enter title", Toast.LENGTH_LONG).show()
            return
        }
        if(position.isEmpty()){
            Toast.makeText(context, "Enter position", Toast.LENGTH_LONG).show()
            return
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = context as ContactDialogCommunicator
    }
}
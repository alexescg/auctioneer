package github.com.alexescg.auctioneer.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.model.User

/**
 *
 *
 * @author alex
 * @since 5/28/17.
 */
class ContactListAdapter(val contactList: List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val user: User = contactList[position]
        (holder as ContactHolder).bind(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater
                .from(parent!!.context)
                .inflate(R.layout.item_contact, parent, false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    private class ContactHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView? = null
        var emailText: TextView? = null
        var userImage: CircleImageView? = null

        init {
            nameText = view.findViewById(R.id.contact_name) as TextView
            emailText = view.findViewById(R.id.contact_email) as TextView
            userImage = view.findViewById(R.id.contact_image) as CircleImageView
        }

        fun bind(user: User) {
            nameText!!.text = user.name
            emailText!!.text = user.email
            Picasso.with(view.context).load(user.avatar).fit().into(userImage)
        }
    }

}
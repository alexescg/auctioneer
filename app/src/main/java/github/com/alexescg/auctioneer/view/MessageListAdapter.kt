package github.com.alexescg.auctioneer.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.model.Message
import github.com.alexescg.auctioneer.util.DateUtil

/**
 *
 *
 * @author alex
 * @since 5/27/17.
 */
class MessageListAdapter(val messageList: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_MESSAGE_SENT: Int = 1
        private val VIEW_TYPE_MESSAGE_RECEIVED: Int = 2
    }

    override fun getItemViewType(position: Int): Int {
        val message: Message = messageList[position]
        if (message.user.id.equals("mi id")) {
            return VIEW_TYPE_MESSAGE_SENT
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater
                    .from(parent!!.context)
                    .inflate(R.layout.item_message_sent, parent, false)
            return SentMessageHolder(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater
                    .from(parent!!.context)
                    .inflate(R.layout.item_message_else, parent, false)
            return ReceivedMessageHolder(view)
        }
        return null!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val message: Message = messageList[position]
        when (holder!!.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT ->
                (holder as SentMessageHolder).bind(message)

            VIEW_TYPE_MESSAGE_RECEIVED ->
                (holder as ReceivedMessageHolder).bind(message)
            else ->
                Log.d("error", "fallo asignando tipo de vista")
        }
    }

    private class ReceivedMessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var messageText: TextView? = null
        var timestampText: TextView? = null

        init {
            messageText = view.findViewById(R.id.else_text_message_body) as TextView
            timestampText = view.findViewById(R.id.else_text_message_time) as TextView
        }

        fun bind(message: Message) {
            messageText!!.text = message.text
            timestampText!!.text = DateUtil.formatDateToTime(message.createdAt!!)
        }
    }

    private class SentMessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var messageText: TextView? = null
        var timestampText: TextView? = null

        init {
            messageText = view.findViewById(R.id.me_text_message_body) as TextView
            timestampText = view.findViewById(R.id.me_text_message_time) as TextView
        }

        fun bind(message: Message) {
            messageText!!.text = message.text
            timestampText!!.text = DateUtil.formatDateToTime(message.createdAt!!)
        }
    }


}
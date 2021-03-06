package github.com.alexescg.auctioneer.view

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.api.RestClient
import github.com.alexescg.auctioneer.api.messages.MessageService
import github.com.alexescg.auctioneer.model.Message
import github.com.alexescg.auctioneer.model.User
import kotlinx.android.synthetic.main.activity_message_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MessageListActivity : AppCompatActivity() {

    var recipient: User? = null
    val messageService: MessageService = RestClient.createService(MessageService::class.java)
    var messagesList: MutableList<Message> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)
        recipient = intent.extras["to"] as User
        recipient!!.id = intent.extras["userId"] as String
        title = recipient!!.name
        initViews()
        loadMessages()

    }

    private fun initViews() {
        recyclerview_message_list.layoutManager = LinearLayoutManager(this)
        recyclerview_message_list.adapter = MessageListAdapter(messagesList, this)

        button_chatbox_send.setOnClickListener {
            val message: Message = Message(edittext_chatbox.text.toString(), to = recipient!!)
            messageService.sendMessage(message).enqueue(object : Callback<Message> {
                override fun onFailure(call: Call<Message>?, t: Throwable?) {
                    Log.d("message create", t.toString())
                }

                override fun onResponse(call: Call<Message>?, response: Response<Message>?) {
                    if (response!!.isSuccessful) {
                        Log.d("message created", response.body()!!.toString())
                        messagesList.add(response.body()!!)
                        recyclerview_message_list.adapter.notifyDataSetChanged()
                        edittext_chatbox.setText("")

                    }
                }

            })
        }
    }

    private fun loadMessages() {
        val preferences: SharedPreferences = applicationContext.getSharedPreferences("Preferences", 0)
        Log.d("el mensaje es de: ", preferences.getString("user", ""))
        Log.d("el mensaje es para: ", recipient!!.id!!)
        val messagesCall: Call <MutableList <Message>> = messageService.getMessages(
                preferences.getString("user", ""),
                recipient!!.id!!
        )
        messagesCall.enqueue(object : Callback<MutableList<Message>> {

            override fun onResponse(call: Call<MutableList<Message>>?,
                                    response: Response<MutableList<Message>>?) {
                if (response!!.isSuccessful) {
//                    for (message: Message in response.body()!!.data) {
//                        Log.d("Mensaje: ", message.toString())
//                    }
                    messagesList.addAll(response.body()!!)
                    recyclerview_message_list.adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MutableList<Message>>?, t: Throwable?) {
                Log.d("mensajes", t!!.message)
            }
        })
    }


}

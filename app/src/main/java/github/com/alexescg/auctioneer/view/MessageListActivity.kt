package github.com.alexescg.auctioneer.view

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

class MessageListActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)
        Log.d("user", intent.getParcelableExtra<User>("user").toString())
        initViews()
        loadMessages()
    }

    private fun initViews() {
        recyclerview_message_list.layoutManager = LinearLayoutManager(this)
        recyclerview_message_list.adapter = MessageListAdapter(emptyList<Message>(), this)
    }

    private fun loadMessages() {
        val messageService: MessageService = RestClient.createService(MessageService::class.java)
        val messagesCall: Call<ApiResponse<List<Message>>> = messageService.getMessages()
        messagesCall.enqueue(object : Callback<ApiResponse<List<Message>>> {

            override fun onResponse(call: Call<ApiResponse<List<Message>>>?,
                                    response: Response<ApiResponse<List<Message>>>?) {
                if (response!!.isSuccessful) {
                    recyclerview_message_list.adapter = MessageListAdapter(response.body()!!.data, this@MessageListActivity)
                    recyclerview_message_list.adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<Message>>>?, t: Throwable?) {
                Log.d("mensajes", t!!.message)
            }
        })
    }
}

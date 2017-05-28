package github.com.alexescg.auctioneer.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.api.RestClient
import github.com.alexescg.auctioneer.api.auth.JwtAuthenticator
import github.com.alexescg.auctioneer.api.messages.MessageService
import github.com.alexescg.auctioneer.model.JsonWebToken
import github.com.alexescg.auctioneer.model.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mTextMessage!!.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mTextMessage!!.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mTextMessage!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextMessage = findViewById(R.id.message) as TextView
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val messageService: MessageService = RestClient.createService(MessageService::class.java)
        val call: Call<ApiResponse<List<Message>>> = messageService.getMessages()
        call.enqueue(object : Callback<ApiResponse<List<Message>>> {
            override fun onResponse(call: Call<ApiResponse<List<Message>>>?, response: Response<ApiResponse<List<Message>>>?) {
                if (response!!.isSuccessful) {
                    for (message: Message in response.body()!!.data) {
                        Log.d("message", message.toString())
                    }
                } else {
                    Log.d("error /authentication", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<Message>>>?, t: Throwable?) {
                Log.e("error", t.toString())

//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }

}

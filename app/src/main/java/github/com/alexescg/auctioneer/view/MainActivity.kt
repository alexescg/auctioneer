package github.com.alexescg.auctioneer.view

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.model.User
import github.com.alexescg.auctioneer.view.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), ChatsFragment.OnFragmentInteractionListener, ContactFragment.OnListFragmentInteractionListener {

    private var fragment: Fragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_contacts -> {
                fragment = ContactFragment()
//                val messagesIntent: Intent = Intent(baseContext, MessageListActivity::class.java)
//                startActivity(messagesIntent)
//                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_chats -> {
                fragment = ChatsFragment()
            }

            R.id.navigation_settings -> {
                fragment = ChatsFragment()
            }
        }
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment).commit();
        true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onListFragmentInteraction(item: User) {

    }

    override fun onFragmentInteraction(uri: Uri?) {
        //lo puedo dejar vacio
    }

}

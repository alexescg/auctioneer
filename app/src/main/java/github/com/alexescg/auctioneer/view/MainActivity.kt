package github.com.alexescg.auctioneer.view

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChatsFragment.OnFragmentInteractionListener, ContactFragment.OnListFragmentInteractionListener {

    private var fragment: Fragment? = null
    private var contactFragment: ContactFragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_contacts -> {
                if (contactFragment == null) {
                    contactFragment = ContactFragment()
                }
                fragment = contactFragment
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
        Toast.makeText(this, "Success $item", Toast.LENGTH_SHORT).show();
    }

    override fun onFragmentInteraction(uri: Uri?) {
        Toast.makeText(this, "Success $uri", Toast.LENGTH_SHORT).show();

    }

}

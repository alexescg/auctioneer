package github.com.alexescg.auctioneer.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import github.com.alexescg.auctioneer.R
import github.com.alexescg.auctioneer.api.ApiResponse
import github.com.alexescg.auctioneer.api.RestClient
import github.com.alexescg.auctioneer.api.user.UserService
import github.com.alexescg.auctioneer.model.User
import kotlinx.android.synthetic.main.fragment_contact_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class ContactFragment : Fragment() {
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: RecyclerView = inflater!!
                .inflate(R.layout.fragment_contact_list, container, false) as RecyclerView

        rootView.layoutManager = LinearLayoutManager(activity)
        rootView.adapter = ContactListAdapter(emptyList<User>())
        loadContacts()
        return rootView
    }

    private fun loadContacts() {
        val userService: UserService = RestClient.createService(UserService::class.java)
        val userCall: Call<ApiResponse<List<User>>> = userService.getUsers()
        userCall.enqueue(object : Callback<ApiResponse<List<User>>> {

            override fun onResponse(call: Call<ApiResponse<List<User>>>?,
                                    response: Response<ApiResponse<List<User>>>?) {
                if (response!!.isSuccessful) {
                    val me: String = activity.getSharedPreferences("Preferences", 0).getString("user", "")

                    val contacts: List<User> = response.body()!!.data.filter {
                        it.id != me
                    }.sortedBy(User::name)
                    list.adapter = ContactListAdapter(contacts)
                    list.adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<User>>>?, t: Throwable?) {
                Log.d("usuarios", t!!.message)
            }
        })
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context as OnListFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: User)
    }
}

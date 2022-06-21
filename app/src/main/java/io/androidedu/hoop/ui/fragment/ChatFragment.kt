package io.androidedu.hoop.ui.fragment

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.androidedu.hoop.R
import io.androidedu.hoop.adapter.ChatAdapter
import io.androidedu.hoop.db.Db
import io.androidedu.hoop.entity.ChatEntity
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlin.concurrent.thread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChatFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var db: Db? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        db = Db.getInstance(requireContext())

        thread(start = true) {
            db!!.chatDao().insertChat(
                ChatEntity(
                    "Angela",
                    "Hey! I'm Angela",
                    "Yesterday",
                    R.drawable.ic_girl1
                )
            )

            db!!.chatDao().insertChat(
                ChatEntity(
                    "Bill",
                    "Hey! I'm Bill",
                    "Yesterday",
                    R.drawable.ic_man1
                )
            )

            db!!.chatDao().insertChat(
                ChatEntity(
                    "George",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                    ,
                    "Yesterday",
                    R.drawable.ic_man2
                )
            )

            db!!.chatDao().insertChat(
                ChatEntity("My brother", "Hi! What's up?", "14:57", R.drawable.ic_man3)
            )

            db!!.chatDao().insertChat(
                ChatEntity(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                    "15:45",
                    R.drawable.ic_man4
                )
            )

            db!!.chatDao().insertChat(
                ChatEntity("My mom", "Are you OK?", "23:43", R.drawable.ic_girl1)
            )

            db!!.chatDao().insertChat(
                ChatEntity(
                    "Android Bootcamp",
                    "Android <3 Kotlin",
                    "23:45",
                    R.drawable.ic_man5
                )
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = object : LinearLayoutManager(context, RecyclerView.VERTICAL, false) {

        }

        val adapter = ChatAdapter { chatEntity ->

            thread {

                db?.chatDao()?.deleteItem(chatEntity)
            }

            "${chatEntity.personName} deleted!".showToast()
        }

        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        recycler_view_chat.layoutAnimation = animation
        recycler_view_chat.layoutManager = layoutManager
        recycler_view_chat.adapter = adapter

        db?.chatDao()!!.getAllItem().observe(viewLifecycleOwner, Observer<List<ChatEntity>> {

            adapter.setNewItemList(it!!)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_chats, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.new_group -> Toast.makeText(context, "New Group is selected!", Toast.LENGTH_LONG).show()
            R.id.new_broadcasts -> Toast.makeText(context, "New Broadcasts is selected!", Toast.LENGTH_LONG).show()
            R.id.whatsapp_web -> Toast.makeText(context, "WhatsApp Web is selected!", Toast.LENGTH_LONG).show()
            R.id.starred_messages -> Toast.makeText(context, "Starred Messages is selected!", Toast.LENGTH_LONG).show()
            R.id.settings -> Toast.makeText(context, "Settings is selected!", Toast.LENGTH_LONG).show()
            R.id.search -> Toast.makeText(context, "Make a search", Toast.LENGTH_LONG).show()

        }

        return true
    }

    private fun String.showToast() {

        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

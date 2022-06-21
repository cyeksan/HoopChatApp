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
import io.androidedu.hoop.adapter.CallAdapter
import io.androidedu.hoop.db.Db
import io.androidedu.hoop.entity.CallEntity
import kotlinx.android.synthetic.main.fragment_call.*
import kotlin.concurrent.thread

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var db: Db? = null

class CallFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        db = Db.getInstance(requireContext())

        thread(start = true) {
            db!!.callDao().insertCall(
                CallEntity(
                    "Robin",
                    R.drawable.ic_girl1,
                    "23 March, 08:47",
                    "phone",
                    "out"
                )
            )

            db!!.callDao().insertCall(
                CallEntity(
                    "Barney",
                    R.drawable.ic_man2,
                    "23 May, 08:47",
                    "phone",
                    "in"
                )
            )

            db!!.callDao().insertCall(
                CallEntity(
                    "Ted",
                    R.drawable.ic_man1,
                    "11 April, 13:46",
                    "video",
                    "in"
                )
            )

            db!!.callDao().insertCall(
                CallEntity(
                    "Marshall",
                    R.drawable.ic_man4,
                    "9 April, 15:00",
                    "phone",
                    "out"
                )
            )

            db!!.callDao().insertCall(
                CallEntity(
                    "Lily",
                    R.drawable.ic_girl1,
                    "1 April, 19:38",
                    "video",
                    "in"
                )
            )

            db!!.callDao().insertCall(
                CallEntity(
                    "Stella",
                    R.drawable.ic_girl1,
                    "17 March, 11:25",
                    "video",
                    "out"
                )
            )

            db!!.callDao().insertCall(
                CallEntity(
                    "Tommy",
                    R.drawable.ic_man5,
                    "4 March, 17:27",
                    "phone",
                    "out"
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
        return inflater.inflate(R.layout.fragment_call, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = object : LinearLayoutManager(context, RecyclerView.VERTICAL, false) {

        }

        val adapter = CallAdapter { callEntity ->

            thread {

                db?.callDao()?.deleteItem(callEntity)

            }

            "${callEntity.personName} deleted!".showToast()
        }

        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        recycler_view_call.layoutAnimation = animation
        recycler_view_call.layoutManager = layoutManager
        recycler_view_call.adapter = adapter

        db?.callDao()!!.getAllItem().observe(viewLifecycleOwner, Observer<List<CallEntity>> {

            adapter.setNewItemList(it!!)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_calls, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.search -> Toast.makeText(context, "MAKE SEARCH", Toast.LENGTH_LONG).show()
            R.id.clear_call_log -> Toast.makeText(context, "Clear call log is clicked", Toast.LENGTH_LONG).show()
            R.id.settings -> Toast.makeText(context, "Settings is clicked", Toast.LENGTH_LONG).show()
        }
        return true
    }

    private fun String.showToast() {

        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CallFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

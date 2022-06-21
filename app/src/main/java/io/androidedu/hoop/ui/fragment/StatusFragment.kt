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
import io.androidedu.hoop.adapter.StatusAdapter
import io.androidedu.hoop.db.Db
import io.androidedu.hoop.entity.StatusEntity
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.android.synthetic.main.fragment_status.*
import kotlin.concurrent.thread

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var db: Db? = null

class StatusFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        db =
            Db.getInstance(requireContext())

        thread(start = true) {
            db!!.statusDao().insertStatus(
                StatusEntity("Ömer", "Today, 13:49", R.drawable.ic_man4)
            )

            db!!.statusDao().insertStatus(
                StatusEntity("Özgür", "Today, 12:13", R.drawable.ic_man1)
            )

            db!!.statusDao().insertStatus(
                StatusEntity(
                    "Deniz", "Today, 10:45", R.drawable.ic_man2
                )
            )

            db!!.statusDao().insertStatus(
                StatusEntity("Melis", "Today, 09:37", R.drawable.ic_girl1)
            )

            db!!.statusDao().insertStatus(
                StatusEntity(
                    "Ali", "Yesterday, 21:32", R.drawable.ic_man3
                )
            )

            db!!.statusDao().insertStatus(
                StatusEntity("Ahmet", "Yesterday, 19:10", R.drawable.ic_man5)
            )

            db!!.statusDao().insertStatus(
                StatusEntity("Veli", "Yesterday, 16:01", R.drawable.ic_man1)
            )
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = object : LinearLayoutManager(context, RecyclerView.VERTICAL, false) {

        }

        val adapter = StatusAdapter { statusEntity ->

            thread {

                db?.statusDao()?.deleteItem(statusEntity)
            }

            "${statusEntity.personName} deleted!".showToast()
        }

        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        recycler_view_status.layoutAnimation = animation
        recycler_view_status.layoutManager = layoutManager
        recycler_view_status.adapter = adapter

        db?.statusDao()!!.getAllItem().observe(viewLifecycleOwner, Observer<List<StatusEntity>> {

            adapter.setNewItemList(it!!)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_status, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.search -> Toast.makeText(context, "MAKE SEARCH", Toast.LENGTH_LONG).show()
            R.id.privacy_policy -> Toast.makeText(context, "Privacy policy is clicked", Toast.LENGTH_LONG).show()
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
            StatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

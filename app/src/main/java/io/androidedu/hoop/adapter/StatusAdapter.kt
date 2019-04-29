package io.androidedu.hoop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import io.androidedu.hoop.R
import io.androidedu.hoop.entity.StatusEntity

class StatusAdapter(
    private var statusList: List<StatusEntity>? = null,
    private val onClickListener: (statusEntity: StatusEntity) -> Unit
) : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatusViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.recycler_view_status_item, p0, false)
        StatusViewHolder(itemView)

        return StatusViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return if (statusList != null) {

            statusList!!.size

        } else {
            0
        }
    }

    override fun onBindViewHolder(p0: StatusViewHolder, p1: Int) {
        val viewHolder = StatusViewHolder(p0.itemView)

        statusList?.let {
            viewHolder.bindTo(it[p1], onClickListener)
        }
    }

    inner class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgPerson: CircleImageView = itemView.findViewById(R.id.imgPerson)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val date: TextView = itemView.findViewById(R.id.date)

        fun bindTo(statusEntity: StatusEntity, onClickListener: (statusEntity: StatusEntity) -> Unit) {

            imgPerson.setImageResource(statusEntity.profilePicture)
            name.text = statusEntity.personName
            date.text = statusEntity.date

            itemView.setOnClickListener {

                onClickListener(statusEntity)
            }

        }
    }

    fun setNewItemList(statusList: List<StatusEntity>) {

        this.statusList = statusList

        notifyDataSetChanged()
    }


}
package io.androidedu.hoop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import io.androidedu.hoop.R
import io.androidedu.hoop.entity.CallEntity

class CallAdapter(
    private var callList: List<CallEntity>? = null,
    private val onClickListener: (callEntity: CallEntity) -> Unit
) : RecyclerView.Adapter<CallAdapter.CallViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CallViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.recycler_view_call_item, p0, false)
        CallViewHolder(itemView)

        return CallViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return if (callList != null) {

            callList!!.size

        } else {

            0
        }
    }

    override fun onBindViewHolder(p0: CallViewHolder, p1: Int) {
        val viewHolder = CallViewHolder(p0.itemView)
        callList?.let {
            viewHolder.bindTo(it[p1], onClickListener)
        }
    }


    inner class CallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgPerson: CircleImageView = itemView.findViewById(R.id.imgPerson)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val inOut: ImageView = itemView.findViewById(R.id.inOut)
        private val callType: ImageView = itemView.findViewById(R.id.callType)

        fun bindTo(callEntity: CallEntity, onClickListener: (callEntity: CallEntity) -> Unit) {

            imgPerson.setImageResource(callEntity.profilePicture)
            name.text = callEntity.personName
            date.text = callEntity.date

            when {
                callEntity.missedType == "in" -> inOut.setImageResource(R.drawable.ic_call_received_black_24dp)
                callEntity.missedType == "out" -> inOut.setImageResource(R.drawable.ic_call_made_black_24dp)
                else -> inOut.visibility = View.GONE
            }

            when {
                callEntity.callType == "phone" -> callType.setImageResource(R.drawable.ic_phone_black_24dp)
                callEntity.callType == "video" -> callType.setImageResource(R.drawable.ic_videocam_black_24dp)
                else -> inOut.visibility = View.INVISIBLE
            }

            itemView.setOnClickListener {

                onClickListener(callEntity)
            }

        }
    }

    fun setNewItemList(callList: List<CallEntity>) {

        this.callList = callList

        notifyDataSetChanged()
    }

}
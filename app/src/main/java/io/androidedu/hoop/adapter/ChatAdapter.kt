package io.androidedu.hoop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import io.androidedu.hoop.R
import io.androidedu.hoop.entity.ChatEntity

class ChatAdapter(
    private var chatList: List<ChatEntity>? = null,
    private val onClickListener: (chatEntity: ChatEntity) -> Unit
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.recycler_view_chat_item, p0, false)
        ChatViewHolder(itemView)

        return ChatViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return if (chatList != null) {

            chatList!!.size

        } else {
            0
        }

    }

    override fun onBindViewHolder(p0: ChatViewHolder, p1: Int) {

        val viewHolder = ChatViewHolder(p0.itemView)

        chatList?.let {
            viewHolder.bindTo(it[p1], onClickListener)
        }
    }


    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgPerson: CircleImageView = itemView.findViewById(R.id.imgPerson)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val message: TextView = itemView.findViewById(R.id.message)
        private val date: TextView = itemView.findViewById(R.id.date)

        fun bindTo(chatEntity: ChatEntity, onClickListener: (chatEntity: ChatEntity) -> Unit) {

            imgPerson.setImageResource(chatEntity.profilePicture)
            name.text = chatEntity.personName
            message.text = chatEntity.message
            date.text = chatEntity.date

            itemView.setOnClickListener {

                onClickListener(chatEntity)
            }

        }
    }

    fun setNewItemList(chatList: List<ChatEntity>) {

        this.chatList = chatList

        notifyDataSetChanged()
    }
}
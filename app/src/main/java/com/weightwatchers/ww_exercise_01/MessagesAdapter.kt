package com.weightwatchers.ww_exercise_01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weightwatchers.ww_exercise_01.databinding.MessageItemsBinding
import com.weightwatchers.ww_exercise_01.model.Message

class MessagesAdapter(
    val messages: MutableList<Message>,
    private val selectedMessageItem: (Message) -> Unit = {}
) :
    RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MessageItemsBinding.inflate(layoutInflater, parent, false)
        return MessagesViewHolder(binding, selectedMessageItem)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.bindView(messages[position])
    }

    class MessagesViewHolder(
        private val binding: MessageItemsBinding,
        private val selectedMessageItem: (Message) -> Unit = {}
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(message: Message) {
            binding.message = message

            binding.card.setOnClickListener {
                selectedMessageItem.invoke(message)
            }
        }
    }
}

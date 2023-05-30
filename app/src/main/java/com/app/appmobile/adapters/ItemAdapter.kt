package com.app.appmobile.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.appmobile.databinding.ItemRecyclerBinding
import com.app.appmobile.models.Item
import com.app.appmobile.models.Task

class ItemAdapter(context: Context, val onClickListener: ItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val items: ArrayList<Task> = ArrayList()

    interface ItemClickListener {
        fun onItemClickListener(item: Task, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAll(taskList: List<Task>) {
        if (taskList.isNotEmpty()) {
            items.clear()
            items.addAll(taskList)
            notifyDataSetChanged()
        }
    }

    fun updateItem(position: Int, text: String) {
        items[position].text = text
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(items[position], position)
    }

    inner class ViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: Task
        private var itemPosition: Int = 0
        private val itemText= binding.itemText
        private val itemId = binding.itemId
        private val done = binding.checkBoxDone
        private val container = binding.root

        fun bind(item: Task, position: Int) {
            this.item = item
            itemPosition = position

            if (!TextUtils.isEmpty(item.text)) {
                itemText.text = item.text
                itemId.text = item.id
                done.isChecked = item.done
            }

            container.setOnClickListener {
                onClickListener.onItemClickListener(item, position)
            }
        }
    }
}
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidui.R
import com.example.androidui.recyclerview.payload.MyPhoto

class MyPhotoAdapter(private val items: List<MyPhoto>) : RecyclerView.Adapter<MyPhotoAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    companion object {
        private const val PAYLOAD_NAME = "name"
        private const val PAYLOAD_COUNT = "count"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        Glide.with(holder.tvPhoto)
            .load(item.url)
            .into(holder.tvPhoto)
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            // 处理部分更新
            for (payload in payloads) {
                when (payload) {
                    PAYLOAD_NAME -> {
                        holder.tvName.text = items[position].name
                    }

                    PAYLOAD_COUNT -> {
                        holder.tvCount.text = items[position].count.toString()
                    }
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun updateCount(position: Int, count: Int) {
        val item = items[position]
        item.count = count
        notifyItemChanged(position, "count")
    }

    fun updateName(position: Int, name: String) {
        val item = items[position]
        item.name = name
        notifyItemChanged(position, "name")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPhoto: ImageView = itemView.findViewById<ImageView>(R.id.tv_photo)
        val tvName: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        val tvCount: TextView = itemView.findViewById<TextView>(R.id.tv_count)
        val btnCount: Button = itemView.findViewById<Button>(R.id.btn_count)
        val btnName: Button = itemView.findViewById<Button>(R.id.btn_name)

        init {
            btnCount.setOnClickListener {
                val position = bindingAdapterPosition
                onItemClickListener?.onItemCountClick(position, items[position])
            }
            btnName.setOnClickListener {
                val position = bindingAdapterPosition
                onItemClickListener?.onItemNameClick(position, items[position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemCountClick(position: Int, item: MyPhoto)
        fun onItemNameClick(position: Int, item: MyPhoto)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}
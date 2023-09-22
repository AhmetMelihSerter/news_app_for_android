import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappforandroid.databinding.NewsCardViewBinding
import com.example.newsappforandroid.product.model.ArticlesModel

class ArticleListAdapter(
    private val onItemClickListener: (item: ArticlesModel) -> Unit,
) : ListAdapter<ArticlesModel, ArticleListAdapter.ModelViewHolder>(ArticlesModelComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = NewsCardViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    object ArticlesModelComparator : DiffUtil.ItemCallback<ArticlesModel>() {
        override fun areItemsTheSame(oldItem: ArticlesModel, newItem: ArticlesModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticlesModel, newItem: ArticlesModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class ModelViewHolder(private val binding: NewsCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(value: ArticlesModel) {
            with(binding) {
                item = value
                binding.cardView.setOnClickListener {
                    onItemClickListener.invoke(value)
                }
            }
        }
    }
}
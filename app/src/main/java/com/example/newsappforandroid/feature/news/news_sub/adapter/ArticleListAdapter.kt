import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappforandroid.R
import com.example.newsappforandroid.feature._model.ArticlesModel

class ArticleListAdapter(private val context: Context) : RecyclerView.Adapter<ArticleListAdapter.ModelViewHolder>() {

    private var articleList = mutableListOf<ArticlesModel>()

    fun postValue(value: List<ArticlesModel>) {
        articleList.clear()
        articleList.addAll(value)
    }

    class ModelViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val titleNews = view.findViewById<TextView>(R.id.title_news)
        private val descriptionNews = view.findViewById<TextView>(R.id.description_news)
        private val imageNews = view.findViewById<ImageView>(R.id.image_news)

        fun bindItems(item: ArticlesModel) {
            titleNews.text = item.title
            descriptionNews.text = item.description
            Glide
                .with(context)
                .load(item.urlToImage)
                .into(imageNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card_view, parent, false)
        return ModelViewHolder(view, context)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(articleList[position])
    }
}
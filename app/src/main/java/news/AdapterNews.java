package news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appdatban.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import location.OnClickLocation;
import menu.AdapterMenu;
import menu.Menu;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.Viewhoder> {
    List<News> newsList;
    Context context;
    OnClickNews onClickNews;

    public AdapterNews(List<News> menuList, Context context) {
        this.newsList = menuList;
        this.context = context;
    }

    public void setOnClickNews(OnClickNews onClickNews) {
        this.onClickNews = onClickNews;
    }

    @NonNull
    @Override
    public AdapterNews.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news,parent,false);
        AdapterNews.Viewhoder viewhoder = new AdapterNews.Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        News news = newsList.get(position);
        Glide.with(context)
                .load(news.getIcon())
                .into(holder.imgNews);
        holder.tvTitleNews.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNews.OnClickItemNews(news);
            }
        });
    }
    public void updateNews(List<News> news ) {
        newsList = news;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        ImageButton imgNews;
        TextView tvTitleNews;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgNews= itemView.findViewById(R.id.img_News);
            tvTitleNews = itemView.findViewById(R.id.tv_TitleNews);

        }
    }
}

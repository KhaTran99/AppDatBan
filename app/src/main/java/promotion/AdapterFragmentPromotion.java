package promotion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appdatban.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFragmentPromotion extends RecyclerView.Adapter<AdapterFragmentPromotion.Viewhoder> {
    List<Promotion> promotionList;
    Context context;
    onClickFragmentPromotion onClickFragmentPromotion;

    public AdapterFragmentPromotion(List<Promotion> promotionList, Context context) {
        this.promotionList = promotionList;
        this.context = context;
    }

    public void setOnClickFragmentPromotion(promotion.onClickFragmentPromotion onClickFragmentPromotion) {
        this.onClickFragmentPromotion = onClickFragmentPromotion;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.iteam_fragment_promotion,parent,false);
        AdapterFragmentPromotion.Viewhoder viewhoder = new AdapterFragmentPromotion.Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {

        Promotion promotion = promotionList.get(position);
        Glide.with(context)
                .load(promotion.getIcon())
                .into(holder.imgPromotion);
        holder.tvTitle.setText(promotion.getTitle());
        holder.tvContent.setText(promotion.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFragmentPromotion.onClickItem(position , promotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        ImageView imgPromotion;
        TextView tvTitle,tvContent;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgPromotion = itemView.findViewById(R.id.img_FragmentPromotion);
            tvTitle = itemView.findViewById(R.id.tvTitle_FragmentPromotion);
            tvContent =itemView.findViewById(R.id.tvContent_FragmentPromotion);
        }
    }
}

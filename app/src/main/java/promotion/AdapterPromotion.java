package promotion;

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

import java.util.List;

public class AdapterPromotion extends RecyclerView.Adapter<AdapterPromotion.ViewHoder> {
List<Promotion> promotionList;
Context context;
onClickFragmentPromotion clickFragmentPromotion;



    public AdapterPromotion(List<Promotion> promotionList, Context context) {
        this.promotionList = promotionList;
        this.context = context;
    }

    public void setClickFragmentPromotion(promotion.onClickFragmentPromotion clickFragmentPromotion) {
        this.clickFragmentPromotion = clickFragmentPromotion;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_promotion,parent,false);
        AdapterPromotion.ViewHoder viewhoder = new AdapterPromotion.ViewHoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {

        Promotion promotion = promotionList.get(position);
        Glide.with(context)
                .load(promotion.getIcon())
                .into(holder.imgPromotion);
        holder.tvTitle.setText(promotion.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFragmentPromotion.onClickItem(position,promotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        ImageButton imgPromotion;
        TextView tvTitle;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imgPromotion=itemView.findViewById(R.id.img_Promotion);
            tvTitle=itemView.findViewById(R.id.tv_TitlePromotion);
        }
    }
}

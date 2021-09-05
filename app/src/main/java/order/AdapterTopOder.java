package order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appdatban.R;

import java.util.List;

import notification.AdapterNotification;
import promotion.Promotion;

public class AdapterTopOder extends RecyclerView.Adapter<AdapterTopOder.Viewhoder> {
    List<Promotion> promotionList;
    Context context;

    public AdapterTopOder(List<Promotion> promotionList, Context context) {
        this.promotionList = promotionList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_oder,parent,false);
        AdapterTopOder.Viewhoder viewhoder = new AdapterTopOder.Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Promotion promotion = promotionList.get(position%promotionList.size());

        Glide.with(context)
                .load(promotion.getIcon())
                .into(holder.imgTop);

    }

    @Override
    public int getItemCount() {
        return promotionList==null?0:Integer.MAX_VALUE;
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView imgTop;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgTop = itemView.findViewById(R.id.img_Top_Oder);
        }
    }
}

package menu;

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

import promotion.AdapterFragmentPromotion;

public class AdapterFragmentMenu extends RecyclerView.Adapter<AdapterFragmentMenu.Viewhoder> {
List<Menu> menuList;
Context context;

    public AdapterFragmentMenu(List<Menu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_fragment_menu,parent,false);
        AdapterFragmentMenu.Viewhoder viewhoder = new AdapterFragmentMenu.Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Menu menu = menuList.get(position);
        Glide.with(context)
                .load(menu.getIcon())
                .into(holder.imgMenu);
        holder.tvName.setText(menu.getName());

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class Viewhoder extends  RecyclerView.ViewHolder {
        ImageView imgMenu;
        TextView tvName;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgMenu = itemView.findViewById(R.id.img_FragmentMenu);
            tvName = itemView.findViewById(R.id.tv_FragmentMenu);
        }
    }
}

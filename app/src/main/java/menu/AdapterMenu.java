package menu;

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

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.Viewhoder> {
    List<Menu> menuList;
    Context context;
    OnClickMenu onClickMenu;

    public void setOnClickMenu(OnClickMenu onClickMenu) {
        this.onClickMenu = onClickMenu;
    }

    public AdapterMenu(List<Menu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu,parent,false);
        Viewhoder viewhoder = new Viewhoder(view);
       return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Menu menu = menuList.get(position);
       Glide.with(context)
               .load(menu.getIcon())
               .into(holder.imgMenu);
      holder.tvName.setText(menu.getName());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onClickMenu.onClick(menu);
          }
      });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageButton imgMenu;
        TextView tvName;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgMenu = itemView.findViewById(R.id.img_Menu);
            tvName = itemView.findViewById(R.id.tv_NameMenu);
        }
    }


//List<Menu> menuList;
//Context context;
//
//    public AdapterMenu(List<Menu> menuList, Context context) {
//        this.menuList = menuList;
//        this.context = context;
//    }
//    @Override
//    public int getItemCount() {
//        return menuList.size();
//    }
//
//
//
//    @NonNull
//    @Override
//    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.item_menu,parent,false);
//        Viewhoder viewhoder = new Viewhoder(view);
//        return viewhoder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
//
//       Menu menu = menuList.get(position);
//       Picasso.with(context)
//               .load(menu.getIcon())
//               .into(holder.imgMenu);
//       holder.tvName.setText(menu.getName());
//    }
//
//
//
//    public class Viewhoder extends RecyclerView.ViewHolder{
//       ImageButton imgMenu;
//       TextView tvName;
//        public Viewhoder(@NonNull View itemView) {
//            super(itemView);
//            imgMenu= itemView.findViewById(R.id.img_Menu);
//            tvName = itemView.findViewById(R.id.tv_NameMenu);
//
//        }
//    }
}

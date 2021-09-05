package notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appdatban.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.Viewhoder> {
    List<Notification>notificationList;
    onClickNotification onClickNotification;
    Context context;

    public AdapterNotification(List<Notification> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    public void setOnClickNotification(notification.onClickNotification onClickNotification) {
        this.onClickNotification = onClickNotification;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_notification,parent,false);
        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {

        Notification notification = notificationList.get(position);
        Glide.with(context)
                .load(notification.getIcon())
                .into(holder.imgNotification);
        holder.tvTitle.setText(notification.getTitle());
        if(notification.getStatus()== 1) {
            holder.tvCheck.setVisibility(View.GONE);
        }
        else if(notification.getStatus()==0) {
            holder.tvCheck.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNotification.onClickItem(position,notification);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        ImageView imgNotification;
        TextView tvTitle,tvCheck;
        RelativeLayout rlNotification;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            rlNotification = itemView.findViewById(R.id.rl_Notification);
            imgNotification = itemView.findViewById(R.id.img_Notification);
            tvTitle = itemView.findViewById(R.id.tv_TitleNotification);
            tvCheck = itemView.findViewById(R.id.tv_check);
        }
    }
}

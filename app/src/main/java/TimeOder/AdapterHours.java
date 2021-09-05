package TimeOder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatban.R;

import java.util.List;

import promotion.AdapterFragmentPromotion;

public class AdapterHours extends RecyclerView.Adapter<AdapterHours.Viewhoder> {
    List<Hours>hoursList;
    OnClickHouse onClickHouse;

    public void setOnClickHouse(OnClickHouse onClickHouse) {
        this.onClickHouse = onClickHouse;
    }

    public AdapterHours(List<Hours> hoursList) {
        this.hoursList = hoursList;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_hours_oder,parent,false);
        AdapterHours.Viewhoder viewhoder = new AdapterHours.Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Hours hours = hoursList.get(position);
        holder.tvHours.setText(hours.getHoursOfDay());
        holder.tvHoursCheck.setText(hours.getHoursOfDay());
        if(hours.getStatus()== 1) {
            holder.rlCheck.setVisibility(View.VISIBLE);
        }
        else {
            holder.rlCheck.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHouse.OnClickItem(position,hours);
//                if(hours.getStatus()== 1) {
//                    holder.rlCheck.setVisibility(View.VISIBLE);
//                }
//                if(holder.rlCheck.getVisibility()== View.INVISIBLE ) {
//                    holder.rlCheck.setVisibility(View.VISIBLE);
//                }
//                else {
//                    holder.rlCheck.setVisibility(View.INVISIBLE);
//                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return hoursList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        TextView tvHours;
        TextView tvHoursCheck;
        RelativeLayout rlCheck;


        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            tvHours = itemView.findViewById(R.id.tv_item_hours_oder);
            tvHoursCheck = itemView.findViewById(R.id.tv_item_hours_oder_check);
            rlCheck = itemView.findViewById(R.id.rlCheck);
        }
    }
}

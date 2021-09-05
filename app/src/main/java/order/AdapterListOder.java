package order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatban.R;

import java.util.List;

public class AdapterListOder extends RecyclerView.Adapter<AdapterListOder.ViewHoder> {
    List<Oder> oderList;

    public AdapterListOder(List<Oder> oderList) {
        this.oderList = oderList;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_oder,parent,false);
        AdapterListOder.ViewHoder viewhoder = new AdapterListOder.ViewHoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Oder oder = oderList.get(position);
        holder.tvId.setText("#"+oder.getId());
        holder.tvLocation.setText(oder.getLocation());
        holder.tvAmount.setText(""+oder.getAmount());
        holder.tvTime.setText(oder.getHours()+" "+oder.getDay());
        if(oder.getStatus()== 0) {
            holder.tvStatus.setText("Hoàn tất");
        }
        else if(oder.getStatus()== 1) {
            holder.tvStatus.setText("Đang xử lý");
        }
        else if(oder.getStatus()== 2) {
            holder.tvStatus.setText("Đã hủy");
        }
        else if(oder.getStatus()== 4) {
            holder.tvStatus.setText("Chờ xác nhận");
        }
        else if(oder.getStatus()== 3) {
            holder.tvStatus.setText("Thành công");
        }
        holder.tvBill.setText("$: "+oder.getTotalBill());

    }

    @Override
    public int getItemCount() {
        return oderList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView tvId,tvLocation,tvStatus,tvAmount,tvTime,tvBill;


        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdOder);
            tvLocation = itemView.findViewById(R.id.tvLocationOder);
            tvAmount = itemView.findViewById(R.id.tvAmountOder);
            tvTime = itemView.findViewById(R.id.tvTimeOder);
            tvBill = itemView.findViewById(R.id.tvBillOder);
            tvStatus = itemView.findViewById(R.id.tvStatusOder);


        }
    }
}

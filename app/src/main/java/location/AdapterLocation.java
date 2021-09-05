package location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appdatban.R;

import java.util.List;

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.ViewHoder> {
List<Location> locationList;
Context context;
OnClickLocation onClickLocation;

    public AdapterLocation(List<Location> locationList, Context context) {
        this.locationList = locationList;
        this.context = context;
    }

    public void setOnClickLocation(OnClickLocation onClickLocation) {
        this.onClickLocation = onClickLocation;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_location,parent,false);
        AdapterLocation.ViewHoder viewhoder = new AdapterLocation.ViewHoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Location location = locationList.get(position);
        Glide.with(context)
                .load(location.getIcon())
                .into(holder.imgLocation);
        holder.tvName.setText(location.getName().toString());
        holder.btnDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLocation.onClickDatCho(location);
            }
        });

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHoder  extends  RecyclerView.ViewHolder{
        ImageButton imgLocation;
        TextView tvName;
        Button btnDatCho;



        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            btnDatCho = itemView.findViewById(R.id.btnDatCho);
            imgLocation = itemView.findViewById(R.id.img_Location);
            tvName = itemView.findViewById(R.id.tv_NameLocation);
        }
    }
}

package promotion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appdatban.MainActivity;
import com.example.appdatban.databinding.PromotionFragmentBinding;

public class FragmentPromotion extends Fragment {
    AdapterFragmentPromotion adapterPromotion;
    public static FragmentPromotion newInstance() {

        Bundle args = new Bundle();

        FragmentPromotion fragment = new FragmentPromotion();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PromotionFragmentBinding binding = PromotionFragmentBinding.inflate(inflater,container,false);
//        <------Menu-------->
        adapterPromotion = new AdapterFragmentPromotion(MainActivity.promotionList,getContext());
        LinearLayoutManager layoutManagerPromotion = new LinearLayoutManager(binding.rlPromotion.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rlPromotion.setLayoutManager(layoutManagerPromotion);
        binding.rlPromotion.setAdapter(adapterPromotion);
        adapterPromotion.setOnClickFragmentPromotion(new onClickFragmentPromotion() {
            @Override
            public void onClickItem(int position, Promotion promotion) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle(promotion.getTitle())
                        .setMessage(promotion.getContent()+"\nCode: "+promotion.getCode())
                        .setPositiveButton("Sử dụng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Sử dụng", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Quay lại", Toast.LENGTH_LONG).show();

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });



        return binding.getRoot();
    }
}

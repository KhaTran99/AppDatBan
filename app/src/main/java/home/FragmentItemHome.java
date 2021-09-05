package home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appdatban.databinding.ItemHomeFragmentBinding;

import menu.Menu;
import menu.OnClickMenu;

public class FragmentItemHome extends Fragment implements OnClickMenu {
    Menu menuItem;
    ItemHomeFragmentBinding binding;
    public static FragmentItemHome newInstance() {

        Bundle args = new Bundle();

        FragmentItemHome fragment = new FragmentItemHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = ItemHomeFragmentBinding.inflate(inflater,container,false);
//        Glide.with(getContext())
//                .load(menuItem.getIcon())
//                .into(binding.imgFragmentItemHome);
//        binding.tvTitleFragmentItemHome.setText(menuItem.getName().toString());
//        binding.tvContentFragmentItemHome.setText(menuItem.getContent());
        return binding.getRoot();
    }

    @Override
    public void onClick(Menu menu) {
        menuItem = menu;
        Glide.with(getContext())
                .load(menuItem.getIcon())
                .into(binding.imgFragmentItemHome);
        binding.tvTitleFragmentItemHome.setText(menuItem.getName().toString());
        binding.tvContentFragmentItemHome.setText(menuItem.getContent());

    }
}

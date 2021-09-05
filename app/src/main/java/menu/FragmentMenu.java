package menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appdatban.MainActivity;
import com.example.appdatban.databinding.MenuFragmentBinding;

public class FragmentMenu extends Fragment {
    AdapterFragmentMenu adapterMenu;
    public static FragmentMenu newInstance() {

        Bundle args = new Bundle();

        FragmentMenu fragment = new FragmentMenu();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MenuFragmentBinding binding = MenuFragmentBinding.inflate(inflater,container,false);
        //        <------Menu-------->
        adapterMenu = new AdapterFragmentMenu(MainActivity.menuList, getContext());
        LinearLayoutManager layoutManagerMenu = new LinearLayoutManager(binding.rlFragmentMenu.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rlFragmentMenu.setLayoutManager(layoutManagerMenu);
        binding.rlFragmentMenu.setAdapter(adapterMenu);
        return binding.getRoot();
    }
}

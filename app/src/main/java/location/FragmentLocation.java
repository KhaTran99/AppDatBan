package location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatban.MainActivity;
import com.example.appdatban.databinding.LocationFragmentBinding;

public class  FragmentLocation extends Fragment {
    AdapterLocation adapterLocation;
    LinearLayoutManager layoutManagerLocation;
    public static FragmentLocation newInstance() {

        Bundle args = new Bundle();

        FragmentLocation fragment = new FragmentLocation();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LocationFragmentBinding binding = LocationFragmentBinding.inflate(inflater,container,false);
        adapterLocation = new AdapterLocation(MainActivity.locationList, getContext());
        layoutManagerLocation = new LinearLayoutManager(binding.rlFragmentLocation.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rlFragmentLocation.setLayoutManager(layoutManagerLocation);
        binding.rlFragmentLocation.setAdapter(adapterLocation);


        return binding.getRoot();
    }
}

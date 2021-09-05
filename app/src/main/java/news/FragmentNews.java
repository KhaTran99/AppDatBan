package news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appdatban.R;
import com.example.appdatban.databinding.NewsFragmentBinding;

import home.FragmentHome;

public class FragmentNews extends Fragment implements OnClickNews {
    NewsFragmentBinding binding;
    public static FragmentNews newInstance() {

        Bundle args = new Bundle();

        FragmentNews fragment = new FragmentNews();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = NewsFragmentBinding.inflate(inflater,container,false);
        binding.btnBackNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentHome.newInstance());
            }
        });
        return binding.getRoot();

    }

    @Override
    public void OnClickItemNews(News news) {
        Glide.with(getContext())
                .load(news.getIcon())
                .into(binding.imgFragmentNews);
        binding.tvTitleFragmentNews.setText(news.getTitle());
        binding.tvContentFragmentNews.setText(news.getContent());

    }
    public void GetFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.Frame, fragment).commit();
    }
}

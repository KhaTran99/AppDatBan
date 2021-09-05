package home;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.appdatban.MainActivity;
import com.example.appdatban.R;
import com.example.appdatban.databinding.HomeFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import location.AdapterLocation;
import location.FragmentLocation;
import location.Location;
import location.OnClickLocation;
import menu.AdapterMenu;
import menu.FragmentMenu;
import menu.Menu;
import menu.OnClickMenu;
import news.AdapterNews;
import news.FragmentNews;
import news.News;
import news.OnClickNews;
import order.MainActivityOrder;
import promotion.AdapterPromotion;
import promotion.FragmentPromotion;
import promotion.Promotion;
import promotion.onClickFragmentPromotion;
import retrofit.ApiUtils;
import retrofit.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sqlhelper.SQLHotMenu;
import sqlhelper.SQLLocation;
import sqlhelper.SQLMenu;
import sqlhelper.SQLNews;
import sqlhelper.SQLPromotion;

public class FragmentHome extends Fragment {
    public static AdapterMenu adapterMenu;
    public static AdapterMenu adapterHotMenu;
    public static AdapterPromotion adapterPromotion;
    public static AdapterLocation adapterLocation;
    public static AdapterNews adapterNews;
    public HomeFragmentBinding binding;
    LinearLayoutManager layoutManagerPromotion;
    LinearLayoutManager layoutManagerLocation;
    LinearLayoutManager layoutManagerMenu;
    LinearLayoutManager layoutManagerHotMenu;
    LinearLayoutManager layoutManagerNews;
    private SOService mService;

    Timer timer;

    TimerTask timerTask;
    int position;


    public static FragmentHome newInstance() {

        Bundle args = new Bundle();

        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        mService = ApiUtils.getSOService();
        binding.tvCity.setText(MainActivity.NAMECITY);


        binding.tvXemThem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentMenu.newInstance());

            }
        });
        binding.tvXemThem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentMenu.newInstance());

            }
        });
        binding.tvXemThem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentLocation.newInstance());

            }
        });
        binding.tvXemThem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentMenu.newInstance());

            }
        });

        binding.btnDealHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentPromotion.newInstance());
            }
        });
        binding.btnUuDaiThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentPromotion.newInstance());
            }
        });
        binding.btnThanhVienMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentPromotion.newInstance());
            }
        });
        binding.btnUuDaiDoiTac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFragment(FragmentPromotion.newInstance());
            }
        });
        mService.getJsonTitleHome().enqueue(new Callback<List<ItemHome>>() {
            @Override
            public void onResponse(Call<List<ItemHome>> call, Response<List<ItemHome>> response) {

                MainActivity.itemHomeList = response.body();
                binding.tvTitle1.setText(MainActivity.itemHomeList.get(0).getTitle());
                binding.tvTitle2.setText(MainActivity.itemHomeList.get(1).getTitle());
                binding.tvTitle3.setText(MainActivity.itemHomeList.get(2).getTitle());
                binding.tvTitle4.setText(MainActivity.itemHomeList.get(3).getTitle());
                binding.tvTitle5.setText(MainActivity.itemHomeList.get(4).getTitle());
            }

            @Override
            public void onFailure(Call<List<ItemHome>> call, Throwable t) {

            }
        });


        //       <------Promotion-------->
        MainActivity.sqlPromotion = new SQLPromotion(getContext());
        MainActivity.promotionList = MainActivity.sqlPromotion.getAllPromotion();
        mService.getJsonPromotion().enqueue(new Callback<List<Promotion>>() {
            @Override
            public void onResponse(Call<List<Promotion>> call, Response<List<Promotion>> response) {
                if(MainActivity.promotionList.size() ==0 ) {
                    for(int i = 0 ; i< response.body().size(); i++) {
                        MainActivity.sqlPromotion.insertPromotion(response.body().get(i));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Promotion>> call, Throwable t) {
            }
        });

        adapterPromotion = new AdapterPromotion(MainActivity.promotionList, getContext());
        layoutManagerPromotion = new LinearLayoutManager(binding.rlItemHome4.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rlItemHome4.setLayoutManager(layoutManagerPromotion);
        binding.rlItemHome4.setAdapter(adapterPromotion);
        adapterPromotion.setClickFragmentPromotion(new onClickFragmentPromotion() {
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

        if (MainActivity.promotionList != null) {
            position = Integer.MAX_VALUE / 2;
            binding.rlItemHome4.scrollToPosition(position);
        }
        SnapHelper snapHelperPromotion = new LinearSnapHelper();
        snapHelperPromotion.attachToRecyclerView(binding.rlItemHome4);
        binding.rlItemHome4.smoothScrollBy(5, 0);
        binding.rlItemHome4.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (timer != null && timerTask != null) {
                    timerTask.cancel();
                    timer.cancel();
                    timer = null;
                    timerTask = null;
                    position = layoutManagerPromotion.findFirstCompletelyVisibleItemPosition();
                }
            }
        });
        // <-------Location------>
        MainActivity.sqlLocation = new SQLLocation(getContext());
        MainActivity.locationList = MainActivity.sqlLocation.getAllLocation();
        mService.getJsonLocation().enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {

                if(MainActivity.locationList.size()== 0) {

                    for(int i = 0 ; i< response.body().size();i++) {
                        MainActivity.sqlLocation.insertLocation(response.body().get(i));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });
        MainActivity.locationList = MainActivity.sqlLocation.getAllLocation();
        adapterLocation = new AdapterLocation(MainActivity.locationList, getContext());
                layoutManagerLocation = new LinearLayoutManager(binding.rlItemHome3.getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.rlItemHome3.setLayoutManager(layoutManagerLocation);
                binding.rlItemHome3.setAdapter(adapterLocation);
                adapterLocation.setOnClickLocation(new OnClickLocation() {
                    @Override
                    public void onClickDatCho(Location location) {
                        Uri gmmIntentUri = Uri.parse(location.getAddress().toString());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                });

        if (MainActivity.locationList != null) {
            position = Integer.MAX_VALUE / 2;
            binding.rlItemHome3.scrollToPosition(position);
        }
        SnapHelper snapHelperLocation = new LinearSnapHelper();
        snapHelperLocation.attachToRecyclerView(binding.rlItemHome3);
        binding.rlItemHome3.smoothScrollBy(5, 0);
        binding.rlItemHome3.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (timer != null && timerTask != null) {
                    timerTask.cancel();
                    timer.cancel();
                    timer = null;
                    timerTask = null;
                    position = layoutManagerLocation.findFirstCompletelyVisibleItemPosition();
                }
            }
        });

        //<----------Menu--------->
        MainActivity.sqlMenu = new SQLMenu(getContext());
        MainActivity.menuList = MainActivity.sqlMenu.getAllMenu();
        mService.getJsonMenu().enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(MainActivity.menuList.size() == 0 ) {
                    MainActivity.menuList = response.body();
                    adapterMenu = new AdapterMenu(MainActivity.menuList, getContext());
                    layoutManagerMenu = new LinearLayoutManager(binding.rlItemHome2.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.rlItemHome2.setLayoutManager(layoutManagerMenu);
                    binding.rlItemHome2.setAdapter(adapterMenu);
                    for(int i = 0 ; i< response.body().size(); i ++ ) {
                        MainActivity.sqlMenu.insertMenu(response.body().get(i));


                    }

                }


            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {

            }
        });
        adapterMenu = new AdapterMenu(MainActivity.menuList, getContext());
        layoutManagerMenu = new LinearLayoutManager(binding.rlItemHome2.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rlItemHome2.setLayoutManager(layoutManagerMenu);
        binding.rlItemHome2.setAdapter(adapterMenu);
        adapterMenu.setOnClickMenu(new OnClickMenu() {
            @Override
            public void onClick(Menu menu) {
                GetFragment(FragmentItemHome.newInstance());
            }
        });

        if (MainActivity.menuList != null) {
            position = Integer.MAX_VALUE / 2;
            binding.rlItemHome2.scrollToPosition(position);
        }
        SnapHelper snapHelperMenu = new LinearSnapHelper();
        snapHelperMenu.attachToRecyclerView(binding.rlItemHome2);
        binding.rlItemHome2.smoothScrollBy(5, 0);
        binding.rlItemHome2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (timer != null && timerTask != null) {
                    timerTask.cancel();
                    timer.cancel();
                    timer = null;
                    timerTask = null;
                    position = layoutManagerMenu.findFirstCompletelyVisibleItemPosition();
                }
            }
        });
        //<----------HotMenu--------->
        MainActivity.sqlHotMenu = new SQLHotMenu(getContext());
        MainActivity.hotMenuList = MainActivity.sqlHotMenu.getAllHotMenu();
        mService.getJsonHotMenu().enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(MainActivity.hotMenuList.size()== 0) {
                MainActivity.hotMenuList = response.body();
                adapterHotMenu = new AdapterMenu(MainActivity.hotMenuList, getContext());
                layoutManagerHotMenu = new LinearLayoutManager(binding.rlItemHome1.getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.rlItemHome1.setLayoutManager(layoutManagerHotMenu);
                binding.rlItemHome1.setAdapter(adapterHotMenu);
                     for(int i = 0 ; i < response.body().size(); i ++) {
                    MainActivity.sqlHotMenu.insertHotMenu(response.body().get(i));
                     }

                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {

            }
        });
        adapterHotMenu = new AdapterMenu(MainActivity.hotMenuList, getContext());
        layoutManagerHotMenu = new LinearLayoutManager(binding.rlItemHome1.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rlItemHome1.setLayoutManager(layoutManagerHotMenu);
        binding.rlItemHome1.setAdapter(adapterHotMenu);
        adapterMenu.setOnClickMenu(new OnClickMenu() {
            @Override
            public void onClick(Menu menu) {
                GetFragment(FragmentItemHome.newInstance());
            }
        });
        if (MainActivity.hotMenuList != null) {
            position = Integer.MAX_VALUE / 2;
            binding.rlItemHome1.scrollToPosition(position);
        }
        SnapHelper snapHelperHotMenu = new LinearSnapHelper();
        snapHelperHotMenu.attachToRecyclerView(binding.rlItemHome1);
        binding.rlItemHome1.smoothScrollBy(5, 0);
        binding.rlItemHome1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (timer != null && timerTask != null) {
                    timerTask.cancel();
                    timer.cancel();
                    timer = null;
                    timerTask = null;
                    position = layoutManagerHotMenu.findFirstCompletelyVisibleItemPosition();
                }
            }
        });
        MainActivity.sqlNews = new SQLNews(getContext());
        MainActivity.newsList = MainActivity.sqlNews.getAllNews();
        mService.getJsonNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(MainActivity.newsList.size()==0 ) {
                    MainActivity.newsList = response.body();
                    adapterNews = new AdapterNews(MainActivity.newsList, getContext());
                    layoutManagerNews = new LinearLayoutManager(binding.rlItemHome5.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.rlItemHome5.setLayoutManager(layoutManagerNews);
                    binding.rlItemHome5.setAdapter(adapterNews);
                    for(int i = 0 ;i<response.body().size();i++) {
                        MainActivity.sqlNews.insertNews(response.body().get(i));
                    }
                }
                adapterNews = new AdapterNews(MainActivity.newsList, getContext());
                layoutManagerNews = new LinearLayoutManager(binding.rlItemHome5.getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.rlItemHome5.setLayoutManager(layoutManagerNews);
                binding.rlItemHome5.setAdapter(adapterNews);
                adapterNews.setOnClickNews(new OnClickNews() {
                    @Override
                    public void OnClickItemNews(News news) {
                        GetFragment(FragmentNews.newInstance());

                    }
                });
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

        if (MainActivity.newsList != null) {
            position = Integer.MAX_VALUE / 2;
            binding.rlItemHome5.scrollToPosition(position);
        }
        SnapHelper snapHelperNews = new LinearSnapHelper();
        snapHelperNews.attachToRecyclerView(binding.rlItemHome5);
        binding.rlItemHome5.smoothScrollBy(5, 0);
        binding.rlItemHome5.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (timer != null && timerTask != null) {
                    timerTask.cancel();
                    timer.cancel();
                    timer = null;
                    timerTask = null;
                    position = layoutManagerNews.findFirstCompletelyVisibleItemPosition();
                }
            }
        });


        return binding.getRoot();
    }

    public void GetFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.Frame, fragment).commit();
    }

}

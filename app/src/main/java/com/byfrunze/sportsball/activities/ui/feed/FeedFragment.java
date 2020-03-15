package com.byfrunze.sportsball.activities.ui.feed;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.activities.ui.webnews.WebInfo;
import com.byfrunze.sportsball.viewmodels.FeedViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment {
    @BindView(R.id.recyclerViewFeed)
    RecyclerView recyclerViewFeed;
    @BindView(R.id.editTextSearch)
    MaterialEditText editText;
    private Context context;
    private RecyclerAdapter recyclerAdapter;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        FeedViewModel viewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        ButterKnife.bind(this, view);
        viewModel.init();
        LifecycleOwner owner = getViewLifecycleOwner();
        recyclerViewFeed.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewFeed.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter();
        if(!editText.getText().toString().isEmpty()) {
            viewModel.setJsonNewsMutableLiveData(editText.getText().toString());
            viewModel.getJsonNewsMutableLiveData().observe(owner, prod -> {
                recyclerAdapter.setArticles(prod.getArticles());
                recyclerViewFeed.setAdapter(recyclerAdapter);

            });
        }
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()) {
                    viewModel.setJsonNewsMutableLiveData(s.toString());
                    viewModel.getJsonNewsMutableLiveData().observe(owner, prod -> {
                        recyclerAdapter.setArticles(prod.getArticles());
                        recyclerViewFeed.setAdapter(recyclerAdapter);

                    });
                }
            }
        });
        Bundle bundle = new Bundle();

        recyclerAdapter.setOnClickListener((el, pos)->{
            bundle.putString("URL", el.getUrl());
            navController.navigate(R.id.webInfo, bundle);

        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}

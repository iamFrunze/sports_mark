package com.byfrunze.sportsball.activities.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.logicapp.SwipeToDeleteCallback;
import com.byfrunze.sportsball.models.ModelOfPerson;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class HistoryFragment extends Fragment {

    @BindView(R.id.recyclerViewHistory)
    RecyclerView recyclerViewHistory;

    @BindView(R.id.btnClean)
    MaterialButton btnClean;

    private Realm mRealm;
    private ModelOfPerson modelLastSaveInfo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, root);
        mRealm = Realm.getDefaultInstance();
        RealmResults<ModelOfPerson> model = mRealm.where(ModelOfPerson.class).findAll();

        HistoryAdapter adapter = new HistoryAdapter();
        adapter.setItems(model);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewHistory.setAdapter(adapter);
        modelLastSaveInfo = new ModelOfPerson();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerViewHistory);

        btnClean.setOnClickListener(v -> {
            mRealm.beginTransaction();
            modelLastSaveInfo.setupModel(Objects.requireNonNull(model.where().findAll().last()));
            model.deleteAllFromRealm();
            ModelOfPerson saveModel = mRealm.createObject(ModelOfPerson.class);
            saveModel.setupModel(modelLastSaveInfo);
            mRealm.commitTransaction();
            adapter.notifyDataSetChanged();
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
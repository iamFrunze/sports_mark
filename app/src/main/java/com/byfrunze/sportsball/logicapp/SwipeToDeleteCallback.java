package com.byfrunze.sportsball.logicapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.byfrunze.sportsball.activities.ui.history.HistoryAdapter;

import io.realm.Realm;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private HistoryAdapter adapter;

    public SwipeToDeleteCallback(HistoryAdapter adapter) {
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> adapter.deleteItem(position));

    }


}

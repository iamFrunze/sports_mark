package com.byfrunze.sportsball.activities.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byfrunze.sportsball.R;
import com.byfrunze.sportsball.models.ModelOfPerson;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private RealmResults<ModelOfPerson> modelList;

    void setItems(RealmResults<ModelOfPerson> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (getItemCount() > 2)
            modelList.deleteFromRealm(getItemCount() - position - 1);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_element, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.textViewNameHistory.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getName());
        holder.textViewDateHistory.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getDate());
        holder.textViewMarkHistory.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getMark());
        holder.textViewPointsHistory.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getPoints());

        holder.textViewNameStrength.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getStrengthName());
        holder.textViewPointsStrength.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getStrengthPoints());
        holder.textViewResStrength.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getStrengthRes());

        holder.textViewNameSpeed.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getSpeedName());
        holder.textViewPointsSpeed.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getSpeedPoints());
        holder.textViewResSpeed.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getSpeedRes());

        holder.textViewNameStamina.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getStaminaName());
        holder.textViewPointsStamina.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getStaminaPoints());
        holder.textViewResStamina.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getStaminaRes());

        holder.textViewNameWS.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getWsName());
        holder.textViewPointsWS.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getWsPoints());
        holder.textViewResWS.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getWsRes());

        holder.textViewNameAgility.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getAgilityName());
        holder.textViewPointsAgility.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getAgilityPoints());
        holder.textViewResAgility.setText(Objects.requireNonNull(modelList.get(getItemCount() - position - 1)).getAgilityRes());

        holder.linearLayoutMain.setOnClickListener(v -> {
            if (holder.linearLayoutMain.getTag().equals("true")) {
                holder.linearLayoutStrength.setVisibility(View.GONE);
                holder.linearLayoutSpeed.setVisibility(View.GONE);
                holder.linearLayoutStamina.setVisibility(View.GONE);
                holder.linearLayoutWS.setVisibility(View.GONE);
                holder.linearLayoutAgility.setVisibility(View.GONE);
                holder.linearLayoutMain.setTag("false");
            } else {
                holder.linearLayoutStrength.setVisibility(View.VISIBLE);
                holder.linearLayoutSpeed.setVisibility(View.VISIBLE);
                holder.linearLayoutStamina.setVisibility(View.VISIBLE);
                holder.linearLayoutWS.setVisibility(View.VISIBLE);
                holder.linearLayoutAgility.setVisibility(View.VISIBLE);
                holder.linearLayoutMain.setTag("true");
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNameHistory)
        TextView textViewNameHistory;
        @BindView(R.id.textViewDateHistory)
        TextView textViewDateHistory;
        @BindView(R.id.textViewPointsHistory)
        TextView textViewPointsHistory;
        @BindView(R.id.textViewMarkHistory)
        TextView textViewMarkHistory;

        @BindView(R.id.textViewNameSpeed)
        TextView textViewNameSpeed;
        @BindView(R.id.textViewResSpeed)
        TextView textViewResSpeed;
        @BindView(R.id.textViewPointsSpeed)
        TextView textViewPointsSpeed;

        @BindView(R.id.textViewNameStamina)
        TextView textViewNameStamina;
        @BindView(R.id.textViewResStamina)
        TextView textViewResStamina;
        @BindView(R.id.textViewPointsStamina)
        TextView textViewPointsStamina;

        @BindView(R.id.textViewNameWS)
        TextView textViewNameWS;
        @BindView(R.id.textViewResWS)
        TextView textViewResWS;
        @BindView(R.id.textViewPointsWS)
        TextView textViewPointsWS;

        @BindView(R.id.textViewNameAgility)
        TextView textViewNameAgility;
        @BindView(R.id.textViewResAgility)
        TextView textViewResAgility;
        @BindView(R.id.textViewPointsAgility)
        TextView textViewPointsAgility;

        @BindView(R.id.textViewNameStrength)
        TextView textViewNameStrength;
        @BindView(R.id.textViewResStrength)
        TextView textViewResStrength;
        @BindView(R.id.textViewPointsStrength)
        TextView textViewPointsStrength;

        @BindView(R.id.linearLayoutMain)
        LinearLayout linearLayoutMain;
        @BindView(R.id.linearLayoutStrength)
        LinearLayout linearLayoutStrength;
        @BindView(R.id.linearLayoutSpeed)
        LinearLayout linearLayoutSpeed;
        @BindView(R.id.linearLayoutStamina)
        LinearLayout linearLayoutStamina;
        @BindView(R.id.linearLayoutWS)
        LinearLayout linearLayoutWS;
        @BindView(R.id.linearLayoutAgility)
        LinearLayout linearLayoutAgility;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

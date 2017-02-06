package com.barbasdev.tools.rxbinding;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barbasdev.common.datalayer.model.ApiResult;

import java.util.ArrayList;
import java.util.List;

public class RxFragmentResultsAdapter<T extends ApiResult> extends RecyclerView.Adapter<RxFragmentResultsAdapter.ResultViewHolder> {

    private List<T> apiResults;

    public RxFragmentResultsAdapter() {
        apiResults = new ArrayList<>();
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setTextColor(Color.BLACK);
        return new ResultViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.textView.setText(apiResults.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return apiResults.size();
    }

    public void addApiResults(List<T> results) {
        apiResults.addAll(results);
        notifyDataSetChanged();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
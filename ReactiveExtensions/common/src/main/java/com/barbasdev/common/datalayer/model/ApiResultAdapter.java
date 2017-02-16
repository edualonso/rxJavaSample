package com.barbasdev.common.datalayer.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ApiResultAdapter<T extends ApiResult> extends RecyclerView.Adapter<ApiResultAdapter.ApiResultViewHolder> {

    private List<T> apiResults = new ArrayList<>();

    public ApiResultAdapter() {

    }

    @Override
    public ApiResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {TextView textView = new TextView(parent.getContext());
        return new ApiResultViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ApiResultViewHolder holder, int position) {
        holder.textView.setText(apiResults.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return apiResults.size();
    }

    public void initApiResults() {
        apiResults = new ArrayList<>();
    }

    public List<T> getApiResults() {
        return apiResults;
    }

    public void addApiResults(List<T> results, boolean clear) {
        if (clear) {
            apiResults.clear();
        }
        apiResults.addAll(results);
        notifyDataSetChanged();
    }

    public void clearApiResults() {
        if (apiResults.size() != 0) {
            apiResults.clear();
            notifyDataSetChanged();
        }
    }

    public static class ApiResultViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ApiResultViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
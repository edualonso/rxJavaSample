package com.barbasdev.tools.rxbinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.barbasdev.common.base.BaseFragment;
import com.barbasdev.tools.BR;
import com.barbasdev.tools.R;
import com.barbasdev.tools.databinding.FragmentProofOfConceptBinding;
import com.jakewharton.rxbinding2.view.RxView;

/**
 * Created by edu on 09/01/2017.
 */

public class ProofOfConceptFragment extends BaseFragment<ProofOfConceptViewModel, FragmentProofOfConceptBinding> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proof_of_concept, container, false);
        binding.setVariable(BR.viewModel, viewModel);

        setupRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.initResultsObservables();
        viewModel.triggerTwoObservables(RxView.clicks(binding.rxBindingButtonTwoObservables));
        viewModel.triggerMergeObservables(RxView.clicks(binding.rxBindingButtonMerge));
        viewModel.triggerZipObservables(RxView.clicks(binding.rxBindingButtonZip));
    }

    @Override
    protected void setupViewModel() {
        viewModel.setup();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewModel.getAdapter());
    }
}
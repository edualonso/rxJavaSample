package com.barbasdev.reactiveextensions.main.proofofconcept;

import android.os.Bundle;

import com.barbasdev.reactiveextensions.main.base.BaseActivity;

/**
 * Created by edu on 11/02/2017.
 */

public class ProofOfConceptActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            showFragment(instantiateFragment(this, ProofOfConceptFragment.class, getFragmentViewModel()));
        }
    }
}
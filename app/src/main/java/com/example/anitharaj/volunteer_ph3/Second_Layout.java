package com.example.anitharaj.volunteer_ph3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by natar on 4/16/2017.
 */

public class Second_Layout extends Fragment {
    View second_view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        second_view = inflater.inflate(R.layout.second_layout,container, false);
        return second_view;
    }
}

package com.example.weixin.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weixin.R;

public class MapFragment extends Fragment{
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        TextView view = root.findViewById(R.id.map);
        view.setText("我是MapFragment");
        return root;
    }

}
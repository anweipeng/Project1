package com.example.weixin.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weixin.R;
public class IndexFragment extends Fragment{
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_index, container, false);
        TextView view = root.findViewById(R.id.homePage);
        view.setText("我是IndexFragment");
        return root;
    }

}
package com.example.kh.myapplication.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kh.myapplication.Module.Information;
import com.example.kh.myapplication.Module.MyAdapterRecycle;
import com.example.kh.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Navigation_Fragment extends Fragment {
    RecyclerView recyclerView;

    public Navigation_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyAdapterRecycle adapter = new MyAdapterRecycle(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public List<Information> getData(){
        List<Information> data = new ArrayList<Information>();
        int[] icon  = new int[]{R.drawable.ic_aspect_ratio_black_24dp,R.drawable.ic_assignment_turned_in_black_24dp, R.drawable.ic_directions_bike_black_24dp, R.drawable.ic_mode_comment_black_24dp};
        String[] title = new String[]{"Mot", "Hai", "Ba", "Bon"};
        for(int i = 0 ; i <  icon.length && i < title.length; i++){
            Information information  =new Information();
            information.icon = icon[i];
            information.tittle = title[i];
            data.add(information);
        }
        return data;
    }
}

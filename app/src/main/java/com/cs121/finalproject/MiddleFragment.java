package com.cs121.finalproject;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiddleFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    ListFragment fragment3;


    public MiddleFragment() {
        // Required empty public constructor
    }

    public static MiddleFragment newInstance(String param1) {
        MiddleFragment fragment = new MiddleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int layout = R.layout.fragment_middle;

        //This is the default page
        if (mParam1.equals("regular")) {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
            if (settings.getBoolean("bydining", true)) {
                Menusview(0, null, null, null);
            } else if (settings.getBoolean("bymeal", true)) {
                Menusview(1, null, null, null);
            } else if (settings.getBoolean("byfavorites", true)) {
                int[] intarray = new int[15];
                for (int i = 0; i < intarray.length; i++) {
                    intarray[i] = 1;
                }
                Menusview(3, intarray, "search", ((MainActivity) getActivity()).getfavmenus());
            }
        }


        return inflater.inflate(layout, container, false);
    }

    public void Menusview(int choice, int[] intarray, String str, ArrayList<ArrayList<List<MenuItem>>> listdayalldiningmenu) {
        switch (choice) {
            case 0:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ByDiningHallFragment fragment = new ByDiningHallFragment();
                fragmentTransaction.replace(R.id.frag, fragment);
                fragmentTransaction.commit();
                break;
            case 1:
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();

                ByMealFragment fragment2 = new ByMealFragment();
                fragmentTransaction2.replace(R.id.frag, fragment2);
                fragmentTransaction2.commit();
                break;
            case 3:
                FragmentManager fragmentManager3 = getFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                
                fragment3 = ListFragment.newInstance(intarray, str, listdayalldiningmenu);
                if (str != null) {
                    if (str.equals("fav")) {
                        fragmentTransaction3.replace(R.id.frag, fragment3);
                    } else {
                        fragmentTransaction3.replace(R.id.frag, fragment3);
                    }
                } else {
                    fragmentTransaction3.replace(R.id.frag, fragment3);
                }
                fragmentTransaction3.addToBackStack("test");
                fragmentTransaction3.commit();
                break;
        }
    }
}



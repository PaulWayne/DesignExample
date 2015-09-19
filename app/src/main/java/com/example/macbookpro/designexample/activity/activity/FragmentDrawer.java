package com.example.macbookpro.designexample.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbookpro.designexample.R;
import com.example.macbookpro.designexample.activity.adapter.NavigationDrawerAdapter;
import com.example.macbookpro.designexample.activity.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDrawer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDrawer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDrawer extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static String TAG = FragmentDrawer.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationDrawerAdapter navigationDrawerAdapter;
    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener fragmentDrawerListener;




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDrawer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDrawer newInstance(String param1, String param2) {
        FragmentDrawer fragment = new FragmentDrawer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentDrawer() {
        // Required empty public constructor
    }

    public void setDrawerListener(FragmentDrawerListener fragmentDrawerListener) {
        this.fragmentDrawerListener = fragmentDrawerListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Récuperation du tableau String dans le fichier Value/String
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);
        navigationDrawerAdapter = new NavigationDrawerAdapter(getData(),getActivity());
        recyclerView.setAdapter(navigationDrawerAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                    fragmentDrawerListener.onDrawerItemSelected(view,position);
                    drawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    Retourne la liste des items
    public static List<NavDrawerItem> getData(){
        List<NavDrawerItem> navDrawerItems = new ArrayList<>();
    // preparing navigation drawer items
        for(int i =0;i <titles.length;i++){
            NavDrawerItem navDrawerItem = new NavDrawerItem();
            navDrawerItem.setTitle(titles[i]);
            navDrawerItems.add(navDrawerItem);
        }
        return navDrawerItems;
    }


    public void setUp(int fragmentId, DrawerLayout mdrawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        drawerLayout = mdrawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);

    }
    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
         private GestureDetector gestureDetector;
         private ClickListener clickListener;

        public RecyclerTouchListener(Context context,final  RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
             View child = rv.findChildViewUnder(e.getX(),e.getY());
             if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                 clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}

package silo.com.silo.UI.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import silo.com.silo.R;

public class MainActivity extends AppCompatActivity {

    //View Variable
    Toolbar toolbar;
    FrameLayout root;
    BottomNavigationView bottombar;
    View contentHamburger;
    ImageView contentRight;
    TextView title;
    RelativeLayout.LayoutParams fragmentparams;
    View fragmentlayout;
    FloatingActionButton fab;
    //Global Variable
    static int view_position = 0;
//    SessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        session = new SessionManager(Beranda.this);
//        String i = session.getKeyRole();

//        Toast.makeText(Beranda.this, i, Toast.LENGTH_SHORT).show();
        IntializeView();

        // Shared Data
    }


    private void IntializeView() {
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.toolbar_title);
        bottombar = findViewById(R.id.NavigationBot);

        root = findViewById(R.id.root);
        contentHamburger = findViewById(R.id.content_hamburger);
//        contentRight = findViewById(R.id.content_right);
        fragmentlayout = findViewById(R.id.scrollView2);
        fragmentparams = (RelativeLayout.LayoutParams) fragmentlayout.getLayoutParams();
        fab = findViewById(R.id.fab);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.hamburger, null);
        root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

//        registerForContextMenu(contentRight);

//        guillotineMenu.findViewById(R.id.logout_group).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                session.logoutUser();
//            }
//        });
//
//        guillotineMenu.findViewById(R.id.profile_group).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Intent intent = new Intent(Beranda.this, EditUser.class);
//                startActivity(intent);
//            }
//        });



        switch (getIntent().getIntExtra("addDialog",0)){
            case 0: bottombar.setSelectedItemId(R.id.navigation_beranda); switchfragment(R.id.navigation_beranda);break;
            case 1: bottombar.setSelectedItemId(R.id.navigation_event); switchfragment(R.id.navigation_event);break;
            case 2: bottombar.setSelectedItemId(R.id.navigation_notifications); switchfragment(R.id.navigation_notifications);break;
            case 3: bottombar.setSelectedItemId(R.id.navigation_more); switchfragment(R.id.navigation_more);break;
        }


            bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_beranda:
                            if(view_position != 0) switchfragment(R.id.navigation_beranda);
                            return true;
                        case R.id.navigation_event:
                            if(view_position != 1) switchfragment(R.id.navigation_event);
                            return true;
                        case R.id.navigation_notifications:
                            if(view_position != 2) switchfragment(R.id.navigation_notifications);
                            return true;
                        case R.id.navigation_more:
                            if(view_position != 3) switchfragment(R.id.navigation_more);
                            return true;
                    }
                    return false;
                }
            });

//        else if(session.getKeyRole().equalsIgnoreCase("Customer Service")){
//            bottombar.findViewById(R.id.navigation_sparepart).setVisibility(View.GONE);
//            bottombar.findViewById(R.id.navigation_supplier).setVisibility(View.GONE);
//
//            bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                    bottombar.findViewById(R.id.navigation_sparepart).setVisibility(View.GONE);
//                    bottombar.findViewById(R.id.navigation_supplier).setVisibility(View.GONE);
//
//
//                    switch (menuItem.getItemId()) {
//                        case R.id.navigation_beranda:
//                            if(view_position != 0) switchfragment(R.id.navigation_beranda);
//
//                            return true;
////                        case R.id.navigation_supplier:
////                            if(view_position != 1) switchfragment(R.id.navigation_supplier);
////                            return true;
////                        case R.id.navigation_sparepart:
////                            if(view_position != 2) switchfragment(R.id.navigation_sparepart);
////                            return true;
//                        case R.id.navigation_procurement:
//                            if(view_position != 3) switchfragment(R.id.navigation_procurement);
//
//                            return true;
//                        case R.id.navigation_transaction:
//                            if(view_position != 4) switchfragment(R.id.navigation_transaction);
//
//                            return true;
//
//                    }
//                    return false;
//                }
//            });
//        }
//
//        else if(session.getKeyRole().equalsIgnoreCase("Casheer")){
//            bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                    switch (menuItem.getItemId()) {
//
//                        case R.id.navigation_beranda:
//                            if(view_position != 0) switchfragment(R.id.navigation_beranda);
//                            bottombar.findViewById(R.id.navigation_sparepart).setVisibility(View.GONE);
//                            bottombar.findViewById(R.id.navigation_supplier).setVisibility(View.GONE);
//
//                            return true;
////                        case R.id.navigation_supplier:
////                            if(view_position != 1) switchfragment(R.id.navigation_supplier);
////                            return true;
////                        case R.id.navigation_sparepart:
////                            if(view_position != 2) switchfragment(R.id.navigation_sparepart);
////                            return true;
//                        case R.id.navigation_procurement:
//                            if(view_position != 3) switchfragment(R.id.navigation_procurement);
//                            bottombar.findViewById(R.id.navigation_sparepart).setVisibility(View.GONE);
//                            bottombar.findViewById(R.id.navigation_supplier).setVisibility(View.GONE);
//
//                            return true;
//                        case R.id.navigation_transaction:
//                            if(view_position != 4) switchfragment(R.id.navigation_transaction);
//                            bottombar.findViewById(R.id.navigation_sparepart).setVisibility(View.GONE);
//                            bottombar.findViewById(R.id.navigation_supplier).setVisibility(View.GONE);
//
//                            return true;
//
//                    }
//                    return false;
//                }
//            });
//        }


    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0, "Reset Balance");
        menu.add(0,v.getId(),0, "See Log");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    public void switchfragment(int id) {
        FragmentManager manager = getSupportFragmentManager();
        fragmentparams.removeRule(RelativeLayout.CENTER_IN_PARENT);
        switch (id) {
            case R.id.navigation_beranda:
                view_position = 0;
                manager.beginTransaction().replace(R.id.fragmentplace, new FragmentBeranda()).commit();
                title.setText("SILO");
                fragmentparams.addRule(RelativeLayout.CENTER_IN_PARENT);
//                contentRight.setVisibility(View.INVISIBLE);
                fab.hide();
                break;
//            case R.id.navigation_supplier:
//                view_position = 1;
//                // Fragment
//                FragmentSupplier fragmentSupplier =  new FragmentSupplier();
//                manager.beginTransaction().replace(R.id.fragmentplace,fragmentSupplier).commit();
//                fragmentparams.addRule(RelativeLayout.CENTER_IN_PARENT, 0);
//                // VIEW
//                title.setText("Data Supplier");
//                //                contentRight.setVisibility(View.INVISIBLE);
//                fab.show();
//                break;
//
//            case R.id.navigation_sparepart:
//                view_position = 2;
//                //Fragment
//                FragmentSparepart fragmentSparepart = new FragmentSparepart();
//                manager.beginTransaction().replace(R.id.fragmentplace, fragmentSparepart).commit();
//                fragmentparams.addRule(RelativeLayout.CENTER_IN_PARENT, 0);
//                //VIEW
//                title.setText("Data Sparepart");
////                 contentRight.setVisibility(View.INVISIBLE);
//                fab.show();
//                break;
//
//            case R.id.navigation_procurement:
//                view_position = 3;
//                FragmentProcurement fragmentProcurement = new FragmentProcurement();
//                manager.beginTransaction().replace(R.id.fragmentplace, fragmentProcurement).commit();
//                fragmentparams.addRule(RelativeLayout.CENTER_IN_PARENT,0);
//                title.setText("Data Pengadaan");
//
//                fab.show();
//                break;
//
//            case R.id.navigation_transaction:
//                view_position = 4;
//                FragmentTransaction fragmentTransaction = new FragmentTransaction();
//                manager.beginTransaction().replace(R.id.fragmentplace, fragmentTransaction).commit();
//                fragmentparams.addRule(RelativeLayout.CENTER_IN_PARENT,0);
//                title.setText("Data Transaksi");
//
//                fab.show();
//                break;

        }
        fragmentlayout.setLayoutParams(fragmentparams);
    }


    public void FABonCLick(View view) {
        Intent i;
        switch (view_position) {
//            case 1:
//                i = new Intent(Beranda.this, AddSupplier.class);
//                i.putExtra("simpan", -1);
//                startActivity(i);
//                break;
//
//            case 2:
//                i = new Intent(Beranda.this, AddSparepart.class);
//                i.putExtra("simpan", -1);
//                startActivity(i);
//                break;
//            case 3 :
//                i = new Intent(Beranda.this, AddProcurement.class);
//                i.putExtra("simpan", -1);
//                startActivity(i);
//                break;
//
//            case 4 :
//                i = new Intent(Beranda.this, MenuCustomer.class);
//                i.putExtra("simpan", -1);
//                startActivity(i);
//                break;
        }
    }
}

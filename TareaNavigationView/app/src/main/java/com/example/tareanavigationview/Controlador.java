package com.example.tareanavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Controlador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout DLayout;
    Toolbar toolBar;
    FragmentManager fragmentM;
    NavigationView Nviw;
    FragmentTransaction fragmentT;
    CircleImageView img;
    TextView nombreUser;
    ActionBarDrawerToggle ActToiggle;
    View view;
    private String userId="";
    private String user="";
    private String password="";
    private String photo="";
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navegador);
        Bundle bundle = this.getIntent().getExtras();
        userId=bundle.getString("UserId");
        user=bundle.getString("Usuario");
        password=bundle.getString("Password");
        photo=bundle.getString("Photo");

        toolBar=findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        DLayout=findViewById(R.id.drawerLayout);
        Nviw=findViewById(R.id.nav_view);

        Nviw.setNavigationItemSelectedListener(this);

        view=Nviw.getHeaderView(0);
        nombreUser=view.findViewById(R.id.textView_userName);
        img=view.findViewById(R.id.profile_image);
        img=view.findViewById(R.id.profile_image);

        nombreUser.setText(bundle.getString("Usuario"));
        Glide.with(this)
                .load( bundle.getString("Photo"))
                .centerCrop()
                .into(img);


        ActToiggle=new ActionBarDrawerToggle(this, DLayout,toolBar,R.string.open_drawer,R.string.close_drawer);
        DLayout.addDrawerListener(ActToiggle);
        ActToiggle.setDrawerIndicatorEnabled(true);
        ActToiggle.syncState();

        if (userId.equals("2")){
            Menu menu = Nviw.getMenu();
            MenuItem item = menu.getItem(1);
            item.setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DLayout.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId()==R.id.nav_home){
            fragmentM=getSupportFragmentManager();
            fragmentT=fragmentM.beginTransaction();
            fragmentT.replace(R.id.content,new MainFragment());
            fragmentT.commit();
        }
        else if (menuItem.getItemId()==R.id.nav_star){
            fragmentM=getSupportFragmentManager();
            fragmentT=fragmentM.beginTransaction();
            fragmentT.replace(R.id.content,new Fragment_Favoritos());
            fragmentT.commit();
        }
        else if (menuItem.getItemId()==R.id.camera){
            fragmentM=getSupportFragmentManager();
            fragmentT=fragmentM.beginTransaction();
            fragmentT.replace(R.id.content,new Fragment_fotos());
            fragmentT.commit();
        }
        else if (menuItem.getItemId()==R.id.nav_grupReloj){
            fragmentM=getSupportFragmentManager();
            fragmentT=fragmentM.beginTransaction();
            fragmentT.replace(R.id.content,new Fragment_Reloj());
            fragmentT.commit();
        }
        else if (menuItem.getItemId()==R.id.nav_grupo){
            fragmentM=getSupportFragmentManager();
            fragmentT=fragmentM.beginTransaction();
            fragmentT.replace(R.id.content,new Fragment_Contactos());
            fragmentT.commit();
        }
        return true;
    }
}
package com.agribank.echannels.introscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetstarted;
    Animation btnAnim;

    IntroViewPagerAdapter introViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);


        btnNext = findViewById(R.id.btnNext);
        btnGetstarted = findViewById(R.id.btnGetStarted);
        tabIndicator = findViewById(R.id.tabIndicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_animation);

        String lorem = "Lorem ipsum, or lipsum as it is sometimes known, is dummy text used in laying out print, graphic or web designs. The passage is attributed to an unknown typesetter";

        final List<ScreenItem> mlist = new ArrayList<>();
        mlist.add(new ScreenItem("Fresh Food", lorem, R.drawable.img1));
        mlist.add(new ScreenItem("Fast Delivery", lorem, R.drawable.img2));
        mlist.add(new ScreenItem("Easy Payment", lorem, R.drawable.img3));

        screenPager = findViewById(R.id.screenViewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mlist);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mlist.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position >= mlist.size() - 1){
                    loadLastScreen();
                }
//                Toast.makeText(IntroActivity.this, "Posotion: " + position +", size: " + mlist.size(), Toast.LENGTH_SHORT).show();
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mlist.size() -1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });

    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetstarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetstarted.setAnimation(btnAnim);
    }


}

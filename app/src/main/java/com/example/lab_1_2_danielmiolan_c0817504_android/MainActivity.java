package com.example.lab_1_2_danielmiolan_c0817504_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ViewPager2 pager;
    private TabLayout tabs;
    public static FragmentAdapter fragmentAdapter;
    public static ProductViewModel productVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productVM = new ViewModelProvider(this).get(ProductViewModel.class);

        pager = findViewById(R.id.pagerView);
        FragmentManager fm = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fm, getLifecycle());
        pager.setAdapter(fragmentAdapter);

        tabs = findViewById(R.id.tabsView);
        tabs.addTab(tabs.newTab().setText("Products"));
        tabs.addTab(tabs.newTab().setText("Editor"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    fragmentAdapter.clearEditorInputs();
                } else {
                    fragmentAdapter.isCreateMode();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        loadProducts();
    }

    private void loadProducts() {
        productVM.getAllProdut().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                fragmentAdapter.updateProductRVAdapter(products);
            }
        });
    }
}
package com.example.lab_1_2_danielmiolan_c0817504_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    private EditorFragment editorFragment = new EditorFragment();
    private ProductFragment productFragment = new ProductFragment();

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position == 0){
            return productFragment;
        }else{
            return editorFragment;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
}

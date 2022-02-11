package com.example.lab_1_2_danielmiolan_c0817504_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    public EditorFragment editorFragment = new EditorFragment();
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

    public void updateProductRVAdapter(List<Product> products) {
        productFragment.updateAdapterList(products);
    }

    public void clearEditorInputs() {
        editorFragment.clearInputs();
    }

    public void isCreateMode() {
        editorFragment.setInsertMode();
    }

    public void isUpdateMode(Product product) {
        editorFragment.setEditMode(product);
    }
}

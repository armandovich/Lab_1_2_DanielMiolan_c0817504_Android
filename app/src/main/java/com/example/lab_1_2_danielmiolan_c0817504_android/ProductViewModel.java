package com.example.lab_1_2_danielmiolan_c0817504_android;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepo;
    private LiveData<List<Product>> productTask;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepo = new ProductRepository(application);
        productTask = productRepo.getAllProduct();
    }

    public LiveData<List<Product>> getAllTask() {
        return productTask;
    }

    public void insert(Product product) {
        productRepo.insert(product);
    }
    public void update(Product product) {
        productRepo.update(product);
    }
    public void delete(Product product) {
        productRepo.delete(product);
    }
}
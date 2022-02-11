package com.example.lab_1_2_danielmiolan_c0817504_android;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        productDao = db.productDao();
        allProducts = productDao.getAll();
    }

    public void insert(Product product) {
        new AsyncInsert(productDao).execute(product);
    }

    public void update(Product product) {
        new AsyncInsert(productDao).execute(product);
    }

    public void delete(Product product) {
        new AsyncInsert(productDao).execute(product);
    }

    public LiveData<List<Product>> getAllProduct() {
        return allProducts;
    }

    private static class AsyncInsert extends AsyncTask<Product, Void, Void> {
        private ProductDao productAsyncDao;

        private AsyncInsert(ProductDao dao) {
            this.productAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Product... product) {
            productAsyncDao.insert(product[0]);
            return null;
        }
    }

    private static class AsyncUpdate extends AsyncTask<Product, Void, Void> {
        private ProductDao productAsyncDao;

        private AsyncUpdate(ProductDao dao) {
            this.productAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Product... product) {
            productAsyncDao.update(product[0]);
            return null;
        }
    }

    private static class AsyncDelete extends AsyncTask<Product, Void, Void> {
        private ProductDao productAsyncDao;

        private AsyncDelete(ProductDao dao) {
            this.productAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Product... product) {
            productAsyncDao.delete(product[0]);
            return null;
        }
    }
}

package com.example.lab_1_2_danielmiolan_c0817504_android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditorFragment extends Fragment {
    private EditText productName;
    private EditText productDescription;
    private EditText productPrice;
    private EditText productLat;
    private EditText productLong;
    private Button saveBtn;
    private Boolean isEditMode = false;
    private Product productHolder = new Product();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditorFragment newInstance(String param1, String param2) {
        EditorFragment fragment = new EditorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        productName = (EditText) view.findViewById(R.id.productNameInput);
        productDescription = (EditText) view.findViewById(R.id.productDescriptionInput);
        productPrice = (EditText) view.findViewById(R.id.productPriceInput);
        productLat = (EditText) view.findViewById(R.id.productLatInput);
        productLong = (EditText) view.findViewById(R.id.productLongInput);

        saveBtn = (Button) view.findViewById(R.id.saveProductBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct(view);
            }
        });

        if (isEditMode) {
            productName.setText(productHolder.getName());
            productDescription.setText(productHolder.getDescription());
            productPrice.setText("" + productHolder.getPrice());
            productLat.setText("" + productHolder.getLatitude());
            productLong.setText("" + productHolder.getLongitude());
        }
    }

    private void saveProduct(View view) {
        String name = productName.getText().toString();
        String description = productName.getText().toString();
        String priceStr = productPrice.getText().toString();
        String latStr = productLat.getText().toString();
        String lonStr = productLong.getText().toString();

        double price = priceStr.equals("") ? 0 : Double.parseDouble(priceStr);
        double lat = latStr.equals("") ? 0 : Double.parseDouble(latStr);
        double lon = lonStr.equals("") ? 0 : Double.parseDouble(lonStr);

        if(name.equals("")) {
            Toast.makeText(view.getContext(), "Product name can't be empty.", Toast.LENGTH_LONG).show();
            return;
        }

        if(description.equals("")) {
            Toast.makeText(view.getContext(), "Product description can't be empty.", Toast.LENGTH_LONG).show();
            return;
        }

        Product tempProduct = new Product();



        tempProduct.setName(name);
        tempProduct.setDescription(description);
        //tempProduct.setPrice(price);
        //tempProduct.setLatitude(lat);
        //tempProduct.setLongitude(lon);

        if (isEditMode) {
            tempProduct.setId(productHolder.getId());
            MainActivity.productVM.update(tempProduct);
            Toast.makeText(view.getContext(), "Product updated", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.productVM.insert(tempProduct);
            Toast.makeText(view.getContext(), "Product saved", Toast.LENGTH_SHORT).show();
            clearInputs();
        }
    }

    public void clearInputs() {
        productName.setText("");
        productDescription.setText("");
        productPrice.setText("");
        productLat.setText("");
        productLong.setText("");
    }

    public void setInsertMode() {
        isEditMode = false;
    }

    public void setEditMode(Product product) {
        isEditMode = true;
        productHolder = product;

        if (productName != null) {
            productName.setText(product.getName());
            productDescription.setText(product.getDescription());
            productPrice.setText("" + product.getPrice());
            productLat.setText("" + product.getLatitude());
            productLong.setText("" + product.getLongitude());
        }
    }
}
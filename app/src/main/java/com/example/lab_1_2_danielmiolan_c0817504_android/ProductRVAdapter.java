package com.example.lab_1_2_danielmiolan_c0817504_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRVAdapter extends ListAdapter<Product, ProductRVAdapter.ViewHolder> {
    private ProductRVAdapter.OnItemClickListener listener;

    public ProductRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(Product oldItem, Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Product oldItem, Product newItem) {
            // below line is to check the category name
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @Override
    public ProductRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rv_item, parent, false);
        return new ProductRVAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.ViewHolder holder, int position) {
        Product model = getProductAt(position);
        holder.tempID = model.getId();
        holder.nameTV.setText(model.getName());
        holder.descriptionTV.setText(model.getDescription());
        holder.priceTV.setText("$" + model.getPrice());
        holder.latitudeTV.setText("" + model.getLatitude());
        holder.longitudeTV.setText("" + model.getLongitude());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TO-DO
                Toast.makeText(view.getContext(), "Product deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Product getProductAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int tempID;
        TextView nameTV;
        TextView descriptionTV;
        TextView priceTV;
        TextView latitudeTV;
        TextView longitudeTV;
        Button deleteBtn;
        Button editBtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.productName);
            descriptionTV = itemView.findViewById(R.id.productDescription);
            priceTV = itemView.findViewById(R.id.productPrice);
            latitudeTV = itemView.findViewById(R.id.productLat);
            longitudeTV = itemView.findViewById(R.id.productLong);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product model);
    }

    public void setOnItemClickListener(ProductRVAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}

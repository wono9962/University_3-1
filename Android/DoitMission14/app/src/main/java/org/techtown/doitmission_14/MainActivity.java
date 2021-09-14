package org.techtown.doitmission_14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        final ProductAdapter adapter = new ProductAdapter();

        adapter.addItem(new Product("롱코트", "160,000", "명절 기회상품···", R.drawable.longcoat));
        adapter.addItem(new Product("빈탄 와이셔츠", "80,000", "특가상품···", R.drawable.shirts));
        adapter.addItem(new Product("조깅화", "220,000", "대박상품···", R.drawable.jogging));
        adapter.addItem(new Product("구O 썬글라스", "1,200,000", "핫딜상품···", R.drawable.sunglass));

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnProductItemClickListener(){
            @Override
            public void onItemClick(ProductAdapter.ViewHolder holder, View view, int position) {
                Product item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "이름 : " + item.getName() + "\n가격 : " + item.getPrice() +
                        "\n설명 : " + item.getInformation(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void setOnItemClickListener(OnProductItemClickListener onProductItemClickListener) {

            }

        });
    }
}
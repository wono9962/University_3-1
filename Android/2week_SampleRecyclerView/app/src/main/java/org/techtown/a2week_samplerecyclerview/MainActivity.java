package org.techtown.a2week_samplerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수", "010-1000-1000"));
        adapter.addItem(new Person("김하늘", "010-2000-2000"));
        adapter.addItem(new Person("홍길동", "010-3000-3000"));
        adapter.addItem(new Person("내이름1", "010-4000-4000"));
        adapter.addItem(new Person("내이름2", "010-4000-4000"));
        adapter.addItem(new Person("내이름3", "010-4000-4000"));
        adapter.addItem(new Person("내이름4", "010-4000-4000"));
        adapter.addItem(new Person("내이름5", "010-4000-4000"));
        adapter.addItem(new Person("내이름6", "010-4000-4000"));
        adapter.addItem(new Person("내이름7", "010-4000-4000"));
        adapter.addItem(new Person("내이름8", "010-4000-4000"));
        adapter.addItem(new Person("내이름9", "010-4000-4000"));
        adapter.addItem(new Person("내이름10", "010-4000-4000"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {

                Person item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "아이템 선택됨" + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
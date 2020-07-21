package com.dawidbula.lisyoftaskstodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    MyAdapter mMyAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Tasks.listOfTasks.size() <= 0){
            Tasks.addSomeTasks();
        }

        mListView = (ListView) findViewById(R.id.list_view_tasks);

        mMyAdapter = new MyAdapter(MainActivity.this, R.layout.list_row, Tasks.listOfTasks);
        mListView.setAdapter(mMyAdapter);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_add_or_remove, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_task:
                Intent intent = new Intent(this, add_task.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends ArrayAdapter<String> {
        ArrayList<String> array;
        private TextView text;

        public MyAdapter(@NonNull Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.array = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = null;
            LayoutInflater inflater = getLayoutInflater();
            view = inflater.inflate(R.layout.list_row, parent, false);
            final TextView textTask = (TextView) view.findViewById(R.id.text_view_task);

            textTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textTask.getText().toString().toLowerCase().equals("youtube")){
                        Intent intent = new Intent(MainActivity.this, Youtube.class);
                        startActivity(intent);
                    }
                }
            });


            Button doneButton = (Button) view.findViewById(R.id.done_task_button);

            doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(array.get(position) + " ::::::: " + position);
                    array.remove(position);
                    mMyAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Zadanie " + array.get(position) + " zostało usunięte", Toast.LENGTH_SHORT).show();
                }
            });

            textTask.setText(array.get(position));
            return view;
        }
    }
}

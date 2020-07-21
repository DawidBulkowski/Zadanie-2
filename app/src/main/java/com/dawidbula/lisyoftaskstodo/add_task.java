package com.dawidbula.lisyoftaskstodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static com.dawidbula.lisyoftaskstodo.R.string.text_view;

public class add_task extends AppCompatActivity {

    public static final int KEY_TO_NEW_TASK = 1000;

    private Button addTaskBtn;
    private EditText taskExitText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskExitText = findViewById(R.id.edit_text_task);
        addTaskBtn = findViewById(R.id.add_new_task_to_list);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskExitText.getText().toString().equals("")){
                    Toast.makeText(add_task.this, "Musisz wpisać treść zadanie by przejść do listy zadań", Toast.LENGTH_SHORT).show();
                } else {
                    Tasks.listOfTasks.add(taskExitText.getText().toString());
                    Snackbar snackbar = Snackbar.make(view, "Dodano " + taskExitText.getText().toString() + " zadanie do listy zadań!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(add_task.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                    snackbar.show();
                }
            }
        });
    }
}

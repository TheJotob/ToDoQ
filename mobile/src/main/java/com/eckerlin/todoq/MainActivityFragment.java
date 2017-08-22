package com.eckerlin.todoq;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.eckerlin.todoq.models.Task;
import com.eckerlin.todoq.models.TaskList;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private MainActivity activity;

    private TextView lblCurrentTask;
    private Button btnDelete, btnPostpone;
    private CheckBox chbDone;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();
        TaskList list = activity.getList();

        try {
            list.push(new Task("Test1"));
            list.push(new Task("Test2"));
            list.push(new Task("Test3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        lblCurrentTask = view.findViewById(R.id.lblCurrentTask);
        btnDelete = view.findViewById(R.id.deleteCurrent);

        updateView();

        btnDelete.setOnClickListener(btn -> {
            try {
                if (!activity.getList().empty())
                    activity.getList().pop();
            } catch (IOException e) {
                Log.e("ToDoQ", e.getMessage());
            }
            updateView();
        });

        return view;
    }

    public void updateView() {
        try {
            if (activity.getList().empty())
                lblCurrentTask.setText("Nothing left to do! :)");
            else
                lblCurrentTask.setText(activity.getList().peek().getText());
        } catch (IOException e) {
            lblCurrentTask.setText(e.getMessage());
        }
    }
}

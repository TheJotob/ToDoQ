package com.eckerlin.todoq;

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
import java.util.Observable;
import java.util.Observer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements Observer, View.OnClickListener {
    private MainActivity activity;

    private TaskList list;

    private TextView lblCurrentTask;
    private Button btnDelete, btnPostpone;
    private CheckBox chbDone;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();
        list = activity.getList();
        list.addObserver(this);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        lblCurrentTask = view.findViewById(R.id.lblCurrentTask);
        btnDelete = view.findViewById(R.id.deleteCurrent);
        btnDelete.setOnClickListener(this);

        btnPostpone = view.findViewById(R.id.postponeCurrent);
        btnPostpone.setOnClickListener(this);

        chbDone = view.findViewById(R.id.doneCurrent);
        chbDone.setOnClickListener(this);

        return view;
    }

    @Override
    public void update(Observable observable, Object o) {
        try {
            if (list.empty()) {
                lblCurrentTask.setText(getString(R.string.nothingToDo));
                btnDelete.setVisibility(View.INVISIBLE);
                btnPostpone.setVisibility(View.INVISIBLE);
                chbDone.setVisibility(View.INVISIBLE);
            } else {
                lblCurrentTask.setText(activity.getList().peek().getText());
                btnDelete.setVisibility(View.VISIBLE);
                btnPostpone.setVisibility(View.VISIBLE);
                chbDone.setVisibility(View.VISIBLE);
            }
            chbDone.setChecked(false);
        } catch (IOException e) {
            lblCurrentTask.setText(e.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
        try {
            if (list.empty())
                return;

            if (view.equals(btnDelete)) {
                list.pop();
            } else if (view.equals(btnPostpone)) {
                Task t = activity.getList().pop();
                list.push(t);
            } else if (view.equals(chbDone)) {
                list.pop();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

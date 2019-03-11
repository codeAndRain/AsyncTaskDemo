package com.challenge.asynctaskdemo;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class ListFragment extends Fragment {

    private AddNameTask task;

    private NamesAdapter adapter;
    private RecyclerView recyclerView;
    private String[] names = {"Add",
            "new", "names", "to", "the", "list", "New", "Names",
            "Have", "Been", "Added", "To", "The", "List", "Async", "Task", "Demo",
            "Another", "Test", "This", "Number", "NoName", "Subtraction", "So", "Random", "Duhhh", "Arrgghhh",
            "Add",
            "new", "names", "to", "the", "list", "New", "Names",
            "Have", "Been", "Added", "To", "The", "List", "Async", "Task", "Demo",
            "Another", "Test", "This", "Number", "NoName", "Subtraction", "So", "Random", "Duhhh", "Arrgghhh"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retain fragment across configuration changes (i.e screen rotations)
        setRetainInstance(true);

        adapter = new NamesAdapter();
        task = new AddNameTask(getActivity(), Arrays.asList(names), adapter);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        recyclerView = view.findViewById(R.id.names_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        if (task != null) {
            task.cancel(false);
        }
        super.onDestroy();
    }

    public static class AddNameTask extends AsyncTask<Void, String, Void> {

        List<String> names;
        NamesAdapter adapter;
        Context context;

        public AddNameTask(Context context, List<String> names, NamesAdapter adapter) {
            this.names = names;
            this.adapter = adapter;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String name : names) {
                if (isCancelled()) // stop if activity is destroyed
                    break;
                publishProgress(name);
                SystemClock.sleep(500); // wait half a second
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            adapter.addName(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Completed", Toast.LENGTH_SHORT).show();
        }
    }
}

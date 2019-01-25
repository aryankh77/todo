package com.example.home.todo_ap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList tasks;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList<TaskInfo> tasks) {
        this.context = (Context) context;
        this.tasks=tasks;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        TextView text1 = (TextView) vi.findViewById(R.id.text1);
        TextView text2 = (TextView) vi.findViewById(R.id.text2);
        TextView text3 = (TextView) vi.findViewById(R.id.text3);
        TextView text4 = (TextView) vi.findViewById(R.id.text4);
        text.setText( ((TaskInfo)tasks.get(position)).getTaskName());
        text1.setText( ((TaskInfo)tasks.get(position)).getPriority().toString());
        text2.setText( ((TaskInfo)tasks.get(position)).getComment());
        text3.setText( ((TaskInfo)tasks.get(position)).getTime());
        text4.setText( ((TaskInfo)tasks.get(position)).getDate());
        return vi;
    }
}

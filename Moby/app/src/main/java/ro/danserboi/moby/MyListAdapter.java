package ro.danserboi.moby;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    LayoutInflater mInflater;
    List<String> mItems;
    public MyListAdapter(Context context, LinkedList<String> items) {
        mInflater = LayoutInflater.from(context);
        this.mItems = items;
    }
    @NonNull
    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, viewGroup, false);
        return new MyViewHolder(mItemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.MyViewHolder viewHolder, int i) {
        String mCurrent = mItems.get(i);
        viewHolder.mItem.setText(mCurrent);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mItem;
        MyListAdapter mAdapter;
        public MyViewHolder(@NonNull View itemView, MyListAdapter adapter) {
            super(itemView);
            mItem = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
        }
    }
}

package vn.binhld.dynamiclistrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyListItemViewHolder> {

    private List<String> localData;

    public MyRecyclerViewAdapter(List<String> localData) {
        this.localData = localData;
    }

    @NonNull
    @Override
    public MyListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyListItemViewHolder holder = new MyListItemViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {
        return localData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyListItemViewHolder holder, int position) {
        holder.itemText.setText(localData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class MyListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemText;

        public MyListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.tv_item_text);
        }
    }
}

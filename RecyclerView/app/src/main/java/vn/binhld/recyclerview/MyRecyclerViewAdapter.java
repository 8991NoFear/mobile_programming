package vn.binhld.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = MyRecyclerViewAdapter.class.getSimpleName();
    private int mNumberItems;
    private MyListItemClickListener mClickListener;

    public MyRecyclerViewAdapter(int numberOfItems, MyListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mClickListener = listener;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     */
    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    /**
     * Cache of the children views for a list item.
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;
        TextView txt;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         */
        public MyViewHolder(View itemView) {
            super(itemView); // this.itemView = itemView

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            txt = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);

            itemView.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         */
        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
            txt.setText("ViewHolder instance: " + String.valueOf(listIndex));

            Context context = itemView.getContext();
            int color = ColorUtils.getViewHolderBackgroundColorFromInstance(context, listIndex);
            itemView.setBackgroundColor(color); // fill data into view
        }

        /**
         * Called whenever a user clicks on an item in the list.
         */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickListener.onListItemClickListener(position); // invoke interface method
            Log.d(TAG, "click #" + String.valueOf(position));
        }
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface MyListItemClickListener {
        public void onListItemClickListener(int clickedItemIndex);
    }
}

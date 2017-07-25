package udemy.android.rauluco.fruitworld.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import udemy.android.rauluco.fruitworld.R;
import udemy.android.rauluco.fruitworld.models.Fruit;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener itemClickListner;

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView name;
        private TextView description;
        private TextView amount;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            amount = (TextView) itemView.findViewById(R.id.amount);
            image = (ImageView) itemView.findViewById(R.id.fruit_image);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete_fruit:
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_amount:
                    fruits.get(getAdapterPosition()).resetAmount();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Fruit chosenItem = fruits.get(getAdapterPosition());
            menu.setHeaderTitle(chosenItem.getName());
            menu.setHeaderIcon(chosenItem.getIcon());

            MenuInflater menuInflater = activity.getMenuInflater();
            menuInflater.inflate(R.menu.fruit_context_menu, menu);

            for(int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setOnMenuItemClickListener(this);
            }
        }

        public void bind(final Fruit fruit, final OnItemClickListener itemClickListner) {
            name.setText(fruit.getName());
            description.setText(fruit.getDescription());
            amount.setText(fruit.getDescription());

            if (fruit.amountIsNotAllowToIncrease(fruit)) {
                amount.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
                amount.setTypeface(null, Typeface.BOLD);
            } else {
                amount.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
                amount.setTypeface(null, Typeface.NORMAL);
            }

            Picasso.with(activity).load(fruit.getIcon()).into(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListner.onItemClick(fruit, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }

    public FruitAdapter(List<Fruit> dataSet, int resource, Activity context, OnItemClickListener listener) {
        fruits = dataSet;
        layout = resource;
        activity = context;
        itemClickListner = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position), itemClickListner);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }
}

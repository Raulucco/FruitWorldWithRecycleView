package udemy.android.rauluco.fruitworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import udemy.android.rauluco.fruitworld.adapters.FruitAdapter;
import udemy.android.rauluco.fruitworld.models.Fruit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManageer;
    private FruitAdapter adapter;
    private List<Fruit> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fruits = new ArrayList<Fruit>(){{
            add(new Fruit());
        }};
        recycleView = (RecyclerView) findViewById(R.id.recycle_view);
        layoutManageer = new LinearLayoutManager(this);
        adapter = new FruitAdapter(fruits, R.layout.recycle_view_item, this, new FruitAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.addItem();
                adapter.notifyItemChanged(position);
            }
        });
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layoutManageer);
        recycleView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fruits_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_new_fruit) {
            int position = fruits.size();
            fruits.add(new Fruit("Fruit #" + position, "Fruit added by user", "0", R.mipmap.ic_launcher_round));
            adapter.notifyItemInserted(position);
            layoutManageer.scrollToPosition(position);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

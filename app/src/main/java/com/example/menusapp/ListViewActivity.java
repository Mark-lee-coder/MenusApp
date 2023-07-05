package com.example.menusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**helped in creating a context menu**/
public class ListViewActivity extends AppCompatActivity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    ArrayList<String> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mainListView = findViewById(R.id.mainListView);

        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(planets));

        listAdapter = new ArrayAdapter<String>(this, R.layout.list_simplerow, planetList);
        mainListView.setAdapter(listAdapter);

        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mainListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                // to do something when items are selected / de-selected
                // for now we do nothing
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                //take action when a menu item is clicked
                if (menuItem.getItemId() == R.id.listDelete) {
                    deleteSelectedItems();
                    actionMode.finish();
                    return true;
                }
                else {
                    return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    // delete selected items from list view
    private void deleteSelectedItems() {
        //getting the checked items from the listview
        SparseBooleanArray checkedItemPositions = mainListView.getCheckedItemPositions();
        int itemCount = mainListView.getCount();
        for (int i = itemCount - 1; i >= 0; i--) {
            if (checkedItemPositions.get(i)) {
                listAdapter.remove(planetList.get(i));
            }
        }
        checkedItemPositions.clear();
        listAdapter.notifyDataSetChanged();
    }
}
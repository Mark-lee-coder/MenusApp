package com.example.menusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    androidx.appcompat.view.ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actionmode_context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.actionModeMenu1:
                    Toast.makeText(MainActivity.this, "Action Mode 1 Clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.actionModeMenu2:
                    Toast.makeText(MainActivity.this, "Action Mode 2 Clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textTitle);
        this.registerForContextMenu(textView);

        Button button = findViewById(R.id.btnActionModeMenu);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }

                mActionMode = startSupportActionMode((androidx.appcompat.view.ActionMode.Callback) mActionModeCallback);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuItem1:
                Toast.makeText(this, "Menu Item 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem2:
                Toast.makeText(this, "Menu Item 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem3:
                Toast.makeText(this, "Menu Item 3 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contextMenu1:
                Toast.makeText(this, "Contextual Menu 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextMenu2:
                Toast.makeText(this, "Contextual Menu 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popupMenu1:
                Toast.makeText(this, "Popup Menu 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.popupMenu2:
                Toast.makeText(this, "Popup Menu 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void showListViewActivity(View view) {
        startActivity(new Intent(MainActivity.this, ListViewActivity.class));
    }
}
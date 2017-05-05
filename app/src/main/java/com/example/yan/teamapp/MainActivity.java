package com.example.yan.teamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yan.teamapp.adapter.PlayerAdapter;
import com.example.yan.teamapp.dao.PlayerDao;
import com.example.yan.teamapp.model.Player;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView PlayerListView;

    @Override
    protected void onResume() {
        super.onResume();
        buildPlayerList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlayerListView = (ListView) findViewById(R.id.playerList_listPlayers);

        PlayerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                Player player = (Player) PlayerListView.getItemAtPosition(position);
                Intent intentPlayerInsert = new Intent(MainActivity.this,
                        PlayerAdd.class);
                intentPlayerInsert.putExtra("player", player);
                startActivity(intentPlayerInsert);
            }
        });

        Button newButton = (Button) findViewById(R.id.playerInsert_buttonNew);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlayerInsert = new Intent(MainActivity.this,
                        PlayerAdd.class);
                startActivity(intentPlayerInsert);
            }
        });

        registerForContextMenu(PlayerListView);
    }

    private void buildPlayerList() {

        PlayerDao pDAO = new PlayerDao(MainActivity.this);
        List<Player> playerList = pDAO.read();
        pDAO.close();

        PlayerAdapter listViewAdapter =  new PlayerAdapter(this, playerList);
        PlayerListView.setAdapter(listViewAdapter);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    final ContextMenu.ContextMenuInfo menuInfo) {


        MenuItem deleteMenuItem = menu.add("Remover");

        AdapterView.AdapterContextMenuInfo adapterMenuInfo =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        Player player =
                (Player) PlayerListView.getItemAtPosition(adapterMenuInfo.position);
        final  PlayerDao studentDAO = new PlayerDao(MainActivity.this);

        buildDelete((AdapterView.AdapterContextMenuInfo) menuInfo, deleteMenuItem);

        studentDAO.close();

    }

    private void buildDelete(final AdapterView.AdapterContextMenuInfo menuInfo, MenuItem deleteMenuItem) {
        deleteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo adapterMenuInfo =
                        menuInfo;
                Player player =
                        (Player) PlayerListView.getItemAtPosition(adapterMenuInfo.position);

                PlayerDao pDAO = new PlayerDao(MainActivity.this);
                pDAO.delete(player.getId());
                pDAO.close();

                buildPlayerList();

                Toast.makeText(MainActivity.this,
                        "Jogador " + player.getName() + " removido!",
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }



    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            System.out.println("Chamou Baby");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.this_menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     //   SendStudentsTask  send = (SendStudentsTask) new SendStudentsTask(this).execute();

        return super.onOptionsItemSelected(item);
    }



}

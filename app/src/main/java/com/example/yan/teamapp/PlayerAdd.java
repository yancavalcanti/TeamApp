package com.example.yan.teamapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yan.teamapp.dao.PlayerDao;
import com.example.yan.teamapp.model.Player;
import com.example.yan.teamapp.util.PlayerInsertUtil;

import java.io.File;

/**
 * Created by Yan on 04/05/2017.
 */

public class PlayerAdd extends AppCompatActivity {


        private PlayerInsertUtil playerInsertUtil;
        private String pathPhoto;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_player_insert);

            playerInsertUtil = new PlayerInsertUtil(PlayerAdd.this);

            Intent intent = getIntent();
            Player editPlayer = (Player) intent.getSerializableExtra("player");

            FloatingActionButton floatButton = (FloatingActionButton) findViewById(R.id.botaoCamera);
            floatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentCaptureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    pathPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                    File filePhoto = new File(pathPhoto);
                    intentCaptureImage.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto));
                    startActivityForResult(intentCaptureImage, 456);
                }
            });

            if (editPlayer != null){
                playerInsertUtil.buildEditStudent(editPlayer);
            }

            Spinner spinner = (Spinner) findViewById(R.id.playersInsert_SpinnerPosition);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.positions, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 456 && resultCode == Activity.RESULT_OK) {
                ImageView avatar = (ImageView) findViewById(R.id.avatar);
                Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
                Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 512, 384, true);
                avatar.setScaleType(ImageView.ScaleType.FIT_XY);
                avatar.setTag(pathPhoto);
                avatar.setImageBitmap(bitmapReduce);
            }
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.this_menu_insert, menu);

            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.PlayersInsert_MenuOk:
                    Player p = new Player();
                    try {
                        PlayerDao dao = new PlayerDao(PlayerAdd.this);
                        p = playerInsertUtil.buildPlayerForInsert();

                        if (p.getId() < 0){
                            dao.update(p);
                        }else{
                            dao.create(p);
                        }

                        dao.close();
                        Toast.makeText(PlayerAdd.this,
                                "Novo Jogador " + p.getName() + " salvo!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }catch (Exception e){
                        Toast.makeText(PlayerAdd.this,
                                "Erro ao salvar novo jogador. \n" + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }

                    break;
            }

            return super.onOptionsItemSelected(item);
        }



    }


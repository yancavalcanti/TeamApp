package com.example.yan.teamapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.yan.teamapp.R;
import com.example.yan.teamapp.model.Player;



/**
 * Created by ErickJohnFidelisCost on 14/03/2017.
 */

public class PlayerInsertUtil {

    private EditText textName;
    private Spinner textPosition;
    private EditText textNumber;
    private ImageView photo;
    private Player player;

    public PlayerInsertUtil(AppCompatActivity appCompatActivity){

        textName = (EditText) appCompatActivity.findViewById(R.id.playersInsert_editTextName);
        textNumber = (EditText) appCompatActivity.findViewById(R.id.playersInsert_editTextNumber);
        textPosition = (Spinner) appCompatActivity.findViewById(R.id.playersInsert_SpinnerPosition);
        photo = (ImageView) appCompatActivity.findViewById(R.id.avatar);

        player = new Player();
    }

    public Player buildPlayerForInsert() throws Exception {

        if (textName.getText().toString().equals("")) {
            throw new Exception("Campo 'nome' obrigatório!");
        }

        if (textPosition.getSelectedItem().toString().equals("")) {
            throw new Exception("Campo 'posição' obrigatório!");
        }

        if (textNumber.getText().toString().equals("")) {
            throw new Exception("Campo 'número' obrigatório!");
        }

        String name = textName.getText().toString();

        String position = textPosition.getSelectedItem().toString();

        String number = textNumber.getText().toString();

        String photoPath = photo.getTag().toString();

        this.player.setName(name);
        this.player.setPositionID(position);

        this.player.setNumber(number);

        this.player.setPhoto(photoPath);

        return this.player;
    }

    public void buildEditStudent(Player editPlayer) {

        if (editPlayer.getPhoto() != null) {
            String photoPath = editPlayer.getPhoto();
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 512, 384, true);
            photo.setScaleType(ImageView.ScaleType.FIT_XY);
            photo.setTag(photoPath);
            photo.setImageBitmap(bitmapReduce);
        }

        textName.setText(editPlayer.getName());

     //   textPosition.setText(editPlayer.getPositionID());

        textNumber.setText(editPlayer.getNumber());

        this.player = editPlayer;

    }
}

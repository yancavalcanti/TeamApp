package com.example.yan.teamapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yan.teamapp.R;
import com.example.yan.teamapp.model.Player;

import java.util.List;



/**
 * Created by Yan on 08/04/2017.
 */

public class PlayerAdapter extends BaseAdapter {

    private final Context context;
    private final List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
       return this.players.size();
    }

    @Override
    public Object getItem(int i) {

        return this.players.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return this.players.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Player player = this.players.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.player_list, parent, false);
        }

        ImageView avatar = (ImageView) view.findViewById(R.id.playerList_imageViewPhoto);
        TextView playerName = (TextView) view.findViewById(R.id.playerList_textViewName);
        TextView playerNumber = (TextView) view.findViewById(R.id.playerList_textViewNumber);
        TextView playerPosition = (TextView) view.findViewById(R.id.playerList_textViewPosition);

        if (player.getPhoto() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(player.getPhoto());
            Bitmap bitmapReduce = Bitmap.createScaledBitmap(bitmap, 512, 384, true);
            avatar.setScaleType(ImageView.ScaleType.FIT_XY);
            avatar.setImageBitmap(bitmapReduce);
        }

        playerName.setText(player.getName());
        playerNumber.setText(player.getNumber());
        playerPosition.setText(player.getPositionID());

        return view;
    }

    public Context getContext() {
        return context;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

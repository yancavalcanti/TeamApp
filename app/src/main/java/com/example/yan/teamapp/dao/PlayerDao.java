package com.example.yan.teamapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.yan.teamapp.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan on 28/04/2017.
 */

public class PlayerDao extends SQLiteOpenHelper {

    public PlayerDao(Context context) {
        super(context, "TeamApp", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableStudents =
                "CREATE TABLE Players (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "number TEXT NOT NULL," +
                        "positionID TEXT NOT NULL," +
                        "photo TEXT)";

        db.execSQL(sqlCreateTableStudents);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    public List<Player> read() {

        SQLiteDatabase database = getReadableDatabase();
        String sqlReadStudents =
                "SELECT * FROM Players ORDER BY number";

        Cursor cursorReadPlayers = database.rawQuery(sqlReadStudents, null);

        List<Player> players = new ArrayList<Player>();
        while (cursorReadPlayers.moveToNext()) {

            Player p = new Player();
            p.setId(cursorReadPlayers.getInt(
                    cursorReadPlayers.getColumnIndex("id")));
            p.setName(cursorReadPlayers.getString(
                    cursorReadPlayers.getColumnIndex("name")));
            p.setNumber(cursorReadPlayers.getString(
                    cursorReadPlayers.getColumnIndex("number")));
            p.setPositionID(cursorReadPlayers.getString(
                    cursorReadPlayers.getColumnIndex("positionID")));
            p.setPhoto(cursorReadPlayers.getString(
                    cursorReadPlayers.getColumnIndex("photo")));
            players.add(p);
        }

        cursorReadPlayers.close();

        return players;
    }

    public void create(Player player) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues playerValues = getContentValues(player);

        database.insert("Players", null, playerValues);
    }


    @NonNull
    private ContentValues getContentValues(Player player) {
        ContentValues playerValues = new ContentValues();
        playerValues.put("name", player.getName());
        playerValues.put("number", player.getNumber());
        playerValues.put("positionID", player.getPositionID());
        playerValues.put("photo", player.getPhoto());
        return playerValues;
    }

    public Player findStudentById(Long id) {
        List<Player> players = read();
        for (Player p : players) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public String findStudentAttributeById(Long id, String att) {
        SQLiteDatabase database = getReadableDatabase();
        String sqlReadStudents =
                "SELECT " + att + " FROM Players WHERE id = " + id;

        Cursor cursorReadStudents = database.rawQuery(sqlReadStudents, null);

        cursorReadStudents.moveToNext();
        String resposta = cursorReadStudents.getString((cursorReadStudents.getColumnIndex(att)));

        cursorReadStudents.close();

        return resposta;
    }

    public void delete(int id) {

        SQLiteDatabase database = getWritableDatabase();
        String[] params = {String.valueOf(id)};
        database.delete("Players", "id = ?", params);
    }

    public void update(Player p) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues studentValues = getContentValues(p);
        String[] params = {String.valueOf(p.getId())};
        database.update("Player", studentValues, "id = ?", params);

    }


    public String findStudentByPhone(String receivedNumber) {
        SQLiteDatabase database = getReadableDatabase();
        String sqlReadStudents =
                "SELECT name FROM Players WHERE number = " + receivedNumber;

        Cursor cursorReadStudents = database.rawQuery(sqlReadStudents, null);
        List<String> alunos = new ArrayList<String>();

        while (cursorReadStudents.moveToNext()) {
            String resposta = cursorReadStudents.getString((cursorReadStudents.getColumnIndex("name")));
            alunos.add(resposta);
        }
        cursorReadStudents.close();

        if (alunos.size() > 0) {
            return alunos.get(0);
        } else {
            return null;
        }
    }

}


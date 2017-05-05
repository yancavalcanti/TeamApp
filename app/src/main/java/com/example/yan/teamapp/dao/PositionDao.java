package com.example.yan.teamapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.yan.teamapp.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan on 28/04/2017.
 */

public class PositionDao extends SQLiteOpenHelper {

    public PositionDao(Context context) {
        super(context, "TeamApp", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTableStudents =
                "CREATE TABLE Positions (" +
                        "id INTEGER PRIMARY KEY,"+
                        "name TEXT NOT NULL,"+
                        "number INTEGER NOT NULL,"+
                        "functionID INTEGER NOT NULL)";

        db.execSQL(sqlCreateTableStudents);
        String addPositions =   "INSERT INTO Positions VALUES(1,'Goleiro',1,1),"+
                                "INSERT INTO Positions VALUES(2,'Lateral Direito',2,2),"+
                                "INSERT INTO Positions VALUES(3,'Zagueiro',3,2),"+
                                "INSERT INTO Positions VALUES(4,'Lateral Esquerdo',4,2),"+
                                "INSERT INTO Positions VALUES(5,'Volante',5,3),"+
                                "INSERT INTO Positions VALUES(6,'Meio',6,3),"+
                                "INSERT INTO Positions VALUES(7,'Ponta/Meia Direita',7,3),"+
                                "INSERT INTO Positions VALUES(8,'Ponta/Meia Esquerda',8,3),"+
                                "INSERT INTO Positions VALUES(9,'Atacante',9,4),"+
                                "INSERT INTO Positions VALUES(10,'Centroavante',10,4)";
        db.execSQL(addPositions);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public List<Position> read() {

        SQLiteDatabase database = getReadableDatabase();
        String sqlReadStudents =
                "SELECT * FROM Positions";

        Cursor cursorReadPlayers = database.rawQuery(sqlReadStudents, null);

        List<Position> positions = new ArrayList<Position>();
        while (cursorReadPlayers.moveToNext()){

            Position p = new Position();
            p.setId(cursorReadPlayers.getInt(
                    cursorReadPlayers.getColumnIndex("id")));
            p.setName(cursorReadPlayers.getString(
                    cursorReadPlayers.getColumnIndex("name")));
            p.setNumber(cursorReadPlayers.getInt(
                    cursorReadPlayers.getColumnIndex("number")));
            p.setFunctionID(cursorReadPlayers.getInt(
                    cursorReadPlayers.getColumnIndex("positionId")));
            positions.add(p);
        }

        cursorReadPlayers.close();

        return positions;
    }

    public void create(Position position) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues postionValues = getContentValues(position);

        database.insert("Positions", null, postionValues);
    }


    @NonNull
    private ContentValues getContentValues(Position position) {
        ContentValues positionValues = new ContentValues();
        positionValues.put("name", position.getName());
        positionValues.put("number", position.getNumber());
        positionValues.put("functionID", String.valueOf(position.getFunctionID()));
        return positionValues;
    }
}
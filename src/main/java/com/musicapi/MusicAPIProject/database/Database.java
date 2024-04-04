package com.musicapi.MusicAPIProject.database;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private final String DATABASE_URL = "192.168.1.171";
    private final Integer PORT = 5432;
    private final String LOGIN_USER = "postgres";
    private final String LOGIN_PASS = "postgrspass";
    private final String DATABASE_CONNECTION_STRING = "jdbc:postgresql://"+DATABASE_URL+":"+PORT+"/postgres";

    //SINGLETON INSTANCE
    private static Database instance = null;

    private Database(){

    }

    public static Database getInstance(){
        if(instance == null)
        {
            instance = new Database();
        }
        
        return instance;
    }

    public List<List<String>> selectSongs()
    {
        try {
            List<List<String>> resultList = new ArrayList<>();
            Connection connection = DriverManager.getConnection(DATABASE_CONNECTION_STRING, LOGIN_USER, LOGIN_PASS);
            connection.setAutoCommit(false);
            Statement query = connection.createStatement();
            ResultSet result = query.executeQuery("SELECT SONGS.ID as ID, SONGS.NAME AS NAME, SONGS.DURATION AS DURATION, ALBUM.NAME AS ALBUM FROM SONGS JOIN ALBUM ON SONGS.ALBUM=ALBUM.ID");
            System.out.println(result);
            while (result.next()) {
                System.out.println("Riga trovata");
                List<String> appoggio = new ArrayList<>();
                appoggio.add(result.getString("id"));
                appoggio.add(result.getString("name"));
                appoggio.add(result.getString("duration"));
                appoggio.add(result.getString("album"));
                resultList.add(appoggio);

            }
            
            result.close();
            query.close();
            connection.close();
            
            return resultList;
        } catch (SQLException e) {
            System.out.println("Errore connessione database" + e);
            return null;
        }
    }

    public List<List<String>> selectSongs(Integer idToSearch)
    {
        try {
            List<List<String>> resultList = new ArrayList<>();
            Connection connection = DriverManager.getConnection(DATABASE_CONNECTION_STRING, LOGIN_USER, LOGIN_PASS);
            connection.setAutoCommit(false);
            Statement query = connection.createStatement();
            ResultSet result = query.executeQuery("SELECT SONGS.ID as ID, SONGS.NAME AS NAME, SONGS.DURATION AS DURATION, ALBUM.NAME AS ALBUM FROM SONGS JOIN ALBUM ON SONGS.ALBUM=ALBUM.ID WHERE songs.id="+idToSearch);
            while (result.next()) {
                System.out.println("Riga trovata");
                List<String> appoggio = new ArrayList<>();
                appoggio.add(result.getString("id"));
                appoggio.add(result.getString("name"));
                appoggio.add(result.getString("duration"));
                appoggio.add(result.getString("album"));
                resultList.add(appoggio);

            }
            
            result.close();
            query.close();
            connection.close();
            
            return resultList;
        } catch (SQLException e) {
            System.out.println("Errore connessione database" + e);
            return null;
        }
    }

    public List<List<String>> selectAlbum(Integer idToSearch)
    {
        try {
            List<List<String>> resultList = new ArrayList<>();
            Connection connection = DriverManager.getConnection(DATABASE_CONNECTION_STRING, LOGIN_USER, LOGIN_PASS);
            connection.setAutoCommit(false);
            Statement query = connection.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM ALBUM WHERE ALBUM.id="+idToSearch);
            while (result.next()) {
                System.out.println("Riga trovata");
                List<String> appoggio = new ArrayList<>();
                appoggio.add(result.getString("id"));
                appoggio.add(result.getString("name"));
                appoggio.add(result.getString("songs"));
                resultList.add(appoggio);

            }
            
            result.close();
            query.close();
            connection.close();
            
            return resultList;
        } catch (SQLException e) {
            System.out.println("Errore connessione database" + e);
            return null;
        }
    }

    public Integer insert(String table, List<String> columns ,List<String> values){
        try {
            Connection connection = DriverManager.getConnection(DATABASE_CONNECTION_STRING, LOGIN_USER, LOGIN_PASS);
            connection.setAutoCommit(false);
            Statement queryStatement = connection.createStatement();
            
            String queryStart = "INSERT INTO public." + table + "(";
            String columnsString = "";
            for(int i=0; i<columns.size(); i++)
            {
                columnsString = columnsString + columns.get(i);
                if(i != columns.size() -1)
                    columnsString += ",";
                
            }
            columnsString += ")";

            String valuesString = " VALUES(";
            for(int i=0; i<values.size(); i++)
            {
                valuesString = valuesString + values.get(i);
                if(i != values.size() -1)
                    valuesString += ",";
                
            }
            valuesString += ")";

            System.out.println(queryStart + columnsString + valuesString);

            queryStatement.executeUpdate(queryStart + columnsString + valuesString);
            
            connection.commit();
            queryStatement.close();
            connection.close();

            
            return 0;

        } catch (SQLException e) {
            System.out.println("Errore connessione database " + e);
            return 1;
        }
    }
    
}
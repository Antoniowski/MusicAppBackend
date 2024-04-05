package com.musicapi.MusicAPIProject.service;
import com.musicapi.MusicAPIProject.api.model.Album;
import com.musicapi.MusicAPIProject.api.model.Song;
import com.musicapi.MusicAPIProject.database.Database;
import com.musicapi.MusicAPIProject.sftp.Sftp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;




@Service
public class SongService{

    public SongService(){}

    public Song getSong(Integer id){
        try {
            Database database = Database.getInstance();
            List<String> result = database.selectSongs(id).getFirst();
            Song resultSong = new Song(Integer.valueOf(result.get(0)), result.get(1), result.get(3),Integer.valueOf(result.get(2)));     
            return resultSong;
        } catch (Exception e) {
            System.out.println("ERRORE: " + e);
            return new Song(-1, "ERRORE", "ERRORE", 0);
        }
        
    }

    public List<Song> getAllSongs()
    {
        try{
            List<Song> resultArray = new ArrayList<Song>();
            Database database = Database.getInstance();
            List<List<String>> result = database.selectSongs();
            for(List<String> song: result)
            {
                Song songHelper = new Song(Integer.valueOf(song.get(0)), song.get(1), song.get(3), Integer.valueOf(song.get(2)));
                resultArray.add(songHelper);
            }
            return resultArray;
        } catch (Exception e) {
            System.out.println("ERRORE: " + e);
            return null;
        }
    }

    public Album getAlbum(Integer id){
        try {
            Database database = Database.getInstance();
            List<String> result = database.selectAlbum(id).getFirst();
            char[] lista = result.get(2).toCharArray();
            List<Integer> albumSongs = new ArrayList<Integer>();
            for(char c: lista){
                try {
                    Integer num = Integer.parseInt(String.valueOf(c));
                    albumSongs.add(num);
                } catch (Exception e) {
                    continue;
                }
            }
            Album resultAlbum = new Album(Integer.valueOf(result.get(0)), result.get(1), albumSongs);
            return resultAlbum;
        } catch (Exception e) {
            System.out.println("ERRORE: " + e);
            return new Album(-1, "ERROR", new ArrayList<>());
           
        }
    }

    public Integer addSong(String name, String album, String imagePath, String musicPath)
    {
        try{
            //DATABASE INSERT
            Database database = Database.getInstance();
            Integer lastID = database.insert("songs", Arrays.asList("name", "duration", "album"), Arrays.asList(name, "60", "1"));

            //ADD SONG TO SFTP SERVER
            Sftp sftp = Sftp.getInstance();
            sftp.makeDirectory(lastID.toString());
            sftp.uploadFile(imagePath, lastID.toString(), "image.png");

            return 0;
        }catch (Exception e){
            return -1;
        }
    }
} 
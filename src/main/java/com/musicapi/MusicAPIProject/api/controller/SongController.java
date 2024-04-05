package com.musicapi.MusicAPIProject.api.controller;
import com.musicapi.MusicAPIProject.api.model.Album;
import com.musicapi.MusicAPIProject.api.model.Song;
import com.musicapi.MusicAPIProject.service.SongService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * SongController
 */
@RestController
public class SongController 
{

    private final String LOCAL_IMAGE_PATH = "src/main/java/com/musicapi/MusicAPIProject/sftp/resources/images/";
    private final String LOCAL_MUSIC_PATH = "src/main/java/com/musicapi/MusicAPIProject/sftp/resources/music/";

    private SongService songService;

    @Autowired
    public SongController(SongService songService)
    {
        this.songService = songService;
    }

    @CrossOrigin
    @GetMapping("/song")
    public Song getSong(@RequestParam Integer id)
    {   
        Song song = this.songService.getSong(id);
        if(song != null)
        {
            return song;
        }
        
        return new Song(-1, "Error", "Cannot find", 0);
    }

    @CrossOrigin
    @GetMapping("/songs")
    public List<Song> getSongs()
    {
        List<Song> songs = this.songService.getAllSongs();
        if(songs != null){
            return songs;
        }

        return null;
    }

    @GetMapping("/album")
    public Album getAlbum(@RequestParam Integer id){
        Album albums = this.songService.getAlbum(id);
        if(albums != null){
            return albums;
        }
        return null;
    }

    @PostMapping("/song")
    public String addSong(@RequestParam String name, String album, String imageName)
    {
        String nameString = name;
        String albumString = album;
        if(album == null)
            albumString = "Default";

        if(name == null)
            return "ERROR";

        Integer result = this.songService.addSong("'"+nameString+"'", albumString, LOCAL_IMAGE_PATH+imageName, "");
        if(result == 0)
            return "200";

        return "ERROR";

        
    }
}
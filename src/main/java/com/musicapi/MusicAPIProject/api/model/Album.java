package com.musicapi.MusicAPIProject.api.model;
import java.util.List;


public class Album {
    private Integer id;
    private String name;
    private Integer numberOfSongs;
    private List<Integer> songs;

    public Album(Integer id, String name, List<Integer> songs){
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.numberOfSongs = songs.size();
    }

    //GETTERS
    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Integer getNumberOfSongs(){
        return this.numberOfSongs;
    }

    public List<Integer> getSongs(){
        return this.songs;
    }

    //SETTERS
    public void setId(Integer newId){
        this.id = newId;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setSongs(List<Integer> newSongsArray){
        this.songs = newSongsArray;
        this.numberOfSongs = newSongsArray.size();
    }
}

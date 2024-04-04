package com.musicapi.MusicAPIProject.api.model;

public class Song
{
    private int id;
    private String name;
    private String album;
    private int duration;

    public Song(int id, String name, String album, int duration)
    {
        this.id = id;
        this.name = name;
        this.album = album;
        this.duration = duration;
    }

    // SETTERS
    public void setId(int newId)
    {
        this.id = newId;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void setAlbum(String newAlbum)
    {
        this.album = newAlbum;
    }

    public void setDuration(int newDuration)
    {
        this.duration = newDuration;
    }


    // GETTERS

    public int getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getAlbum()
    {
        return this.album;
    }
    
    public int getDuration()
    {
        return this.duration;
    }


}
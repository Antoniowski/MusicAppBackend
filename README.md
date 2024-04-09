# Backend MusicApp Project - Springboot API

It's the backend part of my full-stack excercise.
It consists in some API that can be used to retrieve data from a postgresSQL database and from a SFTP server.


## API References

- GET /Songs: It retrieve all the songs from the data base.
```Java
    public List<Song> getSongs()
    {
        List<Song> songs = this.songService.getAllSongs();
        if(songs != null){
            return songs;
        }

        return null;
    }
```

- GET /Song/{id}: It retrive a single song using the specified ID.
```Java
    public Song getSong(@RequestParam Integer id)
    {   
        Song song = this.songService.getSong(id);
        if(song != null)
        {
            return song;
        }
        
        return new Song(-1, "Error", "Cannot find", 0);
    }
```
- GET /Album/{id}: It retrive a single album using the specified ID.
```Java
    public Album getAlbum(@RequestParam Integer id){
        Album albums = this.songService.getAlbum(id);
        if(albums != null){
            return albums;
        }
        return null;
    }
```

- POST /Song/{name}/{album}/{imageName}: It add a new song in the database using the name, the album name and the image specified.
```Java
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
    }\
```

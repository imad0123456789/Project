package BE;

import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private List<Songs> songList;
    private int songCount;
    private String totalTime;
    private String totalTimeString;




    public Playlist(int id, String name , int songCount, String totalTime) {
        this.id = id;
        this.name = name;
        this.songCount = songCount;
        this.totalTime = totalTime;
       this.totalTime = totalTime;
    }
    public Playlist(int id, String name ) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public List<Songs> getSongList() {
        return songList;
    }

    public void setSongList(List<Songs> songList) {
        this.songList = songList;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    @Override
    public String toString() {
        return " Name=" + name + "Total song count =" + songCount + ", Total play Time=" + totalTime;
    }


}

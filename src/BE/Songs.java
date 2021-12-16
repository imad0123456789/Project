package BE;

public class Songs {

    private int id;
    private String Title;
    private String artist;
    private String category;
    private String Time;
    private String fileurl;


    public Songs(int id, String title, String artist, String category, String time, String fileurl) {
        this.id = id;
        Title = title;
        this.artist = artist;
        this.category = category;
        Time = time;
        this.fileurl = fileurl;
    }

    public Songs(String title, String artist, String category, String time, String fileurl) {
        Title = title;
        this.artist = artist;
        this.category = category;
        Time = time;
        this.fileurl = fileurl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public String getTime() {
        return Time;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }


    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", artist='" + artist + '\'' +
                ", category='" + category + '\'' +
                ", Time='" + Time + '\'' +
                ", fileurl=' " + fileurl + '\'' +
                '}' + "\n";
    }



}

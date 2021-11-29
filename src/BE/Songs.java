package BE;

public class Songs {

    private int id;
    private String Title;
    private Artist artist;
    private Category category;
    private Double Time;
    private String file;


    public Songs(int id, String title, Artist artist, Category category, Double time, String file) {
        this.id = id;
        Title = title;
        this.artist = artist;
        this.category = category;
        Time = time;
        this.file = file;


    }
}

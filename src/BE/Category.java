package BE;

public class Category {

    private int id;
    private String Type;

    public Category(int id, String type) {
        this.id = id;
        Type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}

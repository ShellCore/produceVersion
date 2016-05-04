package mx.shellcore.java.produceVersion.models;

public class Application {

    public static final int DEVELOP = 1;
    public static final int RELEASE = 2;

    private int id;
    private String name;
    private String version;
    private int type;

    public Application() {
    }

    public Application(int id, String name, String version, int type) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

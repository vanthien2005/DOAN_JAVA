package DTO;

public class Phim {
    private int id;
    private String name;
    private String type;
    private String duration;
    private String name_room;
    private String url ;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Phim(){}

    public Phim(int id,String name,String type,String duration,String name_room){
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.name_room = name_room;
    }
    public Phim(String name,String type,String duration,String url){
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.url = url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}

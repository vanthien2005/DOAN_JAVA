package DTO;

public class Phim {
    private int id;
    private String name;
    private String type;
    private String duration;
    private String name_room;

    public Phim(){}

    public Phim(int id,String name,String type,String duration,String name_room){
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.name_room = name_room;
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
    public void inThongTin(){
        System.out.println("ID phim: "+id);
        System.out.println("Tên phim: "+name);
        System.out.println("Thể loại: "+type);
        System.err.println("Thời lượng: "+duration);
        System.out.println("Phòng chiếu: "+name_room);
        System.out.println("------------------------------------------");
    }

}

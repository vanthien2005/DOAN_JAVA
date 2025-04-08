package DTO;

public class PhongChieuPhim {
    private int id;
    private int room_id;
    private int movie_id;
    public PhongChieuPhim(int id,int room_id,int movie_id){
        this.id = id;
        this.room_id = room_id;
        this.movie_id = movie_id;
        
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }
    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
   
   public void inThongTin(){
    System.out.println("ID chi tiết phong chiếu phim: "+id);
    System.out.println("ID phòng chiếu: "+room_id);
    System.out.println("ID phim: "+movie_id);
    System.out.println("-------------------------------------------");
   }

}

package DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class LichChieu {
    public static int d = 1;
    private int id;
    private int id_phim;
    private int id_phong;
    private LocalDate tg;
    private LocalTime time;
    private String movieName ;
    private String roomName;
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

   
    Scanner sc = new Scanner(System.in);
    public LichChieu()
    {

    }
    public LichChieu( int id_phim,int id_phong,LocalDate tg,LocalTime time){
        this.id_phim = id_phim;
        this.id_phong = id_phong;
        this.tg = tg;
        this.time = time;
    }
    public LichChieu( int id,int id_phim,int id_phong,LocalDate tg,LocalTime time){
        this.id = id;
        this.id_phim = id_phim;
        this.id_phong = id_phong;
        this.tg = tg;
        this.time = time;
    }
    public static int tangID(){
        return d++;
    }
   public void setID(int id)
    {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setIdPhim(int id){
        id_phim = id;

    }
    public int getIdPhim()
    {
        return id_phim;
    }
    public void setTg(LocalDate tg){
        this.tg = tg;
    }
    public LocalDate getTg()
    {
        return tg;
    }
    public void setTime(LocalTime g)
    {
        time = g;
    }
    public LocalTime getTime(){
        return time;
    }
    public int getIdPhong(){
        return id_phong;
    }
       
    public void inThongTin(){
        System.out.println("Mã lịch chiếu: "+id);
       // System.out.println("mã phòng: "+ id_phong );
        System.out.println("Ngay thang nam: "+time+" "+tg);
       // System.out.println("ten Phim: "+id_phim);  
    }
   
}

package DTO;

public class gheNgoi {
    private int id;
    private int room_id;
    private String number_seat;
    private String status;
    public gheNgoi(int id, int room_id, String number_seat, String status) {
        this.id = id;
        this.room_id = room_id;
        this.number_seat = number_seat;
        this.status = status;
    }
    public gheNgoi( int room_id, String number_seat, String status) {
        this.room_id = room_id;
        this.number_seat = number_seat;
        this.status = status;
    }
    public gheNgoi() {
        //TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public String getNumber_seat() {
        return number_seat;
    }
    public void setNumber_seat(String number_seat) {
        this.number_seat = number_seat;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void inThongTin(){
        System.out.println("Mã ghế: "+id);
        System.out.println("Mã phòng: "+room_id);
        System.out.println("Số ghế: "+number_seat);
        String message = status.equals("1") ? "đã được đặt" : "chưa được đặt";
        System.out.println(message);
        System.out.println("-----------------------------");
    }
    
}

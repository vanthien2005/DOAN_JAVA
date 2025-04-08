package DTO;

public class Ve {
    private int id;
    private int user_id;
    private int seat_id;
    private int showTime_id;
    private String status;
    private int price;
    
    public Ve(int id,int price){
        this.id = id;
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Ve(int id, int user_id, int seat_id, int showTime_id, String status,int price) {
        this.id = id;
        this.user_id = user_id;
        this.seat_id = seat_id;
        this.showTime_id = showTime_id;
        this.status = status;
        this.price = price;
    }
    public Ve(int user_id, int seat_id, int showTime_id, String status,int price) {
        this.user_id = user_id;
        this.seat_id = seat_id;
        this.showTime_id = showTime_id;
        this.status = status;
        this.price = price;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getShowTime_id() {
        return showTime_id;
    }

    public void setShowTime_id(int showTime_id) {
        this.showTime_id = showTime_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void inThongTin(){
        System.out.println();
    }
    
}

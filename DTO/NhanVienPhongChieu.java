package DTO;

public class NhanVienPhongChieu {
    private int id;
    private int emp_id;
    private int room_id;
    public NhanVienPhongChieu(int id, int emp_id, int room_id) {
        this.id = id;
        this.emp_id = emp_id;
        this.room_id = room_id;
    }
    public NhanVienPhongChieu( int emp_id, int room_id) {
        this.emp_id = emp_id;
        this.room_id = room_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    
    
}

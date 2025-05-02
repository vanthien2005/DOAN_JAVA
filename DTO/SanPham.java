package DTO;

public class SanPham {
    private int id;
    private String name;
    private String type;
    private int price;
    private String status;
    private String image;
    private int tongSL;
    private int tongGia;
    public int getTongGia() {
        return tongGia;
    }
    public void setTongGia(int tongGia) {
        this.tongGia = tongGia;
    }
    public int getTongSL() {
        return tongSL;
    }
    public void setTongSL(int tongSL) {
        this.tongSL = tongSL;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public SanPham(){}
    public SanPham(String name, String type, int price, String status,String image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.image = image;
    }
    public SanPham(int id,String name, String type, int price, String status,String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.image = image;
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
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}

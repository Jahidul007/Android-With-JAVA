package example.com.firebasesavingdata;

public class OrderMaterials {

    private String id ;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String productName;

    public OrderMaterials() {
    }

    public OrderMaterials(String id, String userName, String email, String phone, String address, String productName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getProductName() {
        return productName;
    }
}

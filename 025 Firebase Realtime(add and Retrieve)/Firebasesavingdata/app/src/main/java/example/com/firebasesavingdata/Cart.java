package example.com.firebasesavingdata;

public class Cart {

    String id ;
    String name;

    public Cart() {

    }

    public Cart(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

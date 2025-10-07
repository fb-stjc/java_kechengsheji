import java.io.Serializable;
import java.util.ArrayList;

public class goods implements Serializable {
    int id;//编号
    String  name;//名称
    double price;//价格
    int buyNumber;//购买数量

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getBuyNumber() {
        return buyNumber;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }


    @Override
    public String toString() {
        return id + "," + name + ","+price+","+buyNumber;
    }

}
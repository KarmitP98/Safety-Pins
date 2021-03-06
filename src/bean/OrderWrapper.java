package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "purchaseOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderWrapper {


    @XmlElement(name = "items")
    ArrayList<POItemBean> items;
    int id;
    int userId;
    @XmlAttribute(name = "orderDate")
    private String orderDate;
    @XmlElement(name = "shipTo")
    private AddressBean shipTo;
    @XmlElement(name = "billTo")
    private AddressBean billTo;
    
    public OrderWrapper() {
    	
    }
    public OrderWrapper(String orderDate, AddressBean shipTo, AddressBean billTo, ArrayList<POItemBean> items, int id,
                        int userId) {
        super();
        this.orderDate = orderDate;
        this.shipTo = shipTo;
        this.billTo = billTo;
        this.items = items;
        this.id = id;
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public AddressBean getShipTo() {
        return shipTo;
    }

    public void setShipTo(AddressBean shipTo) {
        this.shipTo = shipTo;
    }

    public AddressBean getBillTo() {
        return billTo;
    }

    public void setBillTo(AddressBean billTo) {
        this.billTo = billTo;
    }

    public ArrayList<POItemBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<POItemBean> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}

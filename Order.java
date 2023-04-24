public class Order {
    
    String deliveryName;
    String wayOfPayment;
    float amount = 0;
    
    public void setDeliveryName(String name){
        deliveryName = name;
    }

    public void setWayOfPayment(String wop){
        wayOfPayment = wop;
    }
    
    public void setAmount(float am){
        amount = am;
    }
    
    public String getDeliveryName(){
        return deliveryName;
    }
    
    public String getWayOfPayment(){
        return wayOfPayment;
    }

    public float getAmount(){
        return amount;
    }



}

package bootcamp;

public class Request {

    private String clientId, name;
    private long requestId;
    private int quantity;
    private double price;

    public Request(String clientId, String name, long requestId, int quantity, double price) {
        this.clientId = clientId;
        this.name = name;
        this.requestId = requestId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public long getRequestId() {
        return requestId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

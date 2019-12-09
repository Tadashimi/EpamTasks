package Task3_СargoTransportation;

public class Carrier {
    private String name;
    private WayOfTransportation[] availableWaysOfTransportation;

    public Carrier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WayOfTransportation[] getAvailableWaysOfTransportation() {
        return availableWaysOfTransportation;
    }

    public void setAvailableWaysOfTransportation(WayOfTransportation[] availableWaysOfTransportation) {
        this.availableWaysOfTransportation = availableWaysOfTransportation;
    }
}

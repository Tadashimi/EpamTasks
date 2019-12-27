package main.java.Task3_СargoTransportation;

public class Cargo {
    private String name;
    private CargoSize cargoSize;
    private Float weight;
    private Float cost;
    private WayOfTransportation[] possibleWaysOfTransportation;

    public Cargo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CargoSize getCargoSize() {
        return cargoSize;
    }

    public void setCargoSize(CargoSize cargoSize) {
        this.cargoSize = cargoSize;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public WayOfTransportation[] getPossibleWaysOfTransportation() {
        return possibleWaysOfTransportation;
    }

    public void setPossibleWaysOfTransportation(WayOfTransportation[] possibleWaysOfTransportation) {
        this.possibleWaysOfTransportation = possibleWaysOfTransportation;
    }
}

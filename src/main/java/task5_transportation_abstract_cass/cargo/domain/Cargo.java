package task5_transportation_abstract_cass.cargo.domain;

import task5_transportation_abstract_cass.common.domain.BaseDomain;
import task5_transportation_abstract_cass.transportation.domain.Transportation;

import static task5_transportation_abstract_cass.Constants.INITIAL_ARRAY_CAPACITY;

public class Cargo extends BaseDomain {
    private String name;
    private int weight;
    private CargoType cargoType;
    private Transportation[] transportations;
    private int transportationsCount = 0;

    public Cargo() {
        transportations = new Transportation[INITIAL_ARRAY_CAPACITY];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

    public void addTransportation(Transportation transportation) {
        int currentArrayLength = transportations.length;
        if (transportationsCount >= currentArrayLength) {
            increaseTransportationsArray(currentArrayLength);
        }
        transportations[transportationsCount] = transportation;
        transportationsCount++;
    }

    private void increaseTransportationsArray(int currentArrayLength) {
        Transportation[] tempArray = new Transportation[currentArrayLength * 2];
        System.arraycopy(transportations, 0, tempArray, 0, currentArrayLength);
        transportations = tempArray;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", weight = " + weight +
                ", cargoType = " + cargoType +
                '}';
    }
}

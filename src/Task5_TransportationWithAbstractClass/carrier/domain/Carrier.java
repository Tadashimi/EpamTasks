package Task5_TransportationWithAbstractClass.carrier.domain;

import Task5_TransportationWithAbstractClass.ClassWithIdentifier;
import Task5_TransportationWithAbstractClass.transportation.domain.Transportation;

import static Task5_TransportationWithAbstractClass.Constants.INITIAL_ARRAY_CAPACITY;

public class Carrier extends ClassWithIdentifier {
    private String name;
    private String address;
    private CarrierType carrierType;
    private Transportation[] transportations;
    private int transportationsCount;

    public Carrier() {
        transportations = new Transportation[INITIAL_ARRAY_CAPACITY];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
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
        return "Carrier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType=" + carrierType +
                '}';
    }
}
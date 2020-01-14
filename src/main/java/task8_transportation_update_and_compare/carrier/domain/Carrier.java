package task8_transportation_update_and_compare.carrier.domain;


import task8_transportation_update_and_compare.common.business.domain.BaseEntity;
import task8_transportation_update_and_compare.transportation.domain.Transportation;

public class Carrier extends BaseEntity {

    private String name;
    private String address;
    private CarrierType carrierType;
    private Transportation[] transportations = new Transportation[10];
    private int transportationsCount = 0;

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

    public void addTransformation(Transportation transportation) {
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
                ", name=" + name +
                ", address=" + address +
                ", carrierType=" + carrierType +
                '}';
    }

}

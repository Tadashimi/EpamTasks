package  Task9_TransportationWithException.carrier.domain;


import  Task9_TransportationWithException.common.business.domain.BaseEntity;
import  Task9_TransportationWithException.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public class Carrier extends BaseEntity {

    private String name;
    private String address;
    private CarrierType carrierType;
    private List<Transportation> transportations = new ArrayList<>();

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

    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }

    public void addTransportation(Transportation transportation) {
        transportations.add(transportation);
    }

    public void removeTransportation(Transportation transportation) {
        transportations.remove(transportation);
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType='" + carrierType + '\'' +
                ", transportations=" + transportations.toString() +
                '}';
    }

}

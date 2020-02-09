package task20_transportation_data_base.carrier.domain;


import task20_transportation_data_base.common.business.domain.BaseEntity;
import task20_transportation_data_base.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public class Carrier extends BaseEntity {
    private static final long serialVersionUID = 103L;

    private String name;
    private String address;
    private CarrierType carrierType;
    private List<Transportation> transportations;

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
        if (transportations == null) {
            transportations = new ArrayList<>();
        }
        transportations.add(transportation);
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

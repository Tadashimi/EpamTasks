package  task9_transportation_exceptions.cargo.domain;

import  task9_transportation_exceptions.common.business.domain.BaseEntity;
import  task9_transportation_exceptions.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public abstract class Cargo extends BaseEntity {

    protected String name;
    protected int weight;
    protected List<Transportation> transportations = new ArrayList<>();
    protected CargoType cargoType;

    public Cargo() {
        cargoType = this.getCargoType();
    }

    public abstract CargoType getCargoType();

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
        if (transportation != null) {
            transportations.remove(transportation);
        }
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", transportations=" + transportations.toString() +
                '}';
    }

}

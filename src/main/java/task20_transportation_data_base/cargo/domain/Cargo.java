package task20_transportation_data_base.cargo.domain;

import task20_transportation_data_base.common.business.domain.BaseEntity;
import task20_transportation_data_base.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public abstract class Cargo extends BaseEntity {
    private static final long serialVersionUID = 101L;

    protected String name;
    protected int weight;
    protected List<Transportation> transportations;
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
        if (transportations == null) {
            transportations = new ArrayList<>();
        }
        transportations.add(transportation);
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                '}';
    }
}

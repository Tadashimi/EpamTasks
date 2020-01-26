package task16_transportation_lambda.cargo.domain;

import task16_transportation_lambda.common.business.domain.BaseEntity;
import task16_transportation_lambda.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", transportations=" + transportations +
                '}';
    }
}

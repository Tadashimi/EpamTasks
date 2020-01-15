package task14_transportation_serialization.transportation.domain;

import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.common.business.domain.BaseEntity;

import java.util.Date;
import java.util.Objects;

public class Transportation extends BaseEntity {
    private static final long serialVersionUID = 102L;

    private Cargo cargo;
    private Carrier carrier;
    private String description;
    private String billTo;
    private Date transportationBeginDate;

    public Date getTransportationBeginDate() {
        return transportationBeginDate;
    }

    public void setTransportationBeginDate(Date transportationBeginDate) {
        this.transportationBeginDate = transportationBeginDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "description='" + description + '\'' +
                ", Carrier='" + carrier.getName() + '\'' +
                ", Cargo=" + cargo.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportation that = (Transportation) o;
        return Objects.equals(cargo, that.cargo) &&
                Objects.equals(carrier, that.carrier) &&
                Objects.equals(description, that.description) &&
                Objects.equals(billTo, that.billTo) &&
                Objects.equals(transportationBeginDate, that.transportationBeginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargo, carrier, description, billTo, transportationBeginDate);
    }
}

package  task8_transportation_update_and_compare.transportation.domain;

import  task8_transportation_update_and_compare.cargo.domain.Cargo;
import  task8_transportation_update_and_compare.carrier.domain.Carrier;
import  task8_transportation_update_and_compare.common.business.domain.BaseEntity;

import java.util.Date;

public class Transportation extends BaseEntity {

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
        cargo.addTransformation(this);
        this.cargo = cargo;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        carrier.addTransformation(this);
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "Transportation{id=" + id +
                ", cargo=" + cargo +
                ", carrier=" + carrier +
                ", description='" + description + '\'' +
                ", billTo='" + billTo + '\'' +
                ", transportationBeginDate=" + transportationBeginDate +
                '}';
    }
}

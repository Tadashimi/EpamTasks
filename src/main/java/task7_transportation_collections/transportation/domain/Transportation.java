package task7_transportation_collections.transportation.domain;

import task7_transportation_collections.cargo.domain.Cargo;
import task7_transportation_collections.carrier.domain.Carrier;
import task7_transportation_collections.common.business.domain.BaseEntity;

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
        return "Transportation{id=" + id +
                ", cargo=" + cargo +
                ", carrier=" + carrier +
                ", description='" + description + '\'' +
                ", billTo='" + billTo + '\'' +
                ", transportationBeginDate=" + transportationBeginDate +
                '}';
    }
}

package task5_transportation_abstract_cass.transportation.domain;

import task5_transportation_abstract_cass.common.domain.BaseDomain;
import task5_transportation_abstract_cass.cargo.domain.Cargo;
import task5_transportation_abstract_cass.carrier.domain.Carrier;

import java.util.Date;

public class Transportation extends BaseDomain {
    private Cargo cargo;
    private Carrier carrier;
    private String description;
    private String billTo;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
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
        cargo.addTransportation(this);
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
        carrier.addTransportation(this);
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", cargo=" + cargo +
                ", carrier=" + carrier +
                ", description='" + description + '\'' +
                ", billTo='" + billTo + '\'' +
                ", date=" + date +
                '}';
    }
}

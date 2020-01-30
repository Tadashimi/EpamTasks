package task18_transportation_local_date_and_streams.transportation.domain;

import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.common.business.domain.BaseEntity;

import java.time.LocalDate;

public class Transportation extends BaseEntity {
    private static final long serialVersionUID = 102L;

    private Cargo cargo;
    private Carrier carrier;
    private String description;
    private String billTo;
    private LocalDate transportationBeginDate;

    public LocalDate getTransportationBeginDate() {
        return transportationBeginDate;
    }

    public void setTransportationBeginDate(LocalDate transportationBeginDate) {
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
}

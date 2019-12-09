package Task3_СargoTransportation;

public class Transportation {
    private Long id;
    Carrier carrier;
    WayOfTransportation wayOfTransportation;
    Cargo[] cargos;

    public Transportation(Long id, Carrier carrier, WayOfTransportation wayOfTransportation, Cargo[] cargos) {
        this.id = id;
        this.carrier = carrier;
        this.wayOfTransportation = wayOfTransportation;
        this.cargos = cargos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public WayOfTransportation getWayOfTransportation() {
        return wayOfTransportation;
    }

    public void setWayOfTransportation(WayOfTransportation wayOfTransportation) {
        this.wayOfTransportation = wayOfTransportation;
    }

    public Cargo[] getCargos() {
        return cargos;
    }

    public void setCargos(Cargo[] cargos) {
        this.cargos = cargos;
    }
}

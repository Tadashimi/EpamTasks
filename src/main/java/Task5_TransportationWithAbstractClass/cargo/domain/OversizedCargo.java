package Task5_TransportationWithAbstractClass.cargo.domain;

public class OversizedCargo extends Cargo {
    private Float length;
    private Float high;
    private Float width;

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }
}

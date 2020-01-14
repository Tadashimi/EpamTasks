package task3_transportation_domain_model;

public class CargoSize {
    private Float length;
    private Float high;
    private Float width;
    private Float volume;

    public CargoSize(Float length, Float high, Float width) {
        this.length = length;
        this.high = high;
        this.width = width;
        this.volume = length * high * width;
    }

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

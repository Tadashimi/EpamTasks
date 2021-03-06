package task20_transportation_data_base.cargo.domain;

import java.time.LocalDate;

public class FoodCargo extends Cargo {

    private LocalDate expirationDate;
    private int storeTemperature;

    @Override
    public CargoType getCargoType() {
        return CargoType.FOOD;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStoreTemperature() {
        return storeTemperature;
    }

    public void setStoreTemperature(int storeTemperature) {
        this.storeTemperature = storeTemperature;
    }
}

package task20_transportation_data_base.common.solutions.utils;

import task20_transportation_data_base.cargo.domain.ClothersCargo;
import task20_transportation_data_base.cargo.domain.FoodCargo;
import task20_transportation_data_base.carrier.domain.Carrier;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomEntityGenerator {

    public static ClothersCargo createClothersCargo(int index) {
        ClothersCargo cargo = new ClothersCargo();
        cargo.setSize("Clothers_Size_" + index);
        cargo.setMaterial("Material_" + index);
        cargo.setName("Jeans_" + index);
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));

        return cargo;
    }

    public static FoodCargo createFoodCargo(int index) {
        FoodCargo cargo = new FoodCargo();
        cargo.setExpirationDate(LocalDate.now());
        cargo.setStoreTemperature(index);
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));
        cargo.setName("Milk_" + index);

        return cargo;
    }

    public static Carrier createCarrier(int index) {
        Carrier carrier = new Carrier();
        carrier.setName("Carrier_Name_" + index);
        carrier.setAddress("Address_" + index);
        return carrier;
    }
}

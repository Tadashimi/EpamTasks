package task14_transportation_serialization.helper;

import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.cargo.domain.ClothersCargo;
import task14_transportation_serialization.cargo.domain.FoodCargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.transportation.domain.Transportation;

import java.util.Date;
import java.util.Random;

public class EntityGenerator {
    private static Random random = new Random();

    public static Cargo getFoodCargo() {
        Cargo foodCargo = new FoodCargo();
        foodCargo.setId(random.nextLong());
        foodCargo.setName("foodCargo_" + random.nextInt());
        foodCargo.setWeight(random.nextInt());
        ((FoodCargo) foodCargo).setExpirationDate(new Date());
        ((FoodCargo) foodCargo).setStoreTemperature(10);
        return foodCargo;
    }

    public static Cargo getClothersCargo() {
        Cargo clothersCargo = new ClothersCargo();
        clothersCargo.setId(random.nextLong());
        clothersCargo.setName("clothersCargo_" + random.nextInt());
        clothersCargo.setWeight(random.nextInt());
        ((ClothersCargo) clothersCargo).setSize("size_" + random.nextInt());
        ((ClothersCargo) clothersCargo).setMaterial("material_" + random.nextInt());
        return clothersCargo;
    }

    public static Carrier getCarrier() {
        Carrier carrier = new Carrier();
        carrier.setId(random.nextLong());
        carrier.setName("carrier_" + random.nextInt());
        carrier.setAddress("carrierAddress_" + random.nextInt());
        return carrier;
    }

    public static Transportation getTransportation(Cargo cargo, Carrier carrier) {
        Transportation transportation = new Transportation();
        transportation.setId(random.nextLong());
        transportation.setDescription("description_" + random.nextInt());
        transportation.setBillTo("billTo_" + random.nextInt());
        transportation.setTransportationBeginDate(new Date());
        if (cargo != null) {
            transportation.setCargo(cargo);
        }
        if (carrier != null) {
            transportation.setCarrier(carrier);
        }
        return transportation;
    }

    public static Transportation getTransportation() {
        Cargo cargo = getFoodCargo();
        Carrier carrier = getCarrier();
        Transportation transportation = new Transportation();
        transportation.setId(random.nextLong());
        transportation.setDescription("description_" + random.nextInt());
        transportation.setBillTo("billTo_" + random.nextInt());
        transportation.setTransportationBeginDate(new Date());
        if (cargo != null) {
            transportation.setCargo(cargo);
        }
        if (carrier != null) {
            transportation.setCarrier(carrier);
        }
        return transportation;
    }
}

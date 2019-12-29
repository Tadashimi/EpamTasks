package Task8_TransportationWithUpdateAndCompare.storage.initor;

import Task8_TransportationWithUpdateAndCompare.application.serviceholder.ServiceHolder;
import Task8_TransportationWithUpdateAndCompare.cargo.domain.ClothersCargo;
import Task8_TransportationWithUpdateAndCompare.cargo.domain.FoodCargo;
import Task8_TransportationWithUpdateAndCompare.cargo.service.CargoService;
import Task8_TransportationWithUpdateAndCompare.carrier.domain.Carrier;
import Task8_TransportationWithUpdateAndCompare.carrier.service.CarrierService;
import Task8_TransportationWithUpdateAndCompare.transportation.domain.Transportation;
import Task8_TransportationWithUpdateAndCompare.transportation.service.TransportationService;

import java.util.Date;

public class InMemoryStorageInitor implements StorageInitor {
    private static final int TOTAL_ENTITIES_IN_GROUP = 20;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public InMemoryStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() {
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.add(createClothersCargo(i));
        }
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.add(createFoodCargo(i));
        }
    }

    private ClothersCargo createClothersCargo(int index) {
        ClothersCargo cargo = new ClothersCargo();
        cargo.setSize("Clothers_Size_" + index);
        cargo.setName("Clothers_Name_" + index);
        cargo.setWeight((int) Math.round(Math.random() * 100));

        return cargo;
    }

    private FoodCargo createFoodCargo(int index) {
        FoodCargo cargo = new FoodCargo();
        cargo.setExpirationDate(new Date());
        cargo.setStoreTemperature(index);
        cargo.setName("FoodCargo_Name");
        cargo.setWeight((int) Math.round(Math.random() * 100));

        return cargo;
    }

    private void initCarriers() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Carrier carrier = createCarrier(i);
            carrierService.add(carrier);
        }
    }

    private Carrier createCarrier(int index) {
        Carrier carrier = new Carrier();
        carrier.setName("Carrier_Name");
        carrier.setAddress("Address_" + index);
        return carrier;
    }

    private void initTransportations() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Transportation transportation = createTransportation(i + 1, i + 1 + TOTAL_ENTITIES_IN_GROUP);
            transportationService.add(transportation);
        }
    }

    private Transportation createTransportation(long cargoId, long carrierId) {
        Transportation transportation = new Transportation();
        transportation.setCargo(cargoService.getById(cargoId));
        transportation.setCarrier(carrierService.getById(carrierId));
        transportation.setDescription("Transportation");

        return transportation;
    }
}

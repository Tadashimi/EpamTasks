package task16_transportation_lambda.storage.initor;


import task16_transportation_lambda.application.serviceholder.ServiceHolder;
import task16_transportation_lambda.cargo.domain.Cargo;
import task16_transportation_lambda.cargo.domain.ClothersCargo;
import task16_transportation_lambda.cargo.domain.FoodCargo;
import task16_transportation_lambda.cargo.service.CargoService;
import task16_transportation_lambda.carrier.domain.Carrier;
import task16_transportation_lambda.carrier.service.CarrierService;
import task16_transportation_lambda.transportation.domain.Transportation;
import task16_transportation_lambda.transportation.service.TransportationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static task16_transportation_lambda.common.solutions.utils.CollectionUtils.collectionIsEmptyPredicate;
import static task16_transportation_lambda.common.solutions.utils.EntityUtils.entitiesIdsAreEqualsPredicate;

public class InMemoryStorageInitor implements StorageInitor {

    private static final int TOTAL_ENTITIES_IN_GROUP = 6;

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

        appendTransportationsToCargos();
    }

    private void initCargos() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.save(createClothersCargo(i));
        }
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.save(createFoodCargo(i));
        }
    }

    private ClothersCargo createClothersCargo(int index) {
        ClothersCargo cargo = new ClothersCargo();
        cargo.setSize("Clothers_Size_" + index);
        cargo.setName("Jeans");
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));

        return cargo;
    }

    private FoodCargo createFoodCargo(int index) {
        FoodCargo cargo = new FoodCargo();
        cargo.setExpirationDate(new Date());
        cargo.setStoreTemperature(index);
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));
        cargo.setName("Milk");

        return cargo;
    }

    private void initCarriers() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Carrier carrier = createCarrier(i);
            carrierService.save(carrier);
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
            transportationService.save(transportation);
        }
    }

    private Transportation createTransportation(long cargoId, long carrierId) {
        Transportation transportation = new Transportation();
        transportation.setCargo(cargoService.findById(cargoId));
        transportation.setCarrier(carrierService.findById(carrierId));
        transportation.setDescription("Transportation");

        return transportation;
    }

    private void appendTransportationsToCargos() {
        List<Cargo> cargos = cargoService.getAll();
        List<Transportation> transportations = transportationService.getAll();

        if (collectionIsEmptyPredicate(false).test(cargos) &&
                collectionIsEmptyPredicate(false).test(transportations)) {
            cargos.forEach((cargo) -> {
                if (cargo != null) {
                    appendTransportationsToCargo(cargo, transportations);
                }
            });
        }
    }

    private void appendTransportationsToCargo(Cargo cargo,
                                              List<Transportation> transportations) {
        List<Transportation> cargoTransportations = cargo.getTransportations();

        if (cargoTransportations == null) {
            cargoTransportations = new ArrayList<>();
        }

        for (Transportation transportation : transportations) {
            if (transportation != null &&
                    transportation.getCargo() != null &&
                    entitiesIdsAreEqualsPredicate(cargo).test(transportation.getCargo())) {
                cargoTransportations.add(transportation);
            }
        }

        cargo.setTransportations(transportations);
    }
}

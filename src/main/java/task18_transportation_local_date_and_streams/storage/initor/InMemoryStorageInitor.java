package task18_transportation_local_date_and_streams.storage.initor;


import task18_transportation_local_date_and_streams.application.serviceholder.ServiceHolder;
import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.cargo.domain.ClothersCargo;
import task18_transportation_local_date_and_streams.cargo.domain.FoodCargo;
import task18_transportation_local_date_and_streams.cargo.service.CargoService;
import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.carrier.service.CarrierService;
import task18_transportation_local_date_and_streams.transportation.domain.Transportation;
import task18_transportation_local_date_and_streams.transportation.service.TransportationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static task18_transportation_local_date_and_streams.common.solutions.utils.CollectionUtils.collectionIsEmptyPredicate;
import static task18_transportation_local_date_and_streams.common.solutions.utils.EntityUtils.entitiesIdsAreEqualsPredicate;

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
        cargo.setExpirationDate(LocalDate.now());
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
        Optional<Cargo> cargo = cargoService.findById(cargoId);
        cargo.ifPresent(transportation::setCargo);
        Optional<Carrier> carrier = carrierService.findById(carrierId);
        carrier.ifPresent(transportation::setCarrier);
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

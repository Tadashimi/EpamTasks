package task20_transportation_data_base.storage.initor;


import task20_transportation_data_base.application.serviceholder.ServiceHolder;
import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.service.CargoService;
import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.service.CarrierService;
import task20_transportation_data_base.transportation.domain.Transportation;
import task20_transportation_data_base.transportation.service.TransportationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static task20_transportation_data_base.common.solutions.utils.CollectionUtils.collectionIsEmptyPredicate;
import static task20_transportation_data_base.common.solutions.utils.EntityUtils.entitiesIdsAreEqualsPredicate;
import static task20_transportation_data_base.common.solutions.utils.RandomEntityGenerator.*;

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

    private void initCarriers() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Carrier carrier = createCarrier(i);
            carrierService.save(carrier);
        }
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

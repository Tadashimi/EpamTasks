package task20_transportation_data_base.storage.initor.dbinitor;

import task20_transportation_data_base.application.serviceholder.ServiceHolder;
import task20_transportation_data_base.cargo.service.CargoService;
import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.service.CarrierService;
import task20_transportation_data_base.common.business.exception.checked.InitStorageException;
import task20_transportation_data_base.storage.initor.StorageInitor;
import task20_transportation_data_base.transportation.service.TransportationService;

import java.sql.Connection;
import java.sql.SQLException;

import static task20_transportation_data_base.common.solutions.utils.RandomEntityGenerator.*;

public class DBInitor implements StorageInitor {
    private static final int TOTAL_ENTITIES_IN_GROUP = 6;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public DBInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() throws InitStorageException {
        try {
            Connection connection = TransportationsDataSource.getConnection();
            TransportationSchemeInitor.createTables(connection);
        } catch (SQLException ignored) {

        }

        //initCargos();
        //initCarriers();
        //initTransportations();
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
    /*
    private void initTransportations() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Transportation transportation = createTransportation(i + 1, i + 1 + TOTAL_ENTITIES_IN_GROUP);
            transportationService.save(transportation, Optional.empty());
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
    */
}

package task20_transportation_data_base.storage.initor.dbinitor;

import task20_transportation_data_base.application.serviceholder.ServiceHolder;
import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.service.CargoService;
import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.service.CarrierService;
import task20_transportation_data_base.common.business.exception.checked.InitStorageException;
import task20_transportation_data_base.storage.initor.StorageInitor;
import task20_transportation_data_base.transportation.domain.Transportation;
import task20_transportation_data_base.transportation.service.TransportationService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

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
        /*Scheme manipulation. Uncomment if necessary*/
        //dropTables();
        createTables();

        initCargos();
        initCarriers();
        initTransportations();
    }

    private void createTables() {
        try {
            TransportationSchemeInitor.createTables();
        } catch (SQLException ignored) {
        }
    }

    private void dropTables() {
        try {
            TransportationSchemeInitor.dropTables();
        } catch (SQLException ignored) {
        }
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
            carrierService.save(createCarrier(i));
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
        transportation.setDescription("Description_" + cargoId % carrierId);
        transportation.setBillTo("BillTo_" + cargoId % carrierId);
        transportation.setTransportationBeginDate(LocalDate.now());
        return transportation;
    }

}

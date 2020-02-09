package task20_transportation_data_base.application.serviceholder;

import task20_transportation_data_base.cargo.repo.impl.CargoArrayRepoImpl;
import task20_transportation_data_base.cargo.repo.impl.CargoCollectionRepoImpl;
import task20_transportation_data_base.cargo.repo.impl.CargoDBRepoImpl;
import task20_transportation_data_base.cargo.service.CargoService;
import task20_transportation_data_base.cargo.service.CargoServiceImpl;
import task20_transportation_data_base.carrier.repo.impl.CarrierArrayRepoImpl;
import task20_transportation_data_base.carrier.repo.impl.CarrierCollectionRepoImpl;
import task20_transportation_data_base.carrier.repo.impl.CarrierDBRepoImpl;
import task20_transportation_data_base.carrier.service.CarrierService;
import task20_transportation_data_base.carrier.service.CarrierServiceImpl;
import task20_transportation_data_base.transportation.repo.impl.TransportationArrayRepoImpl;
import task20_transportation_data_base.transportation.repo.impl.TransportationCollectionRepoImpl;
import task20_transportation_data_base.transportation.repo.impl.TransportationDBRepoImpl;
import task20_transportation_data_base.transportation.service.TransportationService;
import task20_transportation_data_base.transportation.service.TransportationServiceImpl;

public final class ServiceHolder {

    private static ServiceHolder instance = null;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    private ServiceHolder(StorageType storageType) {
        SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
        cargoService = initedServiceHolder.cargoService;
        carrierService = initedServiceHolder.carrierService;
        transportationService = initedServiceHolder.transportationService;
    }

    public static void initServiceHolder(StorageType storageType) {
        ServiceHolder.instance = new ServiceHolder(storageType);
    }

    public static ServiceHolder getInstance() {
        return instance;
    }

    private static class SimpleServiceHolder {

        private final CarrierService carrierService;
        private final CargoService cargoService;
        private final TransportationService transportationService;

        public SimpleServiceHolder(
                CarrierService carrierService,
                CargoService cargoService,
                TransportationService transportationService) {
            this.carrierService = carrierService;
            this.cargoService = cargoService;
            this.transportationService = transportationService;
        }
    }

    private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) {
        switch (storageType) {

            case ARRAY: {
                return new SimpleServiceHolder(
                        new CarrierServiceImpl(new CarrierArrayRepoImpl()),
                        new CargoServiceImpl(new CargoArrayRepoImpl()),
                        new TransportationServiceImpl(new TransportationArrayRepoImpl()));
            }

            case DB: {
                return new SimpleServiceHolder(
                        new CarrierServiceImpl(new CarrierDBRepoImpl()),
                        new CargoServiceImpl(new CargoDBRepoImpl()),
                        new TransportationServiceImpl(new TransportationDBRepoImpl()));
            }

            default: {
                return new SimpleServiceHolder(
                        new CarrierServiceImpl(new CarrierCollectionRepoImpl()),
                        new CargoServiceImpl(new CargoCollectionRepoImpl()),
                        new TransportationServiceImpl(new TransportationCollectionRepoImpl()));
            }
        }
    }

    public CarrierService getCarrierService() {
        return carrierService;
    }

    public CargoService getCargoService() {
        return cargoService;
    }

    public TransportationService getTransportationService() {
        return transportationService;
    }
}

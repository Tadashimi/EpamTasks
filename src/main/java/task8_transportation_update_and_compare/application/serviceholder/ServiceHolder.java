package task8_transportation_update_and_compare.application.serviceholder;

import task8_transportation_update_and_compare.cargo.repo.impl.CargoArrayRepoImpl;
import task8_transportation_update_and_compare.cargo.repo.impl.CargoCollectionRepoImpl;
import task8_transportation_update_and_compare.cargo.service.CargoService;
import task8_transportation_update_and_compare.cargo.service.impl.CargoServiceImpl;
import task8_transportation_update_and_compare.carrier.repo.impl.CarrierArrayRepoImpl;
import task8_transportation_update_and_compare.carrier.repo.impl.CarrierCollectionRepoImpl;
import task8_transportation_update_and_compare.carrier.service.CarrierService;
import task8_transportation_update_and_compare.carrier.service.impl.CarrierServiceImpl;
import task8_transportation_update_and_compare.transportation.repo.impl.TransportationArrayRepoImpl;
import task8_transportation_update_and_compare.transportation.repo.impl.TransportationCollectionRepoImpl;
import task8_transportation_update_and_compare.transportation.service.TransportationService;
import task8_transportation_update_and_compare.transportation.service.impl.TransportationServiceImpl;

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

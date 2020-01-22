package task15_transportation_threads.application.serviceholder;

import task15_transportation_threads.cargo.repo.impl.CargoArrayRepoImpl;
import task15_transportation_threads.cargo.repo.impl.CargoCollectionRepoImpl;
import task15_transportation_threads.cargo.service.CargoService;
import task15_transportation_threads.cargo.service.CargoServiceImpl;
import task15_transportation_threads.carrier.repo.impl.CarrierArrayRepoImpl;
import task15_transportation_threads.carrier.repo.impl.CarrierCollectionRepoImpl;
import task15_transportation_threads.carrier.service.CarrierService;
import task15_transportation_threads.carrier.service.CarrierServiceImpl;
import task15_transportation_threads.transportation.repo.impl.TransportationArrayRepoImpl;
import task15_transportation_threads.transportation.repo.impl.TransportationCollectionRepoImpl;
import task15_transportation_threads.transportation.service.TransportationService;
import task15_transportation_threads.transportation.service.TransportationServiceImpl;

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

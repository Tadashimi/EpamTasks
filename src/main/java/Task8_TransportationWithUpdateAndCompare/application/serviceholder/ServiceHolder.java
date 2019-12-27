package main.java.Task8_TransportationWithUpdateAndCompare.application.serviceholder;

import main.java.Task8_TransportationWithUpdateAndCompare.cargo.repo.impl.CargoArrayRepoImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.cargo.repo.impl.CargoCollectionRepoImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.cargo.service.CargoService;
import main.java.Task8_TransportationWithUpdateAndCompare.cargo.service.impl.CargoServiceImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.carrier.repo.impl.CarrierArrayRepoImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.carrier.repo.impl.CarrierCollectionRepoImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.carrier.service.CarrierService;
import main.java.Task8_TransportationWithUpdateAndCompare.carrier.service.impl.CarrierServiceImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.transportation.repo.impl.TransportationArrayRepoImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.transportation.repo.impl.TransportationCollectionRepoImpl;
import main.java.Task8_TransportationWithUpdateAndCompare.transportation.service.TransportationService;
import main.java.Task8_TransportationWithUpdateAndCompare.transportation.service.impl.TransportationServiceImpl;

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

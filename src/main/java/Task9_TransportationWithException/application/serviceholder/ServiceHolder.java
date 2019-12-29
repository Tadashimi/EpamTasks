package  Task9_TransportationWithException.application.serviceholder;

import  Task9_TransportationWithException.cargo.repo.CargoRepo;
import  Task9_TransportationWithException.cargo.repo.impl.CargoArrayRepoImpl;
import  Task9_TransportationWithException.cargo.repo.impl.CargoCollectionRepoImpl;
import  Task9_TransportationWithException.cargo.service.CargoService;
import  Task9_TransportationWithException.cargo.service.impl.CargoServiceImpl;
import  Task9_TransportationWithException.carrier.repo.CarrierRepo;
import  Task9_TransportationWithException.carrier.repo.impl.CarrierArrayRepoImpl;
import  Task9_TransportationWithException.carrier.repo.impl.CarrierCollectionRepoImpl;
import  Task9_TransportationWithException.carrier.service.CarrierService;
import  Task9_TransportationWithException.carrier.service.impl.CarrierServiceImpl;
import  Task9_TransportationWithException.transportation.repo.TransportationRepo;
import  Task9_TransportationWithException.transportation.repo.impl.TransportationArrayRepoImpl;
import  Task9_TransportationWithException.transportation.repo.impl.TransportationCollectionRepoImpl;
import  Task9_TransportationWithException.transportation.service.TransportationService;
import  Task9_TransportationWithException.transportation.service.impl.TransportationServiceImpl;

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
        CargoRepo cargoRepo;
        CarrierRepo carrierRepo;
        TransportationRepo transportationRepo;

        switch (storageType) {

            case ARRAY: {
                cargoRepo = new CargoArrayRepoImpl();
                carrierRepo = new CarrierArrayRepoImpl();
                transportationRepo = new TransportationArrayRepoImpl();
            }

            default: {
                cargoRepo = new CargoCollectionRepoImpl();
                carrierRepo = new CarrierCollectionRepoImpl();
                transportationRepo = new TransportationCollectionRepoImpl();
            }
        }
        return new SimpleServiceHolder(
                new CarrierServiceImpl(carrierRepo),
                new CargoServiceImpl(cargoRepo),
                new TransportationServiceImpl(transportationRepo, cargoRepo, carrierRepo));
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

package  task9_transportation_exceptions.application.serviceholder;

import  task9_transportation_exceptions.cargo.repo.CargoRepo;
import  task9_transportation_exceptions.cargo.repo.impl.CargoArrayRepoImpl;
import  task9_transportation_exceptions.cargo.repo.impl.CargoCollectionRepoImpl;
import  task9_transportation_exceptions.cargo.service.CargoService;
import  task9_transportation_exceptions.cargo.service.impl.CargoServiceImpl;
import  task9_transportation_exceptions.carrier.repo.CarrierRepo;
import  task9_transportation_exceptions.carrier.repo.impl.CarrierArrayRepoImpl;
import  task9_transportation_exceptions.carrier.repo.impl.CarrierCollectionRepoImpl;
import  task9_transportation_exceptions.carrier.service.CarrierService;
import  task9_transportation_exceptions.carrier.service.impl.CarrierServiceImpl;
import  task9_transportation_exceptions.transportation.repo.TransportationRepo;
import  task9_transportation_exceptions.transportation.repo.impl.TransportationArrayRepoImpl;
import  task9_transportation_exceptions.transportation.repo.impl.TransportationCollectionRepoImpl;
import  task9_transportation_exceptions.transportation.service.TransportationService;
import  task9_transportation_exceptions.transportation.service.impl.TransportationServiceImpl;

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

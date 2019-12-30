package Task12_TransportationWithXMLandExportToFile.application.serviceholder;

import Task12_TransportationWithXMLandExportToFile.cargo.repo.impl.CargoArrayRepoImpl;
import Task12_TransportationWithXMLandExportToFile.cargo.repo.impl.CargoCollectionRepoImpl;
import Task12_TransportationWithXMLandExportToFile.cargo.service.CargoService;
import Task12_TransportationWithXMLandExportToFile.cargo.service.CargoServiceImpl;
import Task12_TransportationWithXMLandExportToFile.carrier.repo.impl.CarrierArrayRepoImpl;
import Task12_TransportationWithXMLandExportToFile.carrier.repo.impl.CarrierCollectionRepoImpl;
import Task12_TransportationWithXMLandExportToFile.carrier.service.CarrierService;
import Task12_TransportationWithXMLandExportToFile.carrier.service.CarrierServiceImpl;
import Task12_TransportationWithXMLandExportToFile.transportation.repo.impl.TransportationArrayRepoImpl;
import Task12_TransportationWithXMLandExportToFile.transportation.repo.impl.TransportationCollectionRepoImpl;
import Task12_TransportationWithXMLandExportToFile.transportation.service.TransportationService;
import Task12_TransportationWithXMLandExportToFile.transportation.service.TransportationServiceImpl;

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

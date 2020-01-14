package task8_transportation_update_and_compare.application;

import task8_transportation_update_and_compare.application.serviceholder.ServiceHolder;
import task8_transportation_update_and_compare.application.serviceholder.StorageType;
import task8_transportation_update_and_compare.cargo.domain.Cargo;
import task8_transportation_update_and_compare.cargo.domain.FoodCargo;
import task8_transportation_update_and_compare.cargo.service.CargoService;
import task8_transportation_update_and_compare.cargo.service.cargoListComparator.CompareField;
import task8_transportation_update_and_compare.carrier.service.CarrierService;
import task8_transportation_update_and_compare.common.solutions.utils.CollectionUtils;
import task8_transportation_update_and_compare.storage.initor.InMemoryStorageInitor;
import task8_transportation_update_and_compare.storage.initor.StorageInitor;
import task8_transportation_update_and_compare.transportation.service.TransportationService;

public class Application {
    private static final String SEPARATOR = "======================================================================";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();
        transportationService = ServiceHolder.getInstance().getTransportationService();

        StorageInitor storageInitor = new InMemoryStorageInitor();
        storageInitor.initStorage();

        printStorageData();
        doSearchOperations();

        sortCargoArray();
        doUpdateOperations();
    }

    private static void printStorageData() {
        printSeparator();
        System.out.println("ALL CARGOS");
        cargoService.printAll();
        printSeparator();

        System.out.println("ALL CARRIERS");
        carrierService.printAll();
        printSeparator();

        System.out.println("ALL TRANSPOORTATIONS");
        transportationService.printAll();
        printSeparator();
    }

    private static void doSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
        CollectionUtils.printCollection(cargoService.getByName("Clothers_Name_1"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
        CollectionUtils.printCollection(carrierService.getByName("Carrier_Name"));
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void sortCargoArray() {
        System.out.println("SORT CARGOS BY NAME");
        CollectionUtils.printCollection(cargoService.sortCargo(CompareField.NAME));
        printSeparator();

        System.out.println("SORT CARGOS BY WEIGHT");
        CollectionUtils.printCollection(cargoService.sortCargo(CompareField.WEIGHT));
        printSeparator();

        System.out.println("SORT CARGOS BY NAME AND WEIGHT");
        CollectionUtils.printCollection(cargoService.sortCargo(CompareField.NAME_AND_WEIGHT));
        printSeparator();
    }

    private static void doUpdateOperations() {
        System.out.println("UPDATE OPERATION DEMO");
        Long cargoId = 2L;
        Cargo newCargo = new FoodCargo();
        newCargo.setName("NEW_FOOD_CARGO");
        newCargo.setWeight(123456);
        System.out.println("Old cargo: " + cargoService.getById(cargoId));
        cargoService.update(cargoId, newCargo);
        System.out.println("New cargo: " + cargoService.getById(cargoId));
        printSeparator();
    }
}

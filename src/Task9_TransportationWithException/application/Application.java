package Task9_TransportationWithException.application;

import Task9_TransportationWithException.application.serviceholder.ServiceHolder;
import Task9_TransportationWithException.application.serviceholder.StorageType;
import Task9_TransportationWithException.cargo.domain.Cargo;
import Task9_TransportationWithException.cargo.domain.CargoField;
import Task9_TransportationWithException.cargo.search.CargoSearchCondition;
import Task9_TransportationWithException.cargo.service.CargoService;
import Task9_TransportationWithException.carrier.service.CarrierService;
import Task9_TransportationWithException.common.solutions.exception.TransportationException;
import Task9_TransportationWithException.common.solutions.search.OrderType;
import Task9_TransportationWithException.common.solutions.utils.CollectionUtils;
import Task9_TransportationWithException.storage.initor.StorageInitor;
import Task9_TransportationWithException.storage.initor.impl.InMemoryStorageInitor;
import Task9_TransportationWithException.transportation.domain.Transportation;
import Task9_TransportationWithException.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static Task9_TransportationWithException.cargo.domain.CargoField.NAME;
import static Task9_TransportationWithException.cargo.domain.CargoField.WEIGHT;
import static Task9_TransportationWithException.common.solutions.search.OrderType.ASC;
import static Task9_TransportationWithException.common.solutions.search.OrderType.DESC;
import static java.util.Collections.singletonList;

public class Application {

    private static final String SEPARATOR = "--------------";
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
        demoSearchOperations();
        demoSortOperations();
        demoDeleteOperation();
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
        CollectionUtils.printCollection(cargoService.findByName("Clothers_Name_1"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
        CollectionUtils.printCollection(carrierService.findByName("Carrier_Name"));
    }

    private static void printStorageData() {
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

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void demoSortOperations() {
        demoCargoSorting(singletonList(NAME), ASC);
        demoCargoSorting(singletonList(NAME), DESC);

        demoCargoSorting(singletonList(WEIGHT), ASC);
        demoCargoSorting(singletonList(WEIGHT), DESC);

        demoCargoSorting(Arrays.asList(NAME, WEIGHT), ASC);
        demoCargoSorting(Arrays.asList(NAME, WEIGHT), DESC);
    }

    private static String getOrderingConditionsAsString(CargoSearchCondition condition) {
        StringBuilder result = new StringBuilder();
        result.append(" ORDER BY ");

        Iterator<CargoField> iter = condition.getSortFields().iterator();
        while (iter.hasNext()) {
            CargoField fld = iter.next();
            result.append(fld);

            if (iter.hasNext()) {
                result.append(",");
            }
        }

        result.append(" ").append(condition.getOrderType());

        return result.toString();
    }

    private static void demoCargoSorting(Collection<CargoField> sortFields, OrderType orderType) {
        CargoSearchCondition cargoSearchCondition = new CargoSearchCondition();
        cargoSearchCondition.setOrderType(orderType);
        cargoSearchCondition.setSortFields(new LinkedHashSet<>(sortFields));
        System.out.println(
                "---------Sorting '" + getOrderingConditionsAsString(cargoSearchCondition) + "'------");
        cargoService.search(cargoSearchCondition);
        cargoService.printAll();
        System.out.println();
    }

    private static void demoDeleteOperation() {
        printSeparator();
        System.out.println("DELETE CARGO DEMO");
        long cargoId = 1L;
        removeCargo(cargoId);

        System.out.print("Remove transportation: ");
        Cargo cargo = cargoService.getById(cargoId);
        Transportation transportation = cargo.getTransportations().get(0);
        if (transportationService.deleteById(transportation.getId())) {
            System.out.println("success");
        }

        removeCargo(cargoId);
    }

    private static void removeCargo(long cargoId) {
        try {
            cargoService.deleteById(cargoId);
            System.out.println("Cargo was deleted");
        } catch (TransportationException e) {
            System.out.println(e.getMessage());
        }
    }

}

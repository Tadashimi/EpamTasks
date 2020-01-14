package  task9_transportation_exceptions.application;

import  task9_transportation_exceptions.application.serviceholder.ServiceHolder;
import  task9_transportation_exceptions.application.serviceholder.StorageType;
import  task9_transportation_exceptions.cargo.domain.Cargo;
import  task9_transportation_exceptions.cargo.domain.CargoField;
import  task9_transportation_exceptions.cargo.search.CargoSearchCondition;
import  task9_transportation_exceptions.cargo.service.CargoService;
import  task9_transportation_exceptions.carrier.service.CarrierService;
import  task9_transportation_exceptions.common.solutions.exception.TransportationException;
import  task9_transportation_exceptions.common.solutions.search.OrderType;
import  task9_transportation_exceptions.common.solutions.utils.CollectionUtils;
import  task9_transportation_exceptions.storage.initor.StorageInitor;
import  task9_transportation_exceptions.storage.initor.impl.InMemoryStorageInitor;
import  task9_transportation_exceptions.transportation.domain.Transportation;
import  task9_transportation_exceptions.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static  task9_transportation_exceptions.cargo.domain.CargoField.NAME;
import static  task9_transportation_exceptions.cargo.domain.CargoField.WEIGHT;
import static  task9_transportation_exceptions.common.solutions.search.OrderType.ASC;
import static  task9_transportation_exceptions.common.solutions.search.OrderType.DESC;
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

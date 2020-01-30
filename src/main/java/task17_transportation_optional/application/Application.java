package task17_transportation_optional.application;

import task17_transportation_optional.application.serviceholder.ServiceHolder;
import task17_transportation_optional.application.serviceholder.StorageType;
import task17_transportation_optional.cargo.domain.Cargo;
import task17_transportation_optional.cargo.domain.CargoField;
import task17_transportation_optional.cargo.search.CargoSearchCondition;
import task17_transportation_optional.cargo.service.CargoService;
import task17_transportation_optional.carrier.service.CarrierService;
import task17_transportation_optional.common.business.exception.checked.InitStorageException;
import task17_transportation_optional.common.business.exception.checked.ReportException;
import task17_transportation_optional.common.business.exception.unchecked.ElementNotFoundException;
import task17_transportation_optional.common.solutions.search.OrderType;
import task17_transportation_optional.common.solutions.utils.CollectionUtils;
import task17_transportation_optional.reporting.ReportDefaultService;
import task17_transportation_optional.reporting.ReportService;
import task17_transportation_optional.storage.initor.InitStorageType;
import task17_transportation_optional.storage.initor.StorageInitor;
import task17_transportation_optional.transportation.service.TransportationService;

import java.util.*;

import static java.util.Collections.singletonList;
import static task17_transportation_optional.cargo.domain.CargoField.NAME;
import static task17_transportation_optional.cargo.domain.CargoField.WEIGHT;
import static task17_transportation_optional.common.solutions.search.OrderType.ASC;
import static task17_transportation_optional.common.solutions.search.OrderType.DESC;
import static task17_transportation_optional.storage.initor.StorageInitorFactory.getStorageInitor;

public class Application {

    private static final String SEPARATOR = "\n--------------\n";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        try {
            ServiceHolder.initServiceHolder(StorageType.COLLECTION);
            cargoService = ServiceHolder.getInstance().getCargoService();
            carrierService = ServiceHolder.getInstance().getCarrierService();
            transportationService = ServiceHolder.getInstance().getTransportationService();

            StorageInitor storageInitor = getStorageInitor(InitStorageType.CONCURRENT_INITOR);
            storageInitor.initStorage();

            printStorageData();
            demoSearchOperations();
            demoSortOperations();
            demoDeleteElement();

            demoReportService();
        } catch (InitStorageException e) {
            e.getCause().printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        try {
            System.out.println(cargoService.findById(1L));
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
        printSeparator();

        System.out.println("SEARCH CARGO BY ID = 8");
        try {
            System.out.println(cargoService.findById(8L));
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
                "\n---------Sorting '" + getOrderingConditionsAsString(cargoSearchCondition) + "'------\n");
        cargoService.search(cargoSearchCondition);
        cargoService.printAll();
    }

    private static void demoDeleteElement() {
        System.out.println("\n------Demo  delete element------------\n");
        Optional<Cargo> cargoWithoutTransportation = cargoService.getByIdFetchingTransportations(5L);
        cargoWithoutTransportation.ifPresent(Application::deleteCargo);
        printSeparator();

        Long firstCargo = cargoService.getAll().get(0).getId();
        Optional<Cargo> cargoWithTransportation = cargoService.getByIdFetchingTransportations(firstCargo);
        cargoWithTransportation.ifPresent(Application::deleteCargo);
    }

    private static void deleteCargo(Cargo cargo) {
        System.out.println("Try to delete cargo");
        System.out.println("Cargo details:");
        System.out.println("id: " + cargo.getId());
        System.out.println("name: " + cargo.getName());
        System.out.println("total transportations: " + (cargo.getTransportations() != null ?
                cargo.getTransportations().size() : 0));
        System.out.println();
        try {
            cargoService.deleteById(cargo.getId());
        } catch (Exception e) {
            System.out.println("OOPS, something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    private static void demoReportService() throws ReportException {
        System.out.println("\n----------Demo report service ---------------\n");
        ReportService reportService = new ReportDefaultService(
                cargoService, carrierService, transportationService
        );
        reportService.exportData();
    }
}

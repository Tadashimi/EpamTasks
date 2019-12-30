package Task12_TransportationWithXMLandExportToFile.application;

import Task12_TransportationWithXMLandExportToFile.application.serviceholder.ServiceHolder;
import Task12_TransportationWithXMLandExportToFile.application.serviceholder.StorageType;
import Task12_TransportationWithXMLandExportToFile.cargo.domain.Cargo;
import Task12_TransportationWithXMLandExportToFile.cargo.domain.CargoField;
import Task12_TransportationWithXMLandExportToFile.cargo.search.CargoSearchCondition;
import Task12_TransportationWithXMLandExportToFile.cargo.service.CargoService;
import Task12_TransportationWithXMLandExportToFile.carrier.service.CarrierService;
import Task12_TransportationWithXMLandExportToFile.common.business.exception.checked.InitStorageException;
import Task12_TransportationWithXMLandExportToFile.common.solutions.search.OrderType;
import Task12_TransportationWithXMLandExportToFile.common.solutions.utils.CollectionUtils;
import Task12_TransportationWithXMLandExportToFile.dataExport.impl.TransportationSystemDataExport;
import Task12_TransportationWithXMLandExportToFile.storage.initor.InitStorageType;
import Task12_TransportationWithXMLandExportToFile.storage.initor.StorageInitor;
import Task12_TransportationWithXMLandExportToFile.transportation.service.TransportationService;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static Task12_TransportationWithXMLandExportToFile.cargo.domain.CargoField.NAME;
import static Task12_TransportationWithXMLandExportToFile.cargo.domain.CargoField.WEIGHT;
import static Task12_TransportationWithXMLandExportToFile.common.solutions.search.OrderType.ASC;
import static Task12_TransportationWithXMLandExportToFile.common.solutions.search.OrderType.DESC;
import static Task12_TransportationWithXMLandExportToFile.storage.initor.StorageInitorFactory.getStorageInitor;
import static java.util.Collections.singletonList;

public class Application {

    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {

        try {
            ServiceHolder.initServiceHolder(StorageType.COLLECTION);
            cargoService = ServiceHolder.getInstance().getCargoService();
            carrierService = ServiceHolder.getInstance().getCarrierService();
            transportationService = ServiceHolder.getInstance().getTransportationService();

            StorageInitor storageInitor = getStorageInitor(InitStorageType.XML_FILE);
            storageInitor.initStorage();

            printStorageData();
            demoSearchOperations();
            demoSortOperations();
            demoExceptions();
            demoExportData();
        } catch (InitStorageException e) {
            e.getCause().printStackTrace();
        }
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.findById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.findById(8L));
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

    private static void demoExceptions() {
        System.out.println("------Demo  exceptions------------");
        Long firstCargo = cargoService.getAll().get(0).getId();
        Cargo cargo = cargoService.getByIdFetchingTransportations(firstCargo);
        System.out.println("Try to delete cargo");
        System.out.println("Cargo details:");
        System.out.println("id: " + cargo.getId());
        System.out.println("name: " + cargo.getName());
        System.out.println("total transportations: " + (cargo.getTransportations() != null ? cargo
                .getTransportations().size() : 0));
        System.out.println();
        try {
            cargoService.deleteById(cargo.getId());
        } catch (Exception e) {
            System.out.println("OOPS, something went wrong!");
            System.out.println(e.getMessage());
        }
    }

    private static void demoExportData() {
        TransportationSystemDataExport transportationSystemDataExport = new TransportationSystemDataExport(cargoService, carrierService, transportationService);
        try {
            transportationSystemDataExport.exportData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            File outputFile = transportationSystemDataExport.getOutputFile();
            if (outputFile != null) {
                System.out.println("Data was exported to file: " + outputFile.getAbsolutePath());
            }
        }
    }
}

package task20_transportation_data_base.application;

import task20_transportation_data_base.application.serviceholder.ServiceHolder;
import task20_transportation_data_base.application.serviceholder.StorageType;
import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.domain.CargoField;
import task20_transportation_data_base.cargo.search.CargoSearchCondition;
import task20_transportation_data_base.cargo.service.CargoService;
import task20_transportation_data_base.cargo.service.CargoServiceImpl;
import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.service.CarrierService;
import task20_transportation_data_base.carrier.service.CarrierServiceImpl;
import task20_transportation_data_base.common.business.exception.checked.InitStorageException;
import task20_transportation_data_base.common.business.exception.checked.ReportException;
import task20_transportation_data_base.common.solutions.search.OrderType;
import task20_transportation_data_base.common.solutions.utils.CollectionUtils;
import task20_transportation_data_base.reporting.ReportDefaultService;
import task20_transportation_data_base.reporting.ReportService;
import task20_transportation_data_base.storage.initor.InitStorageType;
import task20_transportation_data_base.storage.initor.StorageInitor;
import task20_transportation_data_base.transportation.domain.Transportation;
import task20_transportation_data_base.transportation.service.TransportationService;

import java.util.*;

import static java.util.Collections.singletonList;
import static task20_transportation_data_base.cargo.domain.CargoField.NAME;
import static task20_transportation_data_base.cargo.domain.CargoField.WEIGHT;
import static task20_transportation_data_base.common.solutions.search.OrderType.ASC;
import static task20_transportation_data_base.common.solutions.search.OrderType.DESC;
import static task20_transportation_data_base.common.solutions.utils.RandomEntityGenerator.createCarrier;
import static task20_transportation_data_base.common.solutions.utils.RandomEntityGenerator.createClothersCargo;
import static task20_transportation_data_base.storage.initor.StorageInitorFactory.getStorageInitor;

public class Application {

    private static final String SEPARATOR = "\n--------------\n";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        try {
            StorageType currentStorageType = StorageType.DB;
            ServiceHolder.initServiceHolder(currentStorageType);
            cargoService = ServiceHolder.getInstance().getCargoService();
            carrierService = ServiceHolder.getInstance().getCarrierService();
            transportationService = ServiceHolder.getInstance().getTransportationService();

            StorageInitor storageInitor = getStorageInitor(InitStorageType.DB_INITOR);
            storageInitor.initStorage();

            //demoSaveInOneTransaction(currentStorageType);
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

    private static void demoSaveInOneTransaction(StorageType currentStorageType) throws Exception {
        if (currentStorageType.equals(StorageType.DB)) {
            List<Cargo> cargos = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                cargos.add(createClothersCargo(i));
            }
            ((CargoServiceImpl) cargoService).saveSeveralCargoes(cargos);

            List<Carrier> carriers = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                carriers.add(createCarrier(i));
            }
            ((CarrierServiceImpl) carrierService).saveSeveralCarriers(carriers);
        }
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        Optional<Cargo> cargo = cargoService.findById(1L);
        if (cargo.isPresent()) {
            System.out.println(cargo);
        } else {
            System.out.println("Cargo was not found");
        }
        printSeparator();

        System.out.println("SEARCH CARGO BY ID = 8");
        cargo = cargoService.findById(8L);
        if (cargo.isPresent()) {
            System.out.println(cargo);
        } else {
            System.out.println("Cargo was not found");
        }
        printSeparator();

        System.out.println("SEARCH TRANSPORTATION BY ID = 15");
        Optional<Transportation> transportation = transportationService.findById(15L);
        if (transportation.isPresent()) {
            System.out.println(transportation);
        } else {
            System.out.println("transportation was not found");
        }
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Jeans_2'");
        CollectionUtils.printCollection(cargoService.findByName("Jeans"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name_1'");
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
        List<Cargo> cargos = cargoService.search(cargoSearchCondition);
        cargos.forEach(System.out::println);
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
            System.out.println("Cargo has been successfully deleted.");
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

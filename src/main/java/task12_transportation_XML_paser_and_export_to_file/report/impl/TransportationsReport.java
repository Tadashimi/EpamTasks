package task12_transportation_XML_paser_and_export_to_file.report.impl;

import task12_transportation_XML_paser_and_export_to_file.cargo.domain.Cargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.domain.ClothersCargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.domain.FoodCargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.service.CargoService;
import task12_transportation_XML_paser_and_export_to_file.carrier.domain.Carrier;
import task12_transportation_XML_paser_and_export_to_file.carrier.service.CarrierService;
import task12_transportation_XML_paser_and_export_to_file.common.solutions.utils.JavaUtilDataUtils;
import task12_transportation_XML_paser_and_export_to_file.report.ReportComponent;
import task12_transportation_XML_paser_and_export_to_file.transportation.domain.Transportation;
import task12_transportation_XML_paser_and_export_to_file.transportation.service.TransportationService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransportationsReport implements ReportComponent {
    private final String COMPONENT_SEPARATOR = "|";
    private final String CARGO_SECTION_LABEL_IN_FILE = "--Cargo section--";
    private final String CARRIER_SECTION_LABEL_IN_FILE = "--Carrier section--";
    private final String TRANSPORTATION_SECTION_LABEL_IN_FILE = "--Transportation section--";

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    private File outputFile = null;

    public TransportationsReport(CargoService cargoService, CarrierService carrierService, TransportationService transportationService) {
        this.cargoService = cargoService;
        this.carrierService = carrierService;
        this.transportationService = transportationService;
    }

    public File getOutputFile() {
        return outputFile;
    }

    @Override
    public void exportData() throws IOException {
        outputFile = File.createTempFile("output-data", ".txt");

        List<String> dataList = createDataList();
        Files.write(outputFile.toPath(), dataList);
    }

    private List<String> createDataList() {
        List<String> dataList = new ArrayList<>();
        dataList.addAll(getCargoesDataList());
        dataList.add("\n");
        dataList.addAll(getCarriersDataList());
        dataList.add("\n");
        dataList.addAll(getTransportationsDataList());
        return dataList;
    }

    private List<String> getCargoesDataList() {
        List<String> cargoesData = new ArrayList<>();
        cargoesData.add(CARGO_SECTION_LABEL_IN_FILE);
        List<Cargo> cargoes = cargoService.getAll();
        for (Cargo cargo : cargoes) {
            cargoesData.add(getCargoDataString(cargo));
        }
        return cargoesData;
    }

    private String getCargoDataString(Cargo cargo) {
        StringBuilder cargoData = new StringBuilder();
        cargoData.append(cargo.getId());
        cargoData.append(COMPONENT_SEPARATOR);
        cargoData.append(cargo.getName());
        cargoData.append(COMPONENT_SEPARATOR);
        cargoData.append(cargo.getWeight());
        cargoData.append(COMPONENT_SEPARATOR);
        cargoData.append(cargo.getCargoType());
        cargoData.append(COMPONENT_SEPARATOR);
        switch (cargo.getCargoType()) {
            case FOOD:
                Date expirationDate = ((FoodCargo) cargo).getExpirationDate();
                cargoData.append(JavaUtilDataUtils.dateToString(expirationDate));
                cargoData.append(COMPONENT_SEPARATOR);
                cargoData.append(((FoodCargo) cargo).getStoreTemperature());
                break;
            default:
                cargoData.append(((ClothersCargo) cargo).getSize());
                cargoData.append(COMPONENT_SEPARATOR);
                cargoData.append(((ClothersCargo) cargo).getMaterial());
        }
        return cargoData.toString();
    }

    private List<String> getCarriersDataList() {
        List<String> carriersData = new ArrayList<>();
        carriersData.add("\n" + CARRIER_SECTION_LABEL_IN_FILE);
        List<Carrier> carriers = carrierService.getAll();
        for (Carrier carrier : carriers) {
            carriersData.add(getCarrierDataString(carrier));
        }
        return carriersData;
    }

    private String getCarrierDataString(Carrier carrier) {
        StringBuilder carrierData = new StringBuilder();
        carrierData.append(carrier.getId());
        carrierData.append(COMPONENT_SEPARATOR);
        carrierData.append(carrier.getName());
        carrierData.append(COMPONENT_SEPARATOR);
        carrierData.append(carrier.getAddress());
        carrierData.append(COMPONENT_SEPARATOR);
        carrierData.append(carrier.getCarrierType());
        return carrierData.toString();
    }

    private List<String> getTransportationsDataList() {
        List<String> transportationsData = new ArrayList<>();
        transportationsData.add("\n" + TRANSPORTATION_SECTION_LABEL_IN_FILE);
        List<Transportation> transportations = transportationService.getAll();
        for (Transportation transportation : transportations) {
            transportationsData.add(getTransportationDataString(transportation));
        }
        return transportationsData;
    }

    private String getTransportationDataString(Transportation transportation) {
        StringBuilder transportationData = new StringBuilder();
        transportationData.append(transportation.getCargo().getId());
        transportationData.append(COMPONENT_SEPARATOR);
        transportationData.append(transportation.getCarrier().getId());
        transportationData.append(COMPONENT_SEPARATOR);
        transportationData.append(transportation.getDescription());
        transportationData.append(COMPONENT_SEPARATOR);
        transportationData.append(transportation.getBillTo());
        transportationData.append(COMPONENT_SEPARATOR);
        Date transportationDate = transportation.getTransportationBeginDate();
        transportationData.append(JavaUtilDataUtils.dateToString(transportationDate));
        return transportationData.toString();
    }

}

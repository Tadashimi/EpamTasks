package task13_transportataion_sax_parser.storage.initor.impl;

import task13_transportataion_sax_parser.cargo.domain.Cargo;
import task13_transportataion_sax_parser.cargo.domain.CargoType;
import task13_transportataion_sax_parser.cargo.domain.ClothersCargo;
import task13_transportataion_sax_parser.cargo.domain.FoodCargo;
import task13_transportataion_sax_parser.carrier.domain.Carrier;
import task13_transportataion_sax_parser.carrier.domain.CarrierType;
import task13_transportataion_sax_parser.common.business.exception.checked.InitStorageException;
import task13_transportataion_sax_parser.common.solutions.utils.FileUtils;
import task13_transportataion_sax_parser.common.solutions.utils.JavaUtilDataUtils;
import task13_transportataion_sax_parser.transportation.domain.Transportation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TextFileDataInitor extends FileDataInitor {

    private static final String FILE = "Task13_TransportataionWithSAXParsing/init-data.txt";
    private static final String CARGO_SECTION_LABEL_IN_FILE = "--Cargo section--";
    private static final String CARRIER_SECTION_LABEL_IN_FILE = "--Carrier section--";
    private static final String TRANSPORTATION_SECTION_LABEL_IN_FILE = "--Transportation section--";

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();

            Map<String, Cargo> cargoMap = parseCargosFromFile(file);
            Map<String, Carrier> carrierMap = parseCarriersFromFile(file);
            List<ParsedTransportation> transportations = parseTransportationsDataFromFile(file);
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson13", FILE);
    }

    private Map<String, Cargo> parseCargosFromFile(File file) throws IOException, ParseException {
        Map<String, Cargo> cargos = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARGO_SECTION_LABEL_IN_FILE);

        for (String cargoStr : fileData) {
            SimpleEntry<String, Cargo> cargoData = parseCargoFromString(cargoStr);
            if (cargoData != null) {
                cargos.put(cargoData.getKey(), cargoData.getValue());
            }
        }

        return cargos;
    }

    private SimpleEntry<String, Cargo> parseCargoFromString(String cargoAsStr) throws ParseException {
        String[] cargoData = cargoAsStr.split("\\|");

        if (cargoData.length > 0) {
            int index = 0;
            String id = cargoData[index++].trim();
            CargoType cargoType = CargoType.valueOf(cargoData[index++].trim());
            String name = cargoData[index++].trim();
            int weight = Integer.parseInt(cargoData[index++].trim());

            Cargo cargo;
            if (CargoType.CLOTHERS.equals(cargoType)) {
                ClothersCargo clothersCargo = new ClothersCargo();
                clothersCargo.setSize(cargoData[index++].trim());
                clothersCargo.setMaterial(cargoData[index].trim());
                cargo = clothersCargo;
            } else {
                FoodCargo foodCargo = new FoodCargo();
                foodCargo.setExpirationDate(JavaUtilDataUtils.valueOf(cargoData[index++].trim()));
                foodCargo.setStoreTemperature(Integer.parseInt(cargoData[index].trim()));
                cargo = foodCargo;
            }

            cargo.setName(name);
            cargo.setWeight(weight);

            return new SimpleEntry<>(id, cargo);
        }

        return null;
    }

    private Map<String, Carrier> parseCarriersFromFile(File file) throws IOException {
        Map<String, Carrier> carriers = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARRIER_SECTION_LABEL_IN_FILE);

        for (String carriersStr : fileData) {
            SimpleEntry<String, Carrier> carrierData = parseCarrierFromString(carriersStr);
            if (carrierData != null) {
                carriers.put(carrierData.getKey(), carrierData.getValue());
            }
        }

        return carriers;
    }


    private SimpleEntry<String, Carrier> parseCarrierFromString(String carrierAsStr) {
        String[] data = carrierAsStr.split("\\|");

        if (data.length > 0) {
            int index = 0;
            String id = data[index++].trim();
            Carrier carrier = new Carrier();
            carrier.setName(data[index++].trim());
            carrier.setAddress(data[index++].trim());
            carrier.setCarrierType(CarrierType.valueOf(data[index].trim()));

            return new SimpleEntry<>(id, carrier);
        }

        return null;
    }

    private List<ParsedTransportation> parseTransportationsDataFromFile(File file)
            throws IOException, ParseException {
        List<ParsedTransportation> result = new ArrayList<>();
        List<String> transportationSection = readSectionInFile(file,
                TRANSPORTATION_SECTION_LABEL_IN_FILE);

        for (String transportationDataStr : transportationSection) {
            result.add(parseTransportationDataFromString(transportationDataStr));
        }
        return result;
    }

    private ParsedTransportation parseTransportationDataFromString(String transportationDataStr)
            throws ParseException {
        String data[] = transportationDataStr.split("\\|");

        ParsedTransportation result = null;
        if (data.length > 0) {
            result = new ParsedTransportation();
            int index = 0;
            result.cargoRef = data[index++].trim();
            result.carrierRef = data[index++].trim();

            Transportation transportation = new Transportation();
            transportation.setDescription(data[index++].trim());
            transportation.setBillTo(data[index++].trim());
            transportation.setTransportationBeginDate(JavaUtilDataUtils.valueOf(data[index].trim()));
            result.transportation = transportation;
        }

        return result;
    }

    private List<String> readSectionInFile(File file, String sectionLabel)
            throws IOException {

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean sectionHasStarted = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (sectionHasStarted) {
                    if (line.isEmpty()) {
                        break;
                    }
                    result.add(line);
                }

                if (!sectionHasStarted && sectionLabel.equals(line)) {
                    sectionHasStarted = true;
                }
            }
        }

        return result;
    }
}
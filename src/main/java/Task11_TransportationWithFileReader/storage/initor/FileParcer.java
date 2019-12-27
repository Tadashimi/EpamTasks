package Task11_TransportationWithFileReader.storage.initor;

import Task11_TransportationWithFileReader.cargo.domain.Cargo;
import Task11_TransportationWithFileReader.cargo.domain.CargoType;
import Task11_TransportationWithFileReader.cargo.domain.ClothersCargo;
import Task11_TransportationWithFileReader.cargo.domain.FoodCargo;
import Task11_TransportationWithFileReader.carrier.domain.Carrier;
import Task11_TransportationWithFileReader.carrier.domain.CarrierType;
import Task11_TransportationWithFileReader.storage.initor.exception.InitException;
import Task11_TransportationWithFileReader.transportation.domain.Transportation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileParcer {

    private static List<Cargo> cargoList = new ArrayList<>();
    private static List<Carrier> carrierList = new ArrayList<>();
    private static List<Transportation> transportationList = new ArrayList<>();

    private static final String cargoBegin = "-----CARGOES BEGIN-----";
    private static final String CARGOS_BEGIN = "-----CARGOES BEGIN-----";
    private static final String CARGOS_END = "-----CARGOES END-----";
    private static final String CARRIERS_BEGIN = "-----CARRIERS BEGIN-----";
    private static final String CARRIERS_END = "-----CARRIERS END-----";
    private static final String TRANSPORTATIONS_BEGIN = "-----TRANSPORTATIONS BEGIN-----";
    private static final String TRANSPORTATIONS_END = "-----TRANSPORTATIONS END-----";
    private static final String SEPARATOR = "\\|";

    private static State currentState = State.BEGINNING;

    enum State {
        BEGINNING,
        CARGOS_START,
        CARGOS_END,
        CARRIERS_BEGIN,
        CARRIERS_END,
        TRANSPORTATION_BEGIN,
        TRANSPORTATION_END,
        END
    }

    public static void parseFile(String filePath) {
        try {
            File fileWithData = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileWithData));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                switch (currentState) {
                    case BEGINNING:
                        if (line.equals(CARGOS_BEGIN)) {
                            currentState = State.CARGOS_START;
                        }
                        // if line eq -----CARGOES BEGIN-----
                        // then state = CARGO_START
                        break;
                    case CARGOS_START:
                        if (!line.equals(CARGOS_END)) {
                            cargoList.add(parseCargoData(line));
                        } else {
                            currentState = State.CARGOS_END;
                        }
                        break;
                    case CARGOS_END:
                        if (line.equals(CARRIERS_BEGIN)) {
                            currentState = State.CARRIERS_BEGIN;
                        }
                        break;
                    case CARRIERS_BEGIN:
                        if (!line.equals(CARRIERS_END)) {
                            carrierList.add(parseCarrierData(line));
                        } else {
                            currentState = State.CARRIERS_END;
                        }
                        break;
                    case CARRIERS_END:
                        if (line.equals(TRANSPORTATIONS_BEGIN)) {
                            currentState = State.TRANSPORTATION_BEGIN;
                        }
                        break;
                    case TRANSPORTATION_BEGIN:
                        if (!line.equals(TRANSPORTATIONS_END)) {
                            transportationList.add(parseTransportationData(line));
                        } else {
                            currentState = State.TRANSPORTATION_END;
                        }
                        break;
                    case TRANSPORTATION_END:
                        if (line.equals(TRANSPORTATIONS_BEGIN)) {
                            currentState = State.END;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            throw new InitException("Error while reading data from file");
        }
    }

    private static Transportation parseTransportationData(String transportationData) {
        Transportation transportation = new Transportation();
        String[] transportationDataStrings = transportationData.split(SEPARATOR);
        transportation.setDescription(transportationDataStrings[0]);
        transportation.setBillTo(transportationDataStrings[1]);
        transportation.setTransportationBeginDate(parseDate(transportationDataStrings[2], "transportation begin date"));
        return transportation;
    }

    private static Carrier parseCarrierData(String carrierData) {
        Carrier carrier = new Carrier();
        String[] carrierDataStrings = carrierData.split(SEPARATOR);
        carrier.setName(carrierDataStrings[0]);
        carrier.setAddress(carrierDataStrings[1]);
        try {
            CarrierType carrierType = CarrierType.valueOf(carrierDataStrings[2]);
            carrier.setCarrierType(carrierType);
        } catch (IllegalArgumentException e) {
            throw new InitException("Incorrect carrier type.");
        }
        return carrier;
    }

    private static Cargo parseCargoData(String cargoData) {
        Cargo cargo;
        CargoType cargoType;
        String[] cargoDataStrings = cargoData.split(SEPARATOR);

        try {
            cargoType = CargoType.valueOf(cargoDataStrings[2]);
        } catch (IllegalArgumentException e) {
            throw new InitException("Incorrect cargo type.");
        }

        switch (cargoType) {
            case FOOD:
                cargo = new FoodCargo();
                ((FoodCargo) cargo).setExpirationDate(parseDate(cargoDataStrings[3], "food cargo expiration date"));
                ((FoodCargo) cargo).setStoreTemperature(parseInt(cargoDataStrings[4], "temperature"));
                break;
            case CLOTHERS:
                cargo = new ClothersCargo();
                ((ClothersCargo) cargo).setSize(cargoDataStrings[3]);
                ((ClothersCargo) cargo).setMaterial(cargoDataStrings[4]);
                break;
            default:
                throw new InitException("Incorrect cargo type.");
        }

        cargo.setName(cargoDataStrings[0]);
        cargo.setWeight(parseInt(cargoDataStrings[1], "weight"));

        return cargo;
    }

    private static Date parseDate(String dateStr, String field) {
        Date date;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new InitException("Date: " + field + " is incorrect.");
        }
        return date;
    }

    private static int parseInt(String intStr, String field) {
        int i;
        try {
            i = Integer.valueOf(intStr);
        } catch (NumberFormatException e) {
            throw new InitException("Cargo " + field + " weight is not a number.");
        }
        return i;
    }

    public static List<Cargo> getCargoListFromFile() {
        return cargoList;
    }

    public static List<Carrier> getCarrierListFromFile() {
        return carrierList;
    }

    public static List<Transportation> getTransportationListFromFile() {
        return transportationList;
    }

}

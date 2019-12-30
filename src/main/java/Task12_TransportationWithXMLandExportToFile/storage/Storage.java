package Task12_TransportationWithXMLandExportToFile.storage;


import Task12_TransportationWithXMLandExportToFile.cargo.domain.Cargo;
import Task12_TransportationWithXMLandExportToFile.carrier.domain.Carrier;
import Task12_TransportationWithXMLandExportToFile.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int ARRAY_CAPACITY = 10;

    public static Cargo[] cargoArray = new Cargo[ARRAY_CAPACITY];
    public static int cargoIndex = 0;
    public static List<Cargo> cargoCollection = new ArrayList<>();

    public static Carrier[] carrierArray = new Carrier[ARRAY_CAPACITY];
    public static int carrierIndex = 0;
    public static List<Carrier> carrierCollection = new ArrayList<>();

    public static Transportation[] transportationArray = new Transportation[ARRAY_CAPACITY];
    public static int transportationIndex = 0;
    public static List<Transportation> transportationCollection = new ArrayList<>();
}

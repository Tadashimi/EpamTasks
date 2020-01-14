package task7_transportation_collections.storage;


import task7_transportation_collections.cargo.domain.Cargo;
import task7_transportation_collections.carrier.domain.Carrier;
import task7_transportation_collections.common.solutions.utils.ArrayUtils;
import task7_transportation_collections.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int ARRAY_CAPACITY = 10;

    public static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
    public static int cargoIndex = 0;

    public static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
    public static int carrierIndex = 0;

    public static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
    public static int transportationIndex = 0;

    public static List<Cargo> cargoList = new ArrayList<>();
    public static List<Carrier> carrierList = new ArrayList<>();
    public static List<Transportation> transportationList = new ArrayList<>();

    public static void printAllCargos() {
        ArrayUtils.printArray(cargos);
    }

    public static void printAllCarriers() {
        ArrayUtils.printArray(carriers);
    }

    public static void printAllTransportations() {
        ArrayUtils.printArray(transportations);
    }

}

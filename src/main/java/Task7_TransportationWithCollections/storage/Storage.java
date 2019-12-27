package main.java.Task7_TransportationWithCollections.storage;


import main.java.Task7_TransportationWithCollections.cargo.domain.Cargo;
import main.java.Task7_TransportationWithCollections.carrier.domain.Carrier;
import main.java.Task7_TransportationWithCollections.common.solutions.utils.ArrayUtils;
import main.java.Task7_TransportationWithCollections.transportation.domain.Transportation;

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

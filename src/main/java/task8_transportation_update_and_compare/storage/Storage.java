package  task8_transportation_update_and_compare.storage;


import  task8_transportation_update_and_compare.cargo.domain.Cargo;
import  task8_transportation_update_and_compare.carrier.domain.Carrier;
import  task8_transportation_update_and_compare.common.solutions.utils.ArrayUtils;
import  task8_transportation_update_and_compare.transportation.domain.Transportation;

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

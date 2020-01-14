package task6_transportation_repo_and_service.storage;

import task6_transportation_repo_and_service.cargo.domain.Cargo;
import task6_transportation_repo_and_service.carrier.domain.Carrier;
import task6_transportation_repo_and_service.common.utils.ArrayUtils;
import task6_transportation_repo_and_service.transportation.domain.Transportation;

public class Storage {

    private static final int ARRAY_CAPACITY = 10;

    public static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
    public static int cargoIndex = 0;

    public static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
    public static int carrierIndex = 0;

    public static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
    public static int transportationIndex = 0;

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

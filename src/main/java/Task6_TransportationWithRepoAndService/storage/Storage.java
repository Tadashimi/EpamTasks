package Task6_TransportationWithRepoAndService.storage;

import Task6_TransportationWithRepoAndService.cargo.domain.Cargo;
import Task6_TransportationWithRepoAndService.carrier.domain.Carrier;
import Task6_TransportationWithRepoAndService.common.utils.ArrayUtils;
import Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

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

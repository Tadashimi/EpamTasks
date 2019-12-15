package Task6_TransportationWithRepoAndService.storage;

import Task6_TransportationWithRepoAndService.cargo.domain.Cargo;
import Task6_TransportationWithRepoAndService.carrier.domain.Carrier;
import Task6_TransportationWithRepoAndService.common.utils.ArrayUtils;
import Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

import java.util.Objects;

public class Storage {

    private static final int ARRAY_CAPACITY = 10;

    private static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
    private static int cargoIndex = 0;

    private static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
    private static int carrierIndex = 0;

    private static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
    private static int transportationIndex = 0;

    public static void addCargo(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargos[cargoIndex] = cargo;
        cargoIndex++;

        if (cargoIndex == cargos.length) {
            Cargo[] newCargos = new Cargo[cargos.length * 2];
            ArrayUtils.copyArray(cargos, newCargos);
            cargos = newCargos;
        }
    }

    public static Cargo getCargoById(long id) {
        for (Cargo cargo : cargos) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }

        return null;
    }

    public static Cargo[] getCargosByName(String name) {
        Cargo[] result = new Cargo[cargos.length];

        int curIndex = 0;
        for (Cargo cargo : cargos) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                result[curIndex++] = cargo;
            }
        }

        return result;
    }

    public static void deleteCargoById(long id) {
        int curIndex = 0;
        while (curIndex < cargos.length) {
            Cargo cargo = cargos[curIndex];
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                ArrayUtils.shiftElementsLeftInArray(cargos, curIndex, 1);
                break;
            }
            curIndex++;
        }
    }

    public static void printAllCargos() {
        ArrayUtils.printArray(cargos);
    }

    public static void addCarrier(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carriers[carrierIndex] = carrier;
        carrierIndex++;

        if (carrierIndex == carriers.length) {
            Carrier[] newCarriers = new Carrier[carriers.length * 2];
            ArrayUtils.copyArray(carriers, newCarriers);
            carriers = newCarriers;
        }
    }

    public static Carrier getCarrierById(long id) {
        for (Carrier carrier : carriers) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }

        return null;
    }

    public static Carrier[] getCarriersByName(String name) {
        Carrier[] result = new Carrier[carriers.length];

        int curIndex = 0;
        for (Carrier carrier : carriers) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
            }
        }

        return result;
    }

    public static void deleteCarrierById(long id) {
        int curIndex = 0;
        while (curIndex < carriers.length) {
            Carrier carrier = carriers[curIndex];
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                ArrayUtils.shiftElementsLeftInArray(carriers, curIndex, 1);
                break;
            }
            curIndex++;
        }
    }

    public static void printAllCarriers() {
        ArrayUtils.printArray(carriers);
    }

    public static void addTransportation(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportations[transportationIndex] = transportation;
        transportationIndex++;

        if (transportationIndex == transportations.length) {
            Transportation[] newTransportations = new Transportation[transportations.length * 2];
            ArrayUtils.copyArray(transportations, newTransportations);
            transportations = newTransportations;
        }
    }

    public static Transportation getTransportationById(long id) {
        for (Transportation transportation : transportations) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    public static void deleteTransportationById(long id) {
        int curIndex = 0;
        while (curIndex < transportations.length) {
            Transportation transportation = transportations[curIndex];
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                ArrayUtils.shiftElementsLeftInArray(transportations, curIndex, 1);
                break;
            }
            curIndex++;
        }
    }

    public static void printAllTransportations() {
        ArrayUtils.printArray(transportations);
    }
}

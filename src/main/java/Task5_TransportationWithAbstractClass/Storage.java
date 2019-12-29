package Task5_TransportationWithAbstractClass;

import Task5_TransportationWithAbstractClass.cargo.domain.Cargo;
import Task5_TransportationWithAbstractClass.carrier.domain.Carrier;
import Task5_TransportationWithAbstractClass.common.domain.BaseDomain;
import Task5_TransportationWithAbstractClass.transportation.domain.Transportation;

import java.util.Objects;

import static Task5_TransportationWithAbstractClass.Constants.INITIAL_ARRAY_CAPACITY;

public class Storage {
    private static Cargo[] allCargoes = new Cargo[INITIAL_ARRAY_CAPACITY];
    private static int cargoIndex = 0;

    private static Carrier[] allCarriers = new Carrier[INITIAL_ARRAY_CAPACITY];
    private static int carrierIndex = 0;

    private static Transportation[] allTransportations = new Transportation[INITIAL_ARRAY_CAPACITY];
    private static int transportationIndex = 0;


    public static void addCargo(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        int currentArrayLength = allCargoes.length;
        if (cargoIndex >= currentArrayLength) {
            increaseCargoesArray(currentArrayLength);
        }
        allCargoes[cargoIndex] = cargo;
        cargoIndex++;
    }

    private static void increaseCargoesArray(int currentArrayLength) {
        Cargo[] tempArray = new Cargo[currentArrayLength * 2];
        System.arraycopy(allCargoes, 0, tempArray, 0, currentArrayLength);
        allCargoes = tempArray;
    }

    public static void addCarrier(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        int currentArrayLength = allCarriers.length;
        if (carrierIndex < currentArrayLength) {
            increaseCarriersArray(currentArrayLength);
        }
        allCarriers[carrierIndex] = carrier;
        carrierIndex++;
    }

    private static void increaseCarriersArray(int currentArrayLength) {
        Carrier[] tempArray = new Carrier[currentArrayLength * 2];
        System.arraycopy(allCarriers, 0, tempArray, 0, currentArrayLength);
        allCarriers = tempArray;
    }

    public static void addTransportation(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        int currentArrayLength = allTransportations.length;
        if (transportationIndex >= currentArrayLength) {
            increaseTransportationsArray(currentArrayLength);
        }
        allTransportations[transportationIndex] = transportation;
        transportationIndex++;
    }

    private static void increaseTransportationsArray(int currentArrayLength) {
        Transportation[] tempArray = new Transportation[currentArrayLength * 2];
        System.arraycopy(allTransportations, 0, tempArray, 0, currentArrayLength);
        allTransportations = tempArray;
    }

    public static void printAllCargoes() {
        System.out.println("Cargoes list:");
        for (Cargo cargo : allCargoes) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }

    public static void printAllCarriers() {
        System.out.println("Carriers list:");
        for (Carrier carrier : allCarriers) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }

    public static void printAllTransportations() {
        System.out.println("Transportations list:");
        for (Transportation transportation : allTransportations) {
            if (transportation != null) {
                System.out.println(transportation);
            }
        }
    }

    public static Cargo getCargoById(Long cargoId) {
        for (Cargo cargo : allCargoes) {
            if ((cargo != null) && (cargo.getId().equals(cargoId))) {
                return cargo;
            }
        }
        return null;
    }

    public static Cargo[] getCargoByName(String cargoName) {
        Cargo[] result = new Cargo[cargoIndex];
        int index = 0;
        for (Cargo cargo : allCargoes) {
            if ((cargo != null) && (Objects.equals(cargo.getName(), cargoName))) {
                result[index] = cargo;
                index++;
            }
        }
        return result;
    }

    public static Carrier getCrrierById(Long carrierId) {
        for (Carrier carrier : allCarriers) {
            if ((carrier != null) && (carrier.getId().equals(carrierId))) {
                return carrier;
            }
        }
        return null;
    }

    public static Carrier[] getCarrierByName(String carrierName) {
        Carrier[] result = new Carrier[carrierIndex];
        int index = 0;
        for (Carrier carrier : allCarriers) {
            if ((carrier != null) && (Objects.equals(carrier.getName(), carrierName))) {
                result[index] = carrier;
                index++;
            }
        }
        return result;
    }

    public static Transportation getTransportationById(Long transportationId) {
        for (Transportation transportation : allTransportations) {
            if ((transportation != null) && (transportation.getId().equals(transportationId))) {
                return transportation;
            }
        }
        return null;
    }

    public static BaseDomain[] getAll() {
        BaseDomain[] result = new BaseDomain[cargoIndex + carrierIndex + transportationIndex];
        System.arraycopy(allCargoes, 0, result, 0, cargoIndex);
        System.arraycopy(allCarriers, 0, result, cargoIndex, carrierIndex);
        System.arraycopy(allTransportations, 0, result, cargoIndex + carrierIndex, transportationIndex);
        return result;
    }
}

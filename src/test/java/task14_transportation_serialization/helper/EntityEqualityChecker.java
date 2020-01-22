package task14_transportation_serialization.helper;

import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.cargo.domain.ClothersCargo;
import task14_transportation_serialization.cargo.domain.FoodCargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.transportation.domain.Transportation;

import java.util.List;
import java.util.Objects;

public class EntityEqualityChecker {

    public static boolean cargoesContainEqualData(Cargo cargo1, Cargo cargo2) {
        if (cargo1 == null && cargo2 == null) {
            return true;
        } else if (cargo1 == null || cargo2 == null) {
            return false;
        } else if (!Objects.equals(cargo1.getCargoType(), cargo2.getCargoType())) {
            return false;
        } else if (!commonCargoFieldsAreEquals(cargo1, cargo2)) {
            return false;
        } else if (!cargoTypeSpecificFieldsAreEquals(cargo1, cargo2)) {
            return false;
        } else {
            return transportationsListsAreEquals(cargo1.getTransportations(), cargo2.getTransportations());
        }
    }

    private static boolean commonCargoFieldsAreEquals(Cargo cargo1, Cargo cargo2) {
        return Objects.equals(cargo1.getId(), cargo2.getId()) &&
                Objects.equals(cargo1.getName(), cargo2.getName()) &&
                Objects.equals(cargo1.getWeight(), cargo2.getWeight());
    }

    private static boolean transportationsListsAreEquals(List<Transportation> transportation1, List<Transportation> transportation2) {

        if (transportation1 == null && transportation2 == null) {
            return true;
        } else if (transportation1 == null || transportation2 == null) {
            return false;
        } else if (transportation1.size() != transportation2.size()) {
            return false;
        } else {
            boolean result = true;
            int i = 0;
            while (i < transportation1.size() && result) {
                result = transportationsDetailsAreEquals(transportation1.get(i), transportation2.get(i));
                i++;
            }
            return result;
        }
    }

    private static boolean cargoTypeSpecificFieldsAreEquals(Cargo cargo1, Cargo cargo2) {
        if (cargo1.getCargoType() == null) {
            return false;
        }
        switch (cargo1.getCargoType()) {
            case CLOTHERS:
                boolean materialsAreEquals = Objects.equals(((ClothersCargo) cargo1).getMaterial(),
                        ((ClothersCargo) cargo2).getMaterial());
                boolean sizesAreEquals = Objects.equals(((ClothersCargo) cargo1).getSize(),
                        ((ClothersCargo) cargo2).getSize());
                return materialsAreEquals && sizesAreEquals;
            case FOOD:

                boolean datesAreEquals = Objects.equals(((FoodCargo) cargo1).getExpirationDate(),
                        ((FoodCargo) cargo2).getExpirationDate());
                boolean temperaturesAreEquals = Objects.equals(((FoodCargo) cargo1).getStoreTemperature(),
                        ((FoodCargo) cargo2).getStoreTemperature());
                return datesAreEquals && temperaturesAreEquals;
            default:
                return false;
        }
    }

    private static boolean transportationsDetailsAreEquals(Transportation transportation1, Transportation transportation2) {
        if (transportation1 == null && transportation2 == null) {
            return true;
        } else if (transportation1 == null || transportation2 == null) {
            return false;
        } else {
            boolean descriptionsAreEquals = Objects.equals(transportation1.getDescription(),
                    transportation2.getDescription());
            boolean billsToAreEquals = Objects.equals(transportation1.getBillTo(), transportation2.getBillTo());
            boolean datesAreEquals = Objects.equals(transportation1.getTransportationBeginDate(),
                    transportation2.getTransportationBeginDate());
            return descriptionsAreEquals && billsToAreEquals && datesAreEquals;
        }
    }

    public static boolean carriersContainEqualData(Carrier carrier1, Carrier carrier2) {
        if (carrier1 == null && carrier2 == null) {
            return true;
        } else if (carrier1 == null || carrier2 == null) {
            return false;
        } else if (!carrierFieldsAreEquals(carrier1, carrier2)) {
            return false;
        } else {
            return transportationsListsAreEquals(carrier1.getTransportations(), carrier2.getTransportations());
        }
    }

    private static boolean carrierFieldsAreEquals(Carrier carrier1, Carrier carrier2) {
        return Objects.equals(carrier1.getId(), carrier2.getId()) &&
                Objects.equals(carrier1.getName(), carrier2.getName()) &&
                Objects.equals(carrier1.getCarrierType(), carrier2.getCarrierType()) &&
                Objects.equals(carrier1.getAddress(), carrier2.getAddress());

    }

    public static boolean transportationsContainEqualData(Transportation transportation1, Transportation transportation2) {
        if (transportation1 == null && transportation2 == null) {
            return true;
        } else if (transportation1 == null || transportation2 == null) {
            return false;
        } else if (!cargoesAreEquals(transportation1.getCargo(), transportation2.getCargo())) {
            return false;
        } else {
            return carriersAreEquals(transportation1.getCarrier(), transportation2.getCarrier());
        }
    }

    private static boolean cargoesAreEquals(Cargo cargo1, Cargo cargo2) {
        if (cargo1 == null && cargo2 == null) {
            return true;
        } else if (cargo1 == null || cargo2 == null) {
            return false;
        } else {
            return Objects.equals(cargo1.getId(), cargo2.getId());
        }
    }

    private static boolean carriersAreEquals(Carrier carrier1, Carrier carrier2) {
        if (carrier1 == null && carrier2 == null) {
            return true;
        } else if (carrier1 == null || carrier2 == null) {
            return false;
        } else {
            return Objects.equals(carrier1.getId(), carrier2.getId());
        }
    }

}

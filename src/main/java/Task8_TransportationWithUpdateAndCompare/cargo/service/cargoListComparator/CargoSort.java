package Task8_TransportationWithUpdateAndCompare.cargo.service.cargoListComparator;

import Task8_TransportationWithUpdateAndCompare.cargo.domain.Cargo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class CargoSort {
    public static void sortCargoList(List<Cargo> cargoList, CompareField compareField) {
        switch (compareField) {
            case NAME:
                Collections.sort(cargoList, new Comparator<Cargo>() {
                    @Override
                    public int compare(Cargo o1, Cargo o2) {
                        String o1Name = o1.getName();
                        String o2Name = o2.getName();
                        if (o1Name != null && o2Name != null) {
                            return o1Name.compareTo(o2Name);
                        }
                        return o1Name == null ? 1 : -1;
                    }
                });
                break;
            case WEIGHT:
                Collections.sort(cargoList, new Comparator<Cargo>() {
                    @Override
                    public int compare(Cargo o1, Cargo o2) {
                        return Integer.compare(o1.getWeight(), o2.getWeight());
                    }
                });
                break;
            default:
                Collections.sort(cargoList, new Comparator<Cargo>() {
                    @Override
                    public int compare(Cargo o1, Cargo o2) {
                        String o1Name = o1.getName();
                        String o2Name = o2.getName();
                        if (o1Name != null && o2Name != null) {
                            if (o1Name.equals(o2Name)) {
                                return Integer.compare(o1.getWeight(), o2.getWeight());
                            }
                            return o1Name.compareTo(o2Name);
                        } else {
                            return o1Name == null ? -1 : 1;
                        }
                    }
                });

        }
    }
}

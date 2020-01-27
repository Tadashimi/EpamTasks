package task16_transportation_lambda.cargo.repo.impl;


import task16_transportation_lambda.cargo.domain.Cargo;
import task16_transportation_lambda.cargo.domain.CargoField;
import task16_transportation_lambda.cargo.repo.CargoRepo;
import task16_transportation_lambda.cargo.search.CargoSearchCondition;
import task16_transportation_lambda.common.solutions.comparator.SimpleComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static task16_transportation_lambda.cargo.domain.CargoField.NAME;
import static task16_transportation_lambda.cargo.domain.CargoField.WEIGHT;

public abstract class CommonCargoRepo implements CargoRepo {

    private static final List<CargoField> FIELDS_ORDER_TO_SORT_CARGOS = Arrays.asList(NAME, WEIGHT);

    private static final Comparator<Cargo> CARGO_NAME_COMPARATOR = (o1, o2) -> SimpleComparator.STRING_COMPARATOR.compare(o1.getName(), o2.getName());

    private static final Comparator<Cargo> CARGO_WEIGHT_COMPARATOR = Comparator.comparingInt(Cargo::getWeight);

    protected Comparator<Cargo> createCargoComparator(CargoSearchCondition searchCondition) {
        Comparator<Cargo> result = null;

        //NAME, WEIGHT
        for (CargoField cargoField : FIELDS_ORDER_TO_SORT_CARGOS) {
            if (searchCondition.shouldSortByField().test(cargoField)) {

                if (result == null) {
                    result = getComparatorForCargoField(cargoField);
                } else {
                    result = result.thenComparing(getComparatorForCargoField(cargoField));
                }
            }
        }

        return result;
    }

    private Comparator<Cargo> getComparatorForCargoField(CargoField cargoField) {
        switch (cargoField) {

            case NAME: {
                return CARGO_NAME_COMPARATOR;
            }
            case WEIGHT: {
                return CARGO_WEIGHT_COMPARATOR;
            }
        }

        return null;
    }
}

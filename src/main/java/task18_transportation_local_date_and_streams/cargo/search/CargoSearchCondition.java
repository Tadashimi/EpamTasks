package task18_transportation_local_date_and_streams.cargo.search;

import task18_transportation_local_date_and_streams.cargo.domain.CargoField;
import task18_transportation_local_date_and_streams.common.solutions.search.OrderType;

import java.util.Set;
import java.util.function.Predicate;

public class CargoSearchCondition {

    private OrderType orderType;
    private Set<CargoField> sortFields;

    public boolean needSorting() {
        return sortFields != null && sortFields.size() > 0;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(
            OrderType orderType) {
        this.orderType = orderType;
    }

    public Set<CargoField> getSortFields() {
        return sortFields;
    }

    public void setSortFields(
            Set<CargoField> sortFields) {
        this.sortFields = sortFields;
    }

    public Predicate<CargoField> shouldSortByField() {
        return cargoField -> sortFields != null && sortFields.contains(cargoField);
    }

    public boolean isAscOrdering() {
        return orderType == null || OrderType.ASC.equals(orderType);
    }
}

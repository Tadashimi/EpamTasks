package task20_transportation_data_base.cargo.domain;

import java.sql.ResultSet;

public class CargoMapper {
    public static Cargo mapCargo(ResultSet resultSet) throws Exception {
        Cargo cargo = null;
        CargoType cargoType = CargoType.valueOf(resultSet.getString("CARGO_TYPE"));
        switch (cargoType) {
            case FOOD:
                cargo = new FoodCargo();
                ((FoodCargo) cargo).setStoreTemperature(resultSet.getInt("STORED_TEMPERATURE"));
                ((FoodCargo) cargo).setExpirationDate(resultSet.getDate("EXPIRATION_DATE").toLocalDate());
                break;
            case CLOTHERS:
                cargo = new ClothersCargo();
                ((ClothersCargo) cargo).setSize(resultSet.getString("SIZE"));
                ((ClothersCargo) cargo).setMaterial(resultSet.getString("MATERIAL"));
                break;
        }
        cargo.setId(resultSet.getLong("ID"));
        cargo.setName(resultSet.getString("NAME"));
        cargo.setWeight(resultSet.getInt("WEIGHT"));
        return cargo;
    }
}

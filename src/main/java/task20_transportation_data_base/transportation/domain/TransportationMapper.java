package task20_transportation_data_base.transportation.domain;

import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.domain.CargoMapper;
import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.domain.CarrierMapper;

import java.sql.ResultSet;

public class TransportationMapper {

    public static Transportation mapTransportation(ResultSet resultSet) throws Exception {
        Transportation transportation = new Transportation();
        transportation.setId(resultSet.getLong("ID"));
        transportation.setDescription(resultSet.getString("DESCRIPTION"));
        transportation.setBillTo(resultSet.getString("BILL_TO"));
        transportation.setTransportationBeginDate(resultSet.getDate("BEGIN_DATE").toLocalDate());

        Cargo cargo = CargoMapper.mapCargo(resultSet);
        transportation.setCargo(cargo);

        Carrier carrier = CarrierMapper.mapCarrier(resultSet);
        transportation.setCarrier(carrier);

        return transportation;
    }
}

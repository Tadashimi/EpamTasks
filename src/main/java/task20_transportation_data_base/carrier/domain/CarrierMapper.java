package task20_transportation_data_base.carrier.domain;

import java.sql.ResultSet;

public class CarrierMapper{

        public static Carrier mapCarrier(ResultSet resultSet) throws Exception {
            Carrier carrier = new Carrier();
            carrier.setId(resultSet.getLong("ID"));
            carrier.setName(resultSet.getString("NAME"));
            carrier.setCarrierType(CarrierType.valueOf(resultSet.getString("CARRIER_TYPE")));
            carrier.setAddress(resultSet.getString("ADDRESS"));
            return carrier;
        }
}

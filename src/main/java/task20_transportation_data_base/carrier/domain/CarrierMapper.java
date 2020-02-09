package task20_transportation_data_base.carrier.domain;

import java.sql.ResultSet;

import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.CARRIER_TABLE_NAME;

public class CarrierMapper {

    public static Carrier mapCarrier(ResultSet resultSet) throws Exception {
        Carrier carrier = new Carrier();
        carrier.setId(resultSet.getLong(CARRIER_TABLE_NAME+".ID"));
        carrier.setName(resultSet.getString(CARRIER_TABLE_NAME+".NAME"));
        carrier.setCarrierType(CarrierType.valueOf(resultSet.getString("CARRIER_TYPE")));
        carrier.setAddress(resultSet.getString("ADDRESS"));
        return carrier;
    }
}

package task14_transportation_serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.transportation.domain.Transportation;

import static task14_transportation_serialization.helper.EntityGenerator.*;
import static task14_transportation_serialization.helper.SerializationHelper.getByteArrayFromObject;
import static task14_transportation_serialization.helper.SerializationHelper.getObjectFromByteArray;

public class EntitySerializationTest {

    @Test
    public void foodCargoSerializationTest() {
        Cargo expectedCargo = getFoodCargo();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargo);
        Cargo actualCargo = (Cargo) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertEquals(expectedCargo, actualCargo);
    }

    @Test
    public void clothersCargoSerializationTest() {
        Cargo expectedCargo = getClothersCargo();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargo);
        Cargo actualCargo = (Cargo) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertEquals(expectedCargo, actualCargo);
    }

    @Test
    public void carrierSerializationTest() {
        Carrier expectedCarrier = getCarrier();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarrier);
        Carrier actualCarrier = (Carrier) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertEquals(expectedCarrier, actualCarrier);
    }

    @Test
    public void transportationSerializationTest() {
        Cargo cargo = getFoodCargo();
        Carrier carrier = getCarrier();
        Transportation expectedTransportation = getTransportation(cargo, carrier);

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportation);
        Transportation actualTransportation = (Transportation) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertEquals(expectedTransportation, actualTransportation);
    }
}

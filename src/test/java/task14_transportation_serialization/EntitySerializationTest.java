package task14_transportation_serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;

import static task14_transportation_serialization.helper.EntityGenerator.*;
import static task14_transportation_serialization.helper.SerializationHelper.getByteArrayFromObject;
import static task14_transportation_serialization.helper.SerializationHelper.getObjectFromByteArray;

public class EntitySerializationTest {
    private Cargo cargo;
    private Carrier carrier;
    private Transportation transportation;



    @Test
    public void carrierSerializationTest() {
        carrier = getCarrier();
        transportation = getTransportation(null, carrier);
        carrier.setTransportations(Arrays.asList(transportation));

        byte[] byteArrayForObject = getByteArrayFromObject(carrier);
        Carrier actualCarrier = (Carrier) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertEquals(carrier, actualCarrier);
    }

    @Test
    public void transportationSerializationTest() {
        cargo = getFoodCargo();
        carrier = getCarrier();
        transportation = getTransportation(cargo, carrier);

        byte[] byteArrayForObject = getByteArrayFromObject(transportation);
        Transportation actualTransportation = (Transportation) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertEquals(transportation, actualTransportation);
    }
}

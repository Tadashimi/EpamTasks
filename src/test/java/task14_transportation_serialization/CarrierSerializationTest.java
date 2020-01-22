package task14_transportation_serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task14_transportation_serialization.carrier.domain.Carrier;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static task14_transportation_serialization.helper.EntityEqualityChecker.carriersContainEqualData;
import static task14_transportation_serialization.helper.EntityGenerator.getCarrier;
import static task14_transportation_serialization.helper.SerializationHelper.getByteArrayFromObject;
import static task14_transportation_serialization.helper.SerializationHelper.getObjectFromByteArray;

public class CarrierSerializationTest {
    @Test
    public void foodCarrierWithoutTransportationSerializationTest() {
        Carrier expectedCarrier = getCarrier();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarrier);
        Carrier actualCarrier = (Carrier) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(carriersContainEqualData(expectedCarrier, actualCarrier));
    }

    @Test
    public void clothersCarrierWithoutTransportationSerializationTest() {
        Carrier expectedCarrier = getCarrier();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarrier);
        Carrier actualCarrier = (Carrier) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(carriersContainEqualData(expectedCarrier, actualCarrier));
    }

    @Test
    public void nullCarrierSerializationTest() {
        Carrier expectedCarrier = null;

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarrier);
        Carrier actualCarrier = (Carrier) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(carriersContainEqualData(expectedCarrier, actualCarrier));
    }

    @Test
    public void cargoesListSerializationTest() {
        List<Carrier> expectedCarriersList = Arrays.asList(getCarrier(), getCarrier());

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarriersList);
        List<Carrier> actualCarriersList = (List<Carrier>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNotNull(actualCarriersList);
        Assertions.assertEquals(expectedCarriersList.size(), actualCarriersList.size());

        Iterator<Carrier> iterator = actualCarriersList.iterator();
        for (Carrier expectedCarrier : expectedCarriersList) {
            Assertions.assertTrue(carriersContainEqualData(expectedCarrier, iterator.next()));
        }
    }

    @Test
    public void cargoesNullListSerializationTest() {
        List<Carrier> expectedCarriersList = null;

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarriersList);
        List<Carrier> actualCarriersList = (List<Carrier>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNull(actualCarriersList);
    }

    @Test
    public void cargoesListWithNullsSerializationTest() {
        List<Carrier> expectedCarrieresList = Arrays.asList(null, null);

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCarrieresList);
        List<Carrier> actualCarriersList = (List<Carrier>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNotNull(actualCarriersList);
        Assertions.assertEquals(expectedCarrieresList.size(), actualCarriersList.size());

        Iterator<Carrier> iterator = actualCarriersList.iterator();
        for (Carrier expectedCarrier : expectedCarrieresList) {
            Assertions.assertTrue(carriersContainEqualData(expectedCarrier, iterator.next()));
        }
    }
}

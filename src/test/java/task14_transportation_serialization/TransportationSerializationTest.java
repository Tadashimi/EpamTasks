package task14_transportation_serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task14_transportation_serialization.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static task14_transportation_serialization.helper.EntityEqualityChecker.transportationsContainEqualData;
import static task14_transportation_serialization.helper.EntityGenerator.getTransportation;
import static task14_transportation_serialization.helper.SerializationHelper.getByteArrayFromObject;
import static task14_transportation_serialization.helper.SerializationHelper.getObjectFromByteArray;

public class TransportationSerializationTest {
    @Test
    public void foodTransportationWithoutTransportationSerializationTest() {
        Transportation expectedTransportation = getTransportation();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportation);
        Transportation actualTransportation = (Transportation) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(transportationsContainEqualData(expectedTransportation, actualTransportation));
    }

    @Test
    public void clothersTransportationWithoutTransportationSerializationTest() {
        Transportation expectedTransportation = getTransportation();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportation);
        Transportation actualTransportation = (Transportation) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(transportationsContainEqualData(expectedTransportation, actualTransportation));
    }

    @Test
    public void nullTransportationSerializationTest() {
        Transportation expectedTransportation = null;

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportation);
        Transportation actualTransportation = (Transportation) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(transportationsContainEqualData(expectedTransportation, actualTransportation));
    }

    @Test
    public void cargoesListSerializationTest() {
        List<Transportation> expectedTransportationsList = Arrays.asList(getTransportation(), getTransportation());

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportationsList);
        List<Transportation> actualTransportationsList = (List<Transportation>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNotNull(actualTransportationsList);
        Assertions.assertEquals(expectedTransportationsList.size(), actualTransportationsList.size());

        Iterator<Transportation> iterator = actualTransportationsList.iterator();
        for (Transportation expectedTransportation : expectedTransportationsList) {
            Assertions.assertTrue(transportationsContainEqualData(expectedTransportation, iterator.next()));
        }
    }

    @Test
    public void cargoesNullListSerializationTest() {
        List<Transportation> expectedTransportationsList = null;

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportationsList);
        List<Transportation> actualTransportationsList = (List<Transportation>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNull(actualTransportationsList);
    }

    @Test
    public void cargoesListWithNullsSerializationTest() {
        List<Transportation> expectedTransportationesList = Arrays.asList(null, null);

        byte[] byteArrayForObject = getByteArrayFromObject(expectedTransportationesList);
        List<Transportation> actualTransportationsList = (List<Transportation>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNotNull(actualTransportationsList);
        Assertions.assertEquals(expectedTransportationesList.size(), actualTransportationsList.size());

        Iterator<Transportation> iterator = actualTransportationsList.iterator();
        for (Transportation expectedTransportation : expectedTransportationesList) {
            Assertions.assertTrue(transportationsContainEqualData(expectedTransportation, iterator.next()));
        }
    }
}

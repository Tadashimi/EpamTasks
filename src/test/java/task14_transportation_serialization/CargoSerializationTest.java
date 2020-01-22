package task14_transportation_serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task14_transportation_serialization.cargo.domain.Cargo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static task14_transportation_serialization.helper.EntityEqualityChecker.cargoesContainEqualData;
import static task14_transportation_serialization.helper.EntityGenerator.getClothersCargo;
import static task14_transportation_serialization.helper.EntityGenerator.getFoodCargo;
import static task14_transportation_serialization.helper.SerializationHelper.getByteArrayFromObject;
import static task14_transportation_serialization.helper.SerializationHelper.getObjectFromByteArray;

public class CargoSerializationTest {

    @Test
    public void foodCargoWithoutTransportationSerializationTest() {
        Cargo expectedCargo = getFoodCargo();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargo);
        Cargo actualCargo = (Cargo) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(cargoesContainEqualData(expectedCargo, actualCargo));
    }

    @Test
    public void clothersCargoWithoutTransportationSerializationTest() {
        Cargo expectedCargo = getClothersCargo();

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargo);
        Cargo actualCargo = (Cargo) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(cargoesContainEqualData(expectedCargo, actualCargo));
    }

    @Test
    public void nullCargoSerializationTest() {
        Cargo expectedCargo = null;

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargo);
        Cargo actualCargo = (Cargo) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertTrue(cargoesContainEqualData(expectedCargo, actualCargo));
    }

    @Test
    public void cargoesListSerializationTest() {
        List<Cargo> expectedCargoesList = Arrays.asList(getFoodCargo(), getClothersCargo());

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargoesList);
        List<Cargo> actualCargoesList = (List<Cargo>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNotNull(actualCargoesList);
        Assertions.assertEquals(expectedCargoesList.size(), actualCargoesList.size());

        Iterator<Cargo> iterator = actualCargoesList.iterator();
        for (Cargo expectedCargo : expectedCargoesList) {
            Assertions.assertTrue(cargoesContainEqualData(expectedCargo, iterator.next()));
        }
    }

    @Test
    public void cargoesNullListSerializationTest() {
        List<Cargo> expectedCargoesList = null;

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargoesList);
        List<Cargo> actualCargoesList = (List<Cargo>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNull(actualCargoesList);
    }

    @Test
    public void cargoesListWithNullsSerializationTest() {
        List<Cargo> expectedCargoesList = Arrays.asList(null, null);

        byte[] byteArrayForObject = getByteArrayFromObject(expectedCargoesList);
        List<Cargo> actualCargoesList = (List<Cargo>) getObjectFromByteArray(byteArrayForObject);

        Assertions.assertNotNull(actualCargoesList);
        Assertions.assertEquals(expectedCargoesList.size(), actualCargoesList.size());

        Iterator<Cargo> iterator = actualCargoesList.iterator();
        for (Cargo expectedCargo : expectedCargoesList) {
            Assertions.assertTrue(cargoesContainEqualData(expectedCargo, iterator.next()));
        }
    }
}

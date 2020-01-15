package task14_transportation_serialization;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task14_transportation_serialization.application.serviceholder.StorageType;
import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.common.business.exception.checked.InitStorageException;
import task14_transportation_serialization.common.business.exception.checked.SerializationException;
import task14_transportation_serialization.storage.Storage;
import task14_transportation_serialization.transportation.domain.Transportation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static task14_transportation_serialization.helper.StorageHelper.*;

public class StorageTest {

    private Cargo[] cargoArray;
    private List<Cargo> cargoCollection;

    private Carrier[] carrierArray;
    private List<Carrier> carrierCollection;

    private Transportation[] transportationArray;
    private List<Transportation> transportationCollection;

    private Path file;

    @BeforeEach
    public void createTempFile() throws IOException {
        file = Files.createTempFile("task14_", ".txt");
    }

    @AfterEach
    public void removeTempFile() throws IOException {
        Files.deleteIfExists(file);
    }

    @Test
    public void storageBasedOnCollectionTest() throws InitStorageException, SerializationException {
        initStorage(StorageType.COLLECTION);
        fillExpectedData();

        serializeStorage(file);
        removeDataFromStorage();
        deserializeStorage(file);

        compareExpectedAndActualData();
    }

    @Test
    public void storageBasedOnArrayTest() throws InitStorageException, SerializationException {
        initStorage(StorageType.ARRAY);
        fillExpectedData();

        serializeStorage(file);
        removeDataFromStorage();
        deserializeStorage(file);

        compareExpectedAndActualData();
    }

    private void compareExpectedAndActualData() {
        Assertions.assertArrayEquals(cargoArray, Storage.cargoArray);
        Assertions.assertArrayEquals(cargoCollection.toArray(), Storage.cargoCollection.toArray());

        Assertions.assertArrayEquals(carrierArray, Storage.carrierArray);
        Assertions.assertArrayEquals(carrierCollection.toArray(), Storage.carrierCollection.toArray());

        Assertions.assertArrayEquals(transportationArray, Storage.transportationArray);
        Assertions.assertArrayEquals(transportationCollection.toArray(), Storage.transportationCollection.toArray());
    }

    private void fillExpectedData() {
        cargoArray = Storage.cargoArray;
        cargoCollection = Storage.cargoCollection;

        carrierArray = Storage.carrierArray;
        carrierCollection = Storage.carrierCollection;

        transportationArray = Storage.transportationArray;
        transportationCollection = Storage.transportationCollection;
    }
}

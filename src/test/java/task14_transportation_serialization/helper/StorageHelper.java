package task14_transportation_serialization.helper;

import task14_transportation_serialization.application.serviceholder.ServiceHolder;
import task14_transportation_serialization.application.serviceholder.StorageType;
import task14_transportation_serialization.common.business.exception.checked.InitStorageException;
import task14_transportation_serialization.common.business.exception.checked.SerializationException;
import task14_transportation_serialization.storage.Storage;
import task14_transportation_serialization.storage.initor.InitStorageType;
import task14_transportation_serialization.storage.initor.StorageInitor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

import static task14_transportation_serialization.storage.initor.StorageInitorFactory.getStorageInitor;

public class StorageHelper {

    public static void initStorage(StorageType type) throws InitStorageException {
        ServiceHolder.initServiceHolder(type);
        StorageInitor storageInitor = getStorageInitor(InitStorageType.MEMORY);
        storageInitor.initStorage();
    }

    public static void writeStorageToFile(Path file) throws SerializationException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            Storage.serializeStatic(oos);
        } catch (Exception e) {
            throw new SerializationException("Error while storage serialization");
        }
    }

    public static void removeDataFromStorage() {
        Storage.cargoCollection = null;
        Storage.carrierCollection = null;
        Storage.transportationCollection = null;

        Storage.cargoArray = null;
        Storage.carrierArray = null;
        Storage.transportationArray = null;
    }

    public static void readStorageFromFile(Path file) throws SerializationException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            Storage.deserializeStatic(ois);
        } catch (Exception e) {
            throw new SerializationException("Error while storage deserialization");
        }
    }

}

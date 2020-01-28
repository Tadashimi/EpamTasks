package task17_transportation_optional.storage;


import task17_transportation_optional.cargo.domain.Cargo;
import task17_transportation_optional.carrier.domain.Carrier;
import task17_transportation_optional.transportation.domain.Transportation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {
    private static final long serialVersionUID = 100L;

    transient private static final int ARRAY_CAPACITY = 10;

    public static Cargo[] cargoArray = new Cargo[ARRAY_CAPACITY];
    public static int cargoIndex = 0;
    public static List<Cargo> cargoCollection = new ArrayList<>();

    public static Carrier[] carrierArray = new Carrier[ARRAY_CAPACITY];
    public static int carrierIndex = 0;
    public static List<Carrier> carrierCollection = new ArrayList<>();

    public static Transportation[] transportationArray = new Transportation[ARRAY_CAPACITY];
    public static int transportationIndex = 0;
    public static List<Transportation> transportationCollection = new ArrayList<>();

    public static void serializeStatic(ObjectOutputStream objectOutputStream) throws IOException {
        serializeCargoes(objectOutputStream);
        serializeCarriers(objectOutputStream);
        serializeTransportations(objectOutputStream);
    }

    public static void deserializeStatic(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        deserializeCargoes(objectInputStream);
        deserializeCarriers(objectInputStream);
        deserializeTransportations(objectInputStream);
    }

    private static void serializeCargoes(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(cargoArray);
        objectOutputStream.writeObject(cargoCollection);
    }

    private static void serializeCarriers(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(carrierArray);
        objectOutputStream.writeObject(carrierCollection);
    }

    private static void serializeTransportations(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(transportationArray);
        objectOutputStream.writeObject(transportationCollection);
    }

    private static void deserializeCargoes(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        cargoArray = (Cargo[]) objectInputStream.readObject();
        cargoCollection = (List<Cargo>) objectInputStream.readObject();
    }

    private static void deserializeCarriers(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        carrierArray = (Carrier[]) objectInputStream.readObject();
        carrierCollection = (List<Carrier>) objectInputStream.readObject();
    }

    private static void deserializeTransportations(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        transportationArray = (Transportation[]) objectInputStream.readObject();
        transportationCollection = (List<Transportation>) objectInputStream.readObject();
    }
}

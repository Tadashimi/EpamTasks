package main.java.Task4_TransportationWithData;

public class Storage {
    private Cargo[] allCargoes;
    private Transportation[] allTransportations;
    private Carrier[] allCarriers;
    private int cargoesCount = 0;
    private int transportationsCount = 0;
    private int carriersCount = 0;

    public Storage(int initialArraysLength) {
        allCargoes = new Cargo[initialArraysLength];
        allTransportations = new Transportation[initialArraysLength];
        allCarriers = new Carrier[initialArraysLength];
    }

    public Storage() {
        this(10);
    }

    public void addCargo(Cargo cargo) {
        int currentArrayLength = allCargoes.length;
        if (cargoesCount >= currentArrayLength) {
            increaseCargoesArray(currentArrayLength);
        }
        allCargoes[cargoesCount] = cargo;
        cargoesCount++;
    }

    private void increaseCargoesArray(int currentArrayLength) {
        Cargo[] tempArray = new Cargo[currentArrayLength * 2];
        System.arraycopy(allCargoes, 0, tempArray, 0, currentArrayLength);
        allCargoes = tempArray;
    }

    public void addTransportation(Transportation transportation) {
        int currentArrayLength = allTransportations.length;
        if (transportationsCount >= currentArrayLength) {
            increaseTransportationsArray(currentArrayLength);
        }
        allTransportations[transportationsCount] = transportation;
        transportationsCount++;
    }

    private void increaseTransportationsArray(int currentArrayLength) {
        Transportation[] tempArray = new Transportation[currentArrayLength * 2];
        System.arraycopy(allTransportations, 0, tempArray, 0, currentArrayLength);
        allTransportations = tempArray;
    }

    public void addCarrier(Carrier carrier) {
        int currentArrayLength = allCarriers.length;
        if (carriersCount < currentArrayLength) {
            increaseCarriersArray(currentArrayLength);
        }
        allCarriers[carriersCount] = carrier;
        carriersCount++;
    }

    private void increaseCarriersArray(int currentArrayLength) {
        Carrier[] tempArray = new Carrier[currentArrayLength * 2];
        System.arraycopy(allCarriers, 0, tempArray, 0, currentArrayLength);
        allCarriers = tempArray;
    }

    public void printAllCargoes() {
        System.out.println("Cargoes list:");
        for (int i = 0; i< cargoesCount; i++) {
            System.out.println(allCargoes[i]);
        }
    }

    public void printAllTransportations() {
        System.out.println("Transportations list:");
        for (int i = 0; i< transportationsCount; i++) {
            System.out.println(allTransportations[i]);
        }
    }

    public void printAllCarriers() {
        System.out.println("Carriers list:");
        for (int i = 0; i< carriersCount; i++) {
            System.out.println(allCarriers[i]);
        }
    }

    public Cargo[] getAllCargoes() {
        return allCargoes;
    }

    private void setAllCargoes(Cargo[] allCargoes) {
        this.allCargoes = allCargoes;
    }

    public Transportation[] getAllTransportations() {
        return allTransportations;
    }

    private void setAllTransportations(Transportation[] allTransportations) {
        this.allTransportations = allTransportations;
    }

    public Carrier[] getAllCarriers() {
        return allCarriers;
    }

    private void setAllCarriers(Carrier[] allCarriers) {
        this.allCarriers = allCarriers;
    }
}

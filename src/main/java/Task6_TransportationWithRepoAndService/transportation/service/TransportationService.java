package main.java.Task6_TransportationWithRepoAndService.transportation.service;

import main.java.Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

public interface TransportationService {

    void addTransportation(Transportation transportation);

    Transportation getById(long id);

    void deleteById(long id);
}

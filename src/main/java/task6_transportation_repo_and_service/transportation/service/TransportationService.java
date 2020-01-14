package task6_transportation_repo_and_service.transportation.service;

import task6_transportation_repo_and_service.transportation.domain.Transportation;

public interface TransportationService {

    void addTransportation(Transportation transportation);

    Transportation getById(long id);

    void deleteById(long id);
}

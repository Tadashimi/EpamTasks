package Task6_TransportationWithRepoAndService.transportation.repo;

import Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

public interface TransportationRepo {

    void addTransportation(Transportation transportation);

    Transportation getById(long id);

    void deleteById(long id);
}

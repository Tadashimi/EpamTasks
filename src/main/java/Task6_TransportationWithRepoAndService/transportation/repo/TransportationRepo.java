package main.java.Task6_TransportationWithRepoAndService.transportation.repo;

import main.java.Task6_TransportationWithRepoAndService.common.repo.BaseRepo;
import main.java.Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

public interface TransportationRepo extends BaseRepo {

    void add(Transportation transportation);

    Transportation getById(long id);
}

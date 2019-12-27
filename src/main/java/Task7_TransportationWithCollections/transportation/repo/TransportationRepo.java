package main.java.Task7_TransportationWithCollections.transportation.repo;

import main.java.Task7_TransportationWithCollections.common.business.repo.CommonRepo;
import main.java.Task7_TransportationWithCollections.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

}

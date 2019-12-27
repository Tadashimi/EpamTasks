package main.java.Task9_TransportationWithException.transportation.repo;

import main.java.Task9_TransportationWithException.common.business.repo.CommonRepo;
import main.java.Task9_TransportationWithException.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

    List<Transportation> getAll();

    void update(Transportation transportation);

}

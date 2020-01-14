package  task9_transportation_exceptions.transportation.repo;

import  task9_transportation_exceptions.common.business.repo.CommonRepo;
import  task9_transportation_exceptions.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

    List<Transportation> getAll();

    void update(Transportation transportation);

}

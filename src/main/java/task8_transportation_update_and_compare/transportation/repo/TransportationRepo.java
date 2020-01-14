package  task8_transportation_update_and_compare.transportation.repo;

import  task8_transportation_update_and_compare.common.business.repo.CommonRepo;
import  task8_transportation_update_and_compare.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

    Transportation[] getAll();

    Transportation update(long id, Transportation transportation);

}

package Task8_TransportationWithUpdateAndCompare.transportation.repo;

import Task8_TransportationWithUpdateAndCompare.common.business.repo.CommonRepo;
import Task8_TransportationWithUpdateAndCompare.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

    Transportation[] getAll();

    Transportation update(long id, Transportation transportation);

}

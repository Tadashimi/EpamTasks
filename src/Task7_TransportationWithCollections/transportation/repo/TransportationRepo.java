package Task7_TransportationWithCollections.transportation.repo;

import Task7_TransportationWithCollections.common.business.repo.CommonRepo;
import Task7_TransportationWithCollections.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

}

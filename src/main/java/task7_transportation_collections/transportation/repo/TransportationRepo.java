package task7_transportation_collections.transportation.repo;

import task7_transportation_collections.common.business.repo.CommonRepo;
import task7_transportation_collections.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {

    void add(Transportation transportation);

    Transportation getById(long id);

}

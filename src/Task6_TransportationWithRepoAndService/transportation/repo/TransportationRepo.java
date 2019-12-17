package Task6_TransportationWithRepoAndService.transportation.repo;

import Task6_TransportationWithRepoAndService.common.repo.BaseRepo;
import Task6_TransportationWithRepoAndService.transportation.domain.Transportation;

public interface TransportationRepo extends BaseRepo {

    void add(Transportation transportation);

    Transportation getById(long id);
}

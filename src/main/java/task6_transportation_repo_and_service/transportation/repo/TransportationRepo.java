package task6_transportation_repo_and_service.transportation.repo;

import task6_transportation_repo_and_service.common.repo.BaseRepo;
import task6_transportation_repo_and_service.transportation.domain.Transportation;

public interface TransportationRepo extends BaseRepo {

    void add(Transportation transportation);

    Transportation getById(long id);
}

package task6_transportation_repo_and_service.carrier.repo;

import task6_transportation_repo_and_service.carrier.domain.Carrier;
import task6_transportation_repo_and_service.common.repo.BaseRepo;

public interface CarrierRepo extends BaseRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);
}

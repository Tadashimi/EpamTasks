package task16_transportation_lambda.carrier.repo;

import task16_transportation_lambda.carrier.domain.Carrier;
import task16_transportation_lambda.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}

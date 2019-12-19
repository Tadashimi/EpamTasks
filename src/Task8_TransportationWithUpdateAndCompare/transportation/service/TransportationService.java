package Task8_TransportationWithUpdateAndCompare.transportation.service;

import Task8_TransportationWithUpdateAndCompare.common.business.service.CommonService;
import Task8_TransportationWithUpdateAndCompare.transportation.domain.Transportation;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation update(Long id, Transportation transportation);

}

package Task9_TransportationWithException.transportation.service;

import Task9_TransportationWithException.common.business.service.CommonService;
import Task9_TransportationWithException.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

    List<Transportation> getAll();

    void update(Transportation transportation);

}

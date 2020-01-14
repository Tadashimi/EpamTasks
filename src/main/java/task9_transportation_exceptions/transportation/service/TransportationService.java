package  task9_transportation_exceptions.transportation.service;

import  task9_transportation_exceptions.common.business.service.CommonService;
import  task9_transportation_exceptions.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

    List<Transportation> getAll();

    void update(Transportation transportation);

}

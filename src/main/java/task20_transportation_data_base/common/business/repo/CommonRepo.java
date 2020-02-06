package task20_transportation_data_base.common.business.repo;

import java.util.List;
import java.util.Optional;

public interface CommonRepo<TYPE, ID> {

    Optional<TYPE> findById(ID id);

    void save(TYPE entity);

    boolean update(TYPE entity);

    boolean deleteById(ID id);

    List<TYPE> getAll();

    int countAll();
}

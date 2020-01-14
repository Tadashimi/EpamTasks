package task11_transportation_txt_parser.common.business.repo;

import java.util.List;

public interface CommonRepo<TYPE, ID> {

    boolean deleteById(ID id);

    void add(TYPE entity);

    TYPE getById(ID id);

    List<TYPE> getAll();

    void update(TYPE entity);

    void addAll(List<TYPE> entities);

}

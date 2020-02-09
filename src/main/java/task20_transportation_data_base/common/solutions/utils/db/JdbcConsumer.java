package task20_transportation_data_base.common.solutions.utils.db;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}

package task20_transportation_data_base.common.solutions.utils.db;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}

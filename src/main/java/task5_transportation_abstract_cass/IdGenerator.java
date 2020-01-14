package task5_transportation_abstract_cass;

public final class IdGenerator {
    private IdGenerator() {
    }

    private static Long id = 0L;

    public static Long generateId() {
        return ++id;
    }
}

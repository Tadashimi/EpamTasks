package Task5_TransportationWithAbstractClass;

public abstract class ClassWithIdentifier {
    protected Long id;

    protected ClassWithIdentifier() {
        id = IdGenerator.generateId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

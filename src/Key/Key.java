package src.Key;

public abstract class Key {
    public abstract boolean lesserThan(Key otherKey);
    public abstract boolean greaterThan(Key otherKey);
    public abstract boolean equal(Key otherKey);

}

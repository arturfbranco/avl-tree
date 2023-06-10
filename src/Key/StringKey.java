package src.Key;

public class StringKey extends Key{
    private final String nameKey;

    public StringKey(String nameKey) {
        this.nameKey = nameKey.toUpperCase();
    }

    public String getNameKey() {
        return nameKey;
    }

    @Override
    public boolean lesserThan(Key otherKey) {
        StringKey otherStringKey = (StringKey) otherKey;
        int result = this.nameKey.compareTo(otherStringKey.getNameKey());
        return result < 0;
    }

    @Override
    public boolean greaterThan(Key otherKey) {
        StringKey otherStringKey = (StringKey) otherKey;
        int result = this.nameKey.compareTo(otherStringKey.getNameKey());
        return result > 0;
    }

    @Override
    public boolean equal(Key otherKey) {
        StringKey otherStringKey = (StringKey) otherKey;
        return this.nameKey.equals(otherStringKey.getNameKey());
    }

    @Override
    public String toString() {
        return this.nameKey;
    }
}

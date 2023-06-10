package src.Key;

import java.time.LocalDate;

public class LocalDateKey extends Key{

    private final LocalDate birthDate;

    public LocalDateKey(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean lesserThan(Key otherKey) {
        LocalDateKey localDateKey = (LocalDateKey) otherKey;
        int result = this.birthDate.compareTo(localDateKey.getBirthDate());
        return result < 0;
    }

    @Override
    public boolean greaterThan(Key otherKey) {
        LocalDateKey localDateKey = (LocalDateKey) otherKey;
        int result = this.birthDate.compareTo(localDateKey.getBirthDate());
        return result > 0;
    }

    @Override
    public boolean equal(Key otherKey) {
        LocalDateKey localDateKey = (LocalDateKey) otherKey;
        return this.birthDate.equals(localDateKey.getBirthDate());
    }

    @Override
    public String toString() {
        return this.birthDate.toString();
    }
}

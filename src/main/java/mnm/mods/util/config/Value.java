package mnm.mods.util.config;

/**
 * A wrapper that saves a default value.
 *
 * @param <T> The type to wrap
 */
public class Value<T> {

    protected T value;

    public Value() {}

    public Value(T val) {
        set(val);
    }

    /**
     * Sets the value.
     *
     * @param val The new value
     */
    public void set(T val) {
        this.value = val;
    }

    /**
     * Gets the value.
     *
     * @return The value
     */
    public T get() {
        return this.value;
    }

}

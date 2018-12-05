import java.util.concurrent.atomic.AtomicLongFieldUpdater;

import static java.lang.Double.*;

/**
 * A {@code double} value that may be updated atomically.
 */
@SuppressWarnings("unused")
public class AtomicDouble extends Number {

    private transient volatile long value;

    private static final AtomicLongFieldUpdater<AtomicDouble> updater = AtomicLongFieldUpdater.newUpdater(AtomicDouble.class, "value");

    /**
     * Creates a new {@code AtomicDouble} with the given initial value.
     *
     * @param initialValue the initial value
     */
    public AtomicDouble(double initialValue) {
        value = doubleToRawLongBits(initialValue);
    }


    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public final double get() {
        return longBitsToDouble(value);
    }

    /**
     * Sets to the given value.
     *
     * @param newValue the new value
     */
    public final void set(double newValue) {
        value = doubleToRawLongBits(newValue);
    }


    /**
     * Atomically sets to the given value and returns the old value.
     *
     * @param newValue the new value
     * @return the previous value
     */
    public final double getAndSet(float newValue) {
        long next = doubleToLongBits(newValue);
        return longBitsToDouble(updater.getAndSet(this, next));
    }

    /**
     * Atomically sets the value to the given updated value
     * if the current value is <a href="#bitEquals">bitwise equal</a>
     * to the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that
     * the actual value was not bitwise equal to the expected value.
     */
    public final boolean compareAndSet(double expect, double update) {
        return updater.compareAndSet(this,
                doubleToRawLongBits(expect),
                doubleToRawLongBits(update));
    }


    /**
     * Atomically adds the given value to the current value.
     *
     * @param delta the value to add
     * @return the updated value
     */
    public final double addAndGet(double delta) {
        while (true) {
            long current = value;
            double currentVal = longBitsToDouble(current);
            double nextVal = currentVal + delta;
            long next = doubleToRawLongBits(nextVal);
            if (updater.compareAndSet(this, current, next)) {
                return nextVal;
            }
        }
    }

    /**
     * Returns the String representation of the current value.
     *
     * @return the String representation of the current value
     */
    public String toString() {
        return Double.toString(get());
    }

    /**
     * Returns the value of this {@code AtomicDouble} as an {@code int}
     * after a narrowing primitive conversion.
     */
    public int intValue() {
        return (int) get();
    }

    /**
     * Returns the value of this {@code AtomicDouble} as a {@code long}
     * after a narrowing primitive conversion.
     */
    public long longValue() {
        return (long) get();
    }

    /**
     * Returns the value of this {@code AtomicDouble} as a {@code float}
     * after a narrowing primitive conversion.
     */
    public float floatValue() {
        return (float) get();
    }

    /**
     * Returns the value of this {@code AtomicDouble} as a {@code double}.
     */
    public double doubleValue() {
        return get();
    }
}
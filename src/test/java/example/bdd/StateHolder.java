package example.bdd;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author sergey.belonozhko@hp.com
 */
public class StateHolder<T> {
    private static ConcurrentMap<Class<?>, StateHolder<?>> states = new ConcurrentHashMap<>();
    private final ThreadLocal context = new ThreadLocal();

    public T getState() {
        return (T) context.get();
    }

    public void setState(T state) {
        context.set(state);
    }

    public void clear() {
        setState(null);
    }

    public static <T> StateHolder<T> getHolder(Class<T> clazz) {
        StateHolder<T> newHolder = new StateHolder<>();
        StateHolder stateHolder = states.putIfAbsent(clazz, newHolder);
        if (stateHolder == null) {
            stateHolder = newHolder;
        }
        return stateHolder;
    }
}

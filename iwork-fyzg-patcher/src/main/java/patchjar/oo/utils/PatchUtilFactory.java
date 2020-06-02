package patchjar.oo.utils;

public class PatchUtilFactory<T extends Type> {
    private Class<T> t;

    public PatchUtilFactory(Class<T> t) {
        this.t = t;
    }

    public Type newInstance() throws IllegalAccessException, InstantiationException {
        Type instance = t.newInstance();
        return instance.newInstance();
    }
}

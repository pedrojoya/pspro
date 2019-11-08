package es.iessaladillo.pedrojoya.executorcompletionservice;

class ResultOrThrowable<T> {

    private final T result;
    private final Throwable throwable;

    private ResultOrThrowable(T result, Throwable throwable) {
        this.result = result;
        this.throwable = throwable;
    }

    T getResult() {
        return result;
    }

    Throwable getThrowable() {
        return throwable;
    }

    static <T> ResultOrThrowable<T> newResult(T result) {
        return new ResultOrThrowable<>(result, null);
    }

    static <T> ResultOrThrowable<T> newThrowable(Throwable throwable) {
        return new ResultOrThrowable<>(null, throwable);
    }

}

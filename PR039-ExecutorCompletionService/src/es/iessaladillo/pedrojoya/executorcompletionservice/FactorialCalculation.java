package es.iessaladillo.pedrojoya.executorcompletionservice;

class FactorialCalculation {

    private final String requester;
    private final int value;
    private final String when;
    private final ResultOrThrowable<Integer> resultOrThrowable;

    FactorialCalculation(String requester, int value, String when, ResultOrThrowable<Integer> resultOrException) {
        this.requester = requester;
        this.value = value;
        this.when = when;
        this.resultOrThrowable = resultOrException;
    }

    String getRequester() {
        return requester;
    }

    int getValue() {
        return value;
    }

    String getWhen() {
        return when;
    }

    Integer getResult() {
        return resultOrThrowable.getResult();
    }

    Throwable getThrowable() {
        return resultOrThrowable.getThrowable();
    }

}

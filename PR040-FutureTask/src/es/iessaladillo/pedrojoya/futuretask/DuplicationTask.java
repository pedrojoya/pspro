package es.iessaladillo.pedrojoya.futuretask;

import java.util.concurrent.Callable;

public class DuplicationTask implements Callable<Integer> {

    private Integer value;

    DuplicationTask(Integer value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        return value * 2;
    }

}

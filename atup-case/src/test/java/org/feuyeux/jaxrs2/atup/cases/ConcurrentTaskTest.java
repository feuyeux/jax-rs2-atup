package org.feuyeux.jaxrs2.atup.cases;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by hanl on 12/18/13.
 */
public class ConcurrentTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<TestRunner> tasks = new ArrayList<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        int n = 5;
        for (int i = 0; i < 20; i++) {
            map.put("" + i, "" + i);
        }
        for (int i = 0; i < n; i++) {
            tasks.add(new TestRunner(i + "-Runner", map));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        List<Future<String>> resultFutures = executorService.invokeAll(tasks);

        for (int i = 0; i < n; i++) {
            System.out.println(resultFutures.get(i).get());
        }
        executorService.shutdown();
    }
}

class TestRunner implements Callable<String> {
    private String name;
    private ConcurrentHashMap<String, String> map;

    SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss SSS");

    TestRunner(String name, ConcurrentHashMap<String, String> map) {
        this.name = name;
        this.map = map;
    }

    @Override
    public String call() throws Exception {
        final Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        StringBuilder r = new StringBuilder(name);
        while (iterator.hasNext()) {
            final Map.Entry<String, String> kv = iterator.next();
            String value = map.remove(kv.getKey());
            if (value != null) {
                r.append(" get ").append(value);
                r.append(" at " + f.format(new Date())).append("; ");
            }
        }
        return r.toString();
    }
}

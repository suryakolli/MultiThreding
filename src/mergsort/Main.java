package mergsort;

import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> arr = List.of(9,7,8,6,1,2,4,3,5,10);

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<List<Integer>> futureSortedList = executorService.submit(new Sorter(arr));

        List<Integer> sortedList = futureSortedList.get();

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);

        System.out.println(sortedList);
    }
}

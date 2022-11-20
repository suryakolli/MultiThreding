package mergsort;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> arr = List.of(9,7,8,6,1,2,4,3,5,10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<List<Integer>> futureSortedList = executorService.submit(new Sorter(arr));
        List<Integer> sortedList = futureSortedList.get();
        System.out.println(sortedList);
    }
}

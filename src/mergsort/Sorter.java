package mergsort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Sorter implements Callable<List<Integer>> {
    private final List<Integer> list;

    Sorter(List<Integer> l) {
        this.list = l;
    }

    @Override
    public List<Integer> call() throws Exception {
        if(list.size() <= 1) return list;

        int mid = list.size() / 2;

        List<Integer> left = new ArrayList<>();
        for(int i = 0; i < mid; i++) {
            left.add(list.get(i));
        }

        List<Integer> right = new ArrayList<>();
        for(int i = mid; i < list.size(); i++) {
            right.add(list.get(i));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<List<Integer>> futureSortedLeft = executorService.submit(new Sorter(left));
        Future<List<Integer>> futureSortedRight = executorService.submit(new Sorter(right));

        List<Integer> sortedLeft = futureSortedLeft.get();
        List<Integer> sortedRight = futureSortedRight.get();

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);

        return merge(sortedLeft, sortedRight);
    }

    private List<Integer> merge (List<Integer> left, List<Integer> right) {
        List<Integer> finalList = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                finalList.add(left.get(i));
                i++;
            } else {
                finalList.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            finalList.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            finalList.add(right.get(j));
            j++;
        }

        return finalList;
    }
}

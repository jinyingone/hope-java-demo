package fun.jinying;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并发修改问题
 *
 * @author jy
 * @date 2019-10-24 上午11:41
 **/
public class ConcurrentModifyError {
    private List<String> cacheList;

    {
        cacheList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            cacheList.add(Integer.toString(i));
        }
    }

    /**
     * 在遍历的过程中修改list中的数据会发生问题.
     * 尤其是在从本地缓存中直接拿出一个list,又存在修改操作时候,此问题会比较隐晦
     *
     * @throws InterruptedException
     */
    public void iterateList() throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<?> submit = executorService.submit(() -> {
                List<String> list = getFromLocalCache();
                list.remove(0);
                //此处会发生异常,因为有可能在遍历的过程中,其他线程修改了list
                list.forEach(Integer::parseInt);
            });
            futures.add(submit);
        }

        for (Future future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                throw (RuntimeException) e.getCause();
            }
        }
    }

    /**
     * 模拟从本地缓存中读取数据
     *
     * @return
     */
    private List<String> getFromLocalCache() {
        return cacheList;
    }
}

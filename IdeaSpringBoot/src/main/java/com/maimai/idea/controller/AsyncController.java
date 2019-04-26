package com.maimai.idea.controller;

import com.maimai.idea.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author user
 */
@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String testAsyncNoRetrun(){
        long start = System.currentTimeMillis();
        asyncService.doNoReturn();
        return String.format("任务执行成功,耗时{%s}", System.currentTimeMillis() - start);
    }



    @GetMapping("/hi")
    public Map<String, Object> testAsyncReturn() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Map<String, Object> map = new HashMap<>(16);
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> future = asyncService.doReturn(i);
            futures.add(future);
        }
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
        return map;
    }

    // public  String  haha(){
    //     ExecutorService service = Executors.newFixedThreadPool(5);
    //     RunnableTask1 task1 = new RunnableTask1();
    //     service.execute(task1);
    //     logger.info("=========》当前线程名："+Thread.currentThread().getName());
    //     return "异步,正在解析......";
    //
    // }
}

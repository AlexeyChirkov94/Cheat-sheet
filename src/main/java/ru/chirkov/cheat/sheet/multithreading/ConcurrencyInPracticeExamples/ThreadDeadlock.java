package ru.chirkov.cheat.sheet.multithreading.ConcurrencyInPracticeExamples;

import java.util.concurrent.*;

/**
 * ThreadDeadlock
 * <p/>
 * Task that deadlocks in a single-threaded Executor
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThreadDeadlock {

    public static void main(String[] args) throws Exception {
        RenderPageTask renderPageTask = new RenderPageTask();
        String call = renderPageTask.call();
        System.out.println(call);
    }


    static ExecutorService exec = Executors.newSingleThreadExecutor();

    public static class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        public String call() throws Exception {
            // Here's where we would actually read the file
            return fileName + "-loaded";
        }
    }

    public static class RenderPageTask implements Callable<String> {
        public String call() throws Exception {
            Future<String> header, footer;
//            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            header = exec.submit(new LoadFileTask("header.html"));
            String page = renderBody();
            // Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // Here's where we would actually render the page
            return " boby ";
        }
    }
}

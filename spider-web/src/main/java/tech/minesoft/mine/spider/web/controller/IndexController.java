//package tech.minesoft.mine.spider.web.controller;
//
//import tech.minesoft.mine.spider.core.SpiderTask;
//import okhttp3.Request;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import tech.minesoft.mine.spider.core.Spider;
//import tech.minesoft.mine.spider.core.component.SpiderGenerator;
//import tech.minesoft.mine.spider.core.vo.SpiderRequest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class IndexController {
//
//    @RequestMapping("/")
//    public void index() {
//
//    }
//
//    @RequestMapping("/test")
//    public void test() {
//    }
//
//    public static void main(String[] args) {
//        SpiderTask task = new SpiderTask("task", new SpiderGenerator() {
//            @Override
//            public List<SpiderRequest> init() {
//                List<SpiderRequest> requestList = new ArrayList<>();
//
//                String url = "https://cn.bing.com/search?q=init";
//                for (int i = 0; i < 5; i++) {
//                    SpiderRequest request = new SpiderRequest();
//
//                    request.setRequest(new Request.Builder().url(url + i).build());
//
//                    requestList.add(request);
//                }
//                return requestList;
//            }
//
//            @Override
//            public List<SpiderRequest> generate() {
//                List<SpiderRequest> requestList = new ArrayList<>();
//
//                String url = "https://cn.bing.com/search?q=generate";
//                for (int i = 5; i < 10; i++) {
//                    SpiderRequest request = new SpiderRequest();
//
//                    request.setRequest(new Request.Builder().url(url + i).build());
//
//                    requestList.add(request);
//                }
//                return requestList;
//            }
//        });
//
//        Spider spider = task.build(3);
//        spider.start();
//
//        int second = 0;
//        while (true) {
//            try {
//                System.out.println(second++);
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}

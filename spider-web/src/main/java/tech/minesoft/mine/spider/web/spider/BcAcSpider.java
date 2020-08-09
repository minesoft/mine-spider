//package tech.minesoft.mine.spider.impl.spider;
//
//import tech.minesoft.mine.spider.core.SpiderTask;
//import tech.minesoft.mine.spider.core.component.SpiderExtractor;
//import tech.minesoft.mine.spider.core.component.SpiderGenerator;
//import tech.minesoft.mine.spider.core.component.SpiderSaver;
//import tech.minesoft.mine.spider.core.Spider;
//import tech.minesoft.mine.spider.core.utils.Content;
//import tech.minesoft.mine.spider.core.vo.SpiderRequest;
//import tech.minesoft.mine.spider.core.vo.SpiderResponse;
//import okhttp3.Request;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BcAcSpider implements SpiderGenerator, SpiderSaver, SpiderExtractor {
//
//    @Override
//    public Content extract(SpiderResponse response) {
//        return null;
//    }
//
//    @Override
//    public List<SpiderRequest> init() {
//        List<SpiderRequest> requestList = new ArrayList<>();
//
//        String url = "http://bbcc.ac/2/forum.php?mod=forumdisplay&fid=66&filter=lastpost&orderby=lastpost";
//
//        SpiderRequest request = new SpiderRequest();
//
//        request.setRequest(new Request.Builder().url(url).build());
//
//        requestList.add(request);
//
//        return requestList;
//    }
//
//    @Override
//    public List<SpiderRequest> generate() {
//        List<SpiderRequest> requestList = new ArrayList<>();
//
//        String url = "https://cn.bing.com/search?q=generate";
//        for (int i = 5; i < 10; i++) {
//            SpiderRequest request = new SpiderRequest();
//
//            request.setRequest(new Request.Builder().url(url + i).build());
//
//            requestList.add(request);
//        }
//        return requestList;
//    }
//
//    @Override
//    public void saveContent(Content content) {
//
//    }
//
//    public void run() {
//        SpiderTask task = new SpiderTask("bbcc.ac", this);
//
//        task.setSaver(this);
//
//        Spider spider = task.build();
//
//        spider.start();
//    }
//}

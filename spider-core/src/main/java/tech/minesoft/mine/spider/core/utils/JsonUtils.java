package tech.minesoft.mine.spider.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static boolean writeJson(File file, Object value) {
        try {
            objectMapper.writeValue(file, value);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeJson(Writer writer, Object value) {
        try {
            objectMapper.writeValue(writer, value);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeJson(OutputStream os, Object value) {
        try {
            objectMapper.writeValue(os, value);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String toJson(String[] keys, Object[] vals) {
        try {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < keys.length; i++) {
                map.put(keys[i], vals[i]);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T toBean(String content, Class<T> clz) {

        try {
            return objectMapper.readValue(content, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T toBean(Object obj, Class<T> clz) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            if (null != json && !"".equals(json)) {
                return objectMapper.readValue(json, clz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

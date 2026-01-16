package com.example.librarymanagementfrontend.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Convert book to JSON string
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    // Convert JSON string to book
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    // Convert JSON string to List of books
    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, listType);
    }
}

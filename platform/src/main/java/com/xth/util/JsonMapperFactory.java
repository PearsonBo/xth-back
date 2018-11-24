package com.xth.util;

/**
 * @author
 */
public class JsonMapperFactory {

    private static final JsonMapper JSON_MAPPER = new JsonMapper();

    private static final JsonMapper WITH_JSON_TYPE_INFO_MAPPER = new JsonMapper(false);

    private JsonMapperFactory() {

    }

    public static JsonMapper getJsonMapper() {
        return JSON_MAPPER;
    }

    public static JsonMapper getWithJsonTypeInfoMapper() {
        return WITH_JSON_TYPE_INFO_MAPPER;
    }

}

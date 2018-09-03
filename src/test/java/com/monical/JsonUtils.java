/**
 * Project: tuangou-remote-biz
 *
 * File Created at 2013-4-6
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.monical;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JsonUtils
 *
 * @author xing.yang
 */
public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static String toJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T fromJson(String content, Class<T> valueType) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	public static <T> T fromJson(String content, TypeReference<?> valueTypeRef) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return (T) objectMapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}

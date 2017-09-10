package com.mzgy.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import  com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Protostuff serializer tool, for POJO serialization.
 * Protostuff is much more efficient than json, even faster than Protobuf and Avro, but the serialized string is human-unreadable.
 * Not support Array or Generic-type, please wrap these special objects via a POJO with empty constructors.
 *
 * @author lhfcws
 * @since 2016-03-16
 */
public class ProtostuffSerializer implements Serializable {

    static Map<Class, Schema> schemaCache = new ConcurrentHashMap<>();

    /**
     * common protostuff serialize, object need a empty constructor
     * Be careful to convert result byte[] to String, use new String(bytes, StandardCharsets.UTF_16LE).
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serializeObject(T obj) {
        Class<T> klass = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        try {
            if (schemaCache.containsKey(klass)) {
                return ProtostuffIOUtil.toByteArray(obj, schemaCache.get(klass), buffer);
            } else {
                schemaCache.put(klass, RuntimeSchema.getSchema(klass));
                return ProtostuffIOUtil.toByteArray(obj, schemaCache.get(klass), buffer);
            }
        } finally {
            buffer.clear();
        }
    }

    /**
     * common protostuff unserialize
     *
     * @param bs
     * @param klass
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] bs, Class<T> klass) {
        if (schemaCache.containsKey(klass)) {
            Schema<T> schema = schemaCache.get(klass);
            T msg = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bs, msg, schema);
            return msg;
        } else {
            Schema<T> schema = RuntimeSchema.getSchema(klass);
            T msg = schema.newMessage();
            schemaCache.put(klass, schema);
            ProtostuffIOUtil.mergeFrom(bs, msg, schema);
            return msg;
        }
    }
}
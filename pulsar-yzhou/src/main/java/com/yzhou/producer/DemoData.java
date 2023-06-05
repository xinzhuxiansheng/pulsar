package com.yzhou.producer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import lombok.Data;

@Data
public class DemoData implements Serializable {
    private static final long serialVersionUID = 1L;
    private int intField;
    private String stringField;

    public DemoData(int intField, String stringField) {
        this.intField = intField;
        this.stringField = stringField;
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArray);
        objectOutputStream.writeObject(this);
        return byteArray.toByteArray();
    }

    public static DemoData deserializer(byte[] bytes) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
        return (DemoData) inputStream.readObject();
    }
}
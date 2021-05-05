package com.company;

public class KeyValuePersistor {

    public static void main(String[] args) {
        LocalKeyValuePersister localKeyValuePersister = new LocalKeyValuePersister();
        KeyValuePersistor keyValuePersistor = new KeyValuePersistor();

        System.out.println(keyValuePersistor.read(localKeyValuePersister, "key1"));
        keyValuePersistor.update(localKeyValuePersister, "key1", "value1.10");
    }

    public void update(LocalKeyValuePersister localKeyValuePersister, String key, String value) {
        localKeyValuePersister.update(key, value);
    }

    public void create(LocalKeyValuePersister localKeyValuePersister, String key, String value) {
        localKeyValuePersister.update(key, value);
    }

    public void delete(LocalKeyValuePersister localKeyValuePersister, String key) {
        localKeyValuePersister.delete(key);
    }

    public String read(LocalKeyValuePersister localKeyValuePersister, String key) {
        return localKeyValuePersister.read(key);
    }
}

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HashList<K,V> {
    private List<K> keyList = new LinkedList<>();
    private HashMap<K,V> hashMap = new HashMap<>();

    public int size() {
        return keyList.size();
    }

    public void addElement(K key, V value) {
        keyList.add(key);
        hashMap.put(key, value);
    }

    public V getValueByKey(K key) {
        return hashMap.get(key);
    }

    public V getValueByIndex(int index) {
        return hashMap.get(keyList.get(index));
    }

    public K getKeyByIndex(int index) {
        return keyList.get(index);
    }
}

public class HashTable implements Table {

    private String[] keys;
    private String[] values;
    private int size;
    private final int capacity;
    private static final String deleted = "deleted";  // Маркер удалённой ячейки

    public HashTable() {
        capacity = 20;
        keys = new String[capacity];
        values = new String[capacity];
        size = 0;
    }

    public int hash (String key){
        return Math.abs(key.hashCode()) % keys.length; //hashCode() - Метод, который возвращает целочисленное значение для объекта.
    }

    @Override
    public void put(String key, String value) {
        if (size>= keys.length) {
            return;
        }
        int index = hash(key);
        for(int i = 0; i<keys.length; i++){
            index = (index + i) % keys.length; // Линейное пробирование
            if (keys[index] == null || keys[index] == deleted){
                keys[index] = key;
                values[index] = value;
                size++;
                return;
            }
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
        }
    }

    @Override
    public String get(String key) {
        int index = hash(key);
        int startIndex = index; //Начальный индекс, для обнаружения исходной точки
        while (keys[index] != null){
            if(keys[index] != deleted && keys[index].equals(key)){
                return values[index];
            }
            index = (index + 1) % keys.length; // Линейное пробирование
            if (index == startIndex){
                return null;
            }
        }
        return null;
    }

    @Override
    public void remove(String key) {
        int index = hash(key);
        for(int i = 0; i<keys.length; i++){
            index = (index + i) % keys.length; // Линейное пробирование
            if (keys[index] == null){
                return;
            }
            if (keys[index] != deleted && keys[index].equals(key)){
                keys[index] = deleted;
                values[index] = deleted;
                size--;
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }
}
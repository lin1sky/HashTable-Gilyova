public class HashTable implements Table {

    private String[] keys;
    private String[] values;
    private int size;
    private int maxSize;
    private static final String deleted = "deleted";  // Маркер удалённой ячейки

    public HashTable() {
        maxSize = 20;
        keys = new String[maxSize];
        values = new String[maxSize];
        size = 0;
    }

    public int hash (String key){
        return Math.abs(key.hashCode()) % keys.length; //hashCode() - Метод, который возвращает целочисленное значение для объекта.
    }

    @Override
    public void put(String key, String value) {
        if (size>= keys.length) {
            resize();
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
    
    private void resize(){
        int newSize = maxSize * 2;
        String[] oldKeys = keys;
        String[] oldValues = values;
        keys = new String[newSize];
        values = new String[newSize];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++){
            if (oldKeys[i] != null && oldKeys[i] != deleted){
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}

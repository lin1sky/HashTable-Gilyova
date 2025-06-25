public class Main {
    public static void main(String[] args) {

        Table table = new HashTable();
        table.put("1", "one");
        table.put("2", "two");
        table.put("3", "three");
        table.put("4", "four");
        table.put("5", "five");
        table.put("6", "six");
        table.put("7", "seven");
        table.put("8", "eight");
        table.put("9", "nine");
        table.put("10", "ten");
        table.put("15", "fifteen");

        System.out.println("Размер: " + table.size());
        System.out.println(table.get("4")); //проверяем эти ключи, т.к. из-за метода hash их изначальный индекс будет одинаковый
        System.out.println(table.get("15"));
        System.out.println(table.get("20")); //проверим возврат значения для ключа, которого не существет

        table.remove("4");
        System.out.println("Размер: " + table.size());
        System.out.println(table.get("4"));
        System.out.println(table.get("15"));

    }
}

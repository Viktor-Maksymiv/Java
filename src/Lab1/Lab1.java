package Lab1;

public class Lab1 {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.InsertElement(45, "hello".toCharArray());
        set.InsertElement(145, "world".toCharArray());
        set.InsertElement(99, "Java".toCharArray());
        set.InsertElement(199, "Array".toCharArray());
        System.out.println(set.GetElement(45));
        set.DeleteElement(45);
        set.InsertElement(45, "hello world!".toCharArray());
        System.out.println(set.GetElement(45));
        System.out.println(set.GetElement(145));
        System.out.println(set.GetElement(99));
        System.out.println(set.GetElement(299));

    }
    private static class HashSet {

        HashElement[] Table = new HashElement[100];

        private int HashFunc(int key){
            return key % this.Table.length;
        }

        private void InsertElement(int key, char[] value){
            int counter = 0;
            for (int i = 0; i < this.Table.length; i++){
                if (this.Table[i] != null){
                    counter++;
                }
            }
            if (counter > Table.length / 2){
                System.out.println("The half of the table is full");
                return;
            }
            if (Table[HashFunc(key)] == null) {
                this.Table[HashFunc(key)] = new HashElement();
                this.Table[HashFunc(key)].key = key;
                this.Table[HashFunc(key)].value = value;
            } else if (Table[HashFunc(key)].key == key) {
                System.out.println("Key is already in the table");
            } else {
                for (int i = HashFunc(key); i < Table.length; i++) {
                    if (Table[i] == null) {
                        this.Table[i] = new HashElement();
                        this.Table[i].key = key;
                        this.Table[i].value = value;
                        break;
                    }
                    if (Table.length - 1 == i) {
                        System.out.println("there isn`t free space after " + i + "-nd element");
                    }
                }
            }
        }

        private char[] GetElement(int key){

            if (Table[HashFunc(key)] == null) {
                System.out.println("Key not found");
                return "\0".toCharArray();
            }
            if (Table[HashFunc(key)].key == key) {
                return this.Table[HashFunc(key)].value;
            }
            else {
                for (int i = HashFunc(key); i < Table.length; i++) {
                    if (Table[i].key == key) {
                        return Table[i].value;
                    }
                }
            }
            System.out.println("Key not found");
            return "\0".toCharArray();
        }

        private  void DeleteElement(int key){
            if (Table[HashFunc(key)] == null) {
                System.out.println("Not found key to delete");
                return;
            }
            if (Table[HashFunc(key)].key == key) {
                this.Table[HashFunc(key)] = null;
                return;
            }
            else {
                for (int i = HashFunc(key); i < Table.length; i++) {
                    if (Table[i].key == key) {
                        this.Table[i] = null;
                        return;
                    }
                }
            }
            System.out.println("Not found key to delete");
        }
    }

    public static class HashElement {
        int key;
        char[] value;
    }
}
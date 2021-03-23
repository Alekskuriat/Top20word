import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> arrayListString = new ArrayList<>();

        InputStream stream = Main.class.getResourceAsStream("book.txt");
        byte[] data = new byte[stream.available()];
        stream.read(data);
        String text = new String(data);

        String[] lines = text.split(" ");

        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll("[.,!?:;' -_]", "");
            lines[i] = lines[i].toLowerCase();
        }

        Collections.addAll(arrayListString, lines);

        top20word(arrayListString);
    }

    private static void top20word(ArrayList<String> arrayListString) {
        Set<String> stringSet = new HashSet<String>(arrayListString);
        Map<String, Integer> map = new TreeMap<>();
        Iterator<String> iterSet = stringSet.iterator();
        while (iterSet.hasNext()) {
            String strSet = iterSet.next();
            int count = 1;
            Iterator<String> iterArrayListString = arrayListString.iterator();
            while (iterArrayListString.hasNext()) {
                String strArray = iterArrayListString.next();
                if (strArray.equals(strSet)) {
                    map.put(strSet, count++);
                }
            }
        }

        List<Map.Entry<String, Integer>> list = map.entrySet().stream()
                .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toList());
        System.out.println("Топ-20 слов:");
        for (int i = 0; i < 20; i++) {

            System.out.println(list.get(i).getKey().toUpperCase() + " - " + list.get(i).getValue());
        }

    }


}

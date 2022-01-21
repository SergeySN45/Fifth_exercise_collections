import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Есть входной файл с набором слов, написанных через пробел
 * Необходимо:
 * - Прочитать слова из файла.
 * - Отсортировать в алфавитном порядке.
 * - Посчитать сколько раз каждое слово встречается в файле
 * - Вывести статистику на консоль
 * - Найти слово с максимальным количеством повторений
 * - Вывести на консоль это слово и сколько раз оно встречается в файле
 * @author SNesterov
 * @version 0.1
 */

public class Fifth_exercise_collections {
    public static void main(String[] args) throws IOException {

        //Считываем файл и разбиваем по словам
        StringBuilder stringBuilder = readFromFile(new FileInputStream("D:\\Java courses\\Fifth_exercise_collections\\Words.txt"));
        String readedFile = stringBuilder.toString();
        String[] words = readedFile.split(" ");

        //Создаём коллекцию на основе массива слов
        ArrayList<String> wordsInList = new ArrayList<>();
        for (String word: words) {
            wordsInList.add(word);
        }

        //сортируем слова в алфавитном порядке и выводим
        Collections.sort(wordsInList);
        for (String word : wordsInList) {
            System.out.print(word + " ");
        }
        System.out.println();

        //Определяем количесво сколько раз встречалось каждое слово
        TreeMap<String, Integer> countedWords = new TreeMap<>();
        for (String word: wordsInList) {
            if (countedWords.containsKey(word)) {
                countedWords.replace(word, countedWords.get(word) + 1);
            } else countedWords.put(word, 1);
        }

        //Выводим информацию на экран
        for (Map.Entry<String, Integer> a: countedWords.entrySet()) {
            System.out.println(a.toString());
        }

        //Находим слово которое встречалось чаще всех и выводим информацию
        String encounteredKey =  findMax(countedWords);
        System.out.println("The word '" + encounteredKey + "' is the most encountered. It met " + countedWords.get(encounteredKey) + " times");
    }

    //Функция для чтения данных из файла
    public static StringBuilder readFromFile(FileInputStream fileInputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        while ((i = bufferedInputStream.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder;
    }

    //Находим самый встречающийся элемент и его количество
    public static String findMax (TreeMap<String, Integer> treeMap) {
        String key = treeMap.firstKey();
        int max = treeMap.get(key);

        for (Map.Entry<String, Integer> a: treeMap.entrySet()) {
            if (a.getValue() > max) {
                key = a.getKey();
                max = a.getValue();
            }
        }
        return key;
    }
}

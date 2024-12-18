import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Diplodocus diplodocus1 = new Diplodocus("диплодок1", 25, Diplodocus.DinoEra.ЮРСКИЙ);
        new Diplodocus("диплодок2", 27, Diplodocus.DinoEra.МЕЛОВОЙ);
        new Diplodocus("диплодок3", 30, Diplodocus.DinoEra.ЮРСКИЙ);
        new Diplodocus("диплодок4", 28, Diplodocus.DinoEra.ТРИАСОВЫЙ);
        new Diplodocus("диплодок5", 29, Diplodocus.DinoEra.МЕЛОВОЙ);

        // Создаем таблицу один раз
        Annotation.createTable(diplodocus1);

        // Получаем все экземпляры Diplodocus и вставляем их в таблицу
        List<Diplodocus> diplodocuses = Diplodocus.getAllInstances();
        for (Diplodocus d : diplodocuses) {
            Annotation.insertIntoTable(d);
        }
    }
}

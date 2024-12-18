import java.util.ArrayList;
import java.util.List;

@Table(title = "ТаблицаДиплодоков")
public class Diplodocus {
    private static final List<Diplodocus> diplodocusInstances = new ArrayList<>();

    public enum DinoEra {
        ТРИАСОВЫЙ, ЮРСКИЙ, МЕЛОВОЙ;
    }

    @Column
    private String имя;

    @Column
    private int высота;

    @Column
    private DinoEra эпоха;

    public Diplodocus(String имя, int высота, DinoEra эпоха) {
        this.имя = имя;
        this.высота = высота;
        this.эпоха = эпоха;
        diplodocusInstances.add(this);  // Добавляем текущий объект в список
    }

    public static List<Diplodocus> getAllInstances() {
        return diplodocusInstances;
    }
}
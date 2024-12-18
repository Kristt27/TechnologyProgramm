import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Annotation {

    // Метод для создания таблицы в базе данных на основе аннотаций в классе
    public static void createTable(Object cl) throws Exception {
        Class<?> clClass = cl.getClass(); // Получаем объект Class для переданного класса
        // Проверяем, есть ли у класса аннотация @Table. Если нет, выбрасываем исключение.
        if (!clClass.isAnnotationPresent(Table.class)) {
            throw new Exception("Класс не содержит аннотации @Table"); // Класс не имеет аннотации @Table
        }
        Table table = clClass.getAnnotation(Table.class); // Получаем аннотацию @Table из класса
        StringBuilder sql = new StringBuilder("CREATE TABLE " + table.title() + " ("); // Начинаем строить SQL-запрос для CREATE TABLE
        StringBuilder sqlDEL = new StringBuilder("DROP TABLE IF EXISTS " + table.title() + ";"); // SQL-запрос для удаления таблицы, если она существует
        Field[] fields = clClass.getDeclaredFields(); // Получаем все поля, объявленные в классе

        // Проходим по каждому полю, чтобы создать соответствующие колонки в таблице
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) { // Проверяем, есть ли у поля аннотация @Column
                field.setAccessible(true); // Делаем поле доступным для изменения, даже если оно private
                Column column = field.getAnnotation(Column.class); // Получаем аннотацию @Column
                sql.append(field.getName()).append(" "); // Добавляем имя поля в SQL-запрос
                // Проверяем тип поля и задаем соответствующий SQL-тип для колонки
                if (field.getType() == int.class) {
                    sql.append("INT");
                }
                else if (field.getType() == String.class || field.getType().isEnum()) {
                    sql.append("TEXT");
                }
                sql.append(","); // Добавляем запятую после каждого поля
            }
        }
        sql.deleteCharAt(sql.length() - 1); // Убираем последнюю запятую
        sql.append(");"); // Закрываем SQL-запрос на создание таблицы

        Connection connection = null;
        try {
            // Устанавливаем соединение с базой данных SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:9.db");
            Statement statement = connection.createStatement(); // Создаем объект Statement для выполнения SQL-запросов
            statement.execute(sqlDEL.toString()); // Выполняем запрос на удаление таблицы, если она существует
            statement.execute(sql.toString()); // Выполняем запрос на создание таблицы
        } catch (Exception e) {
            e.printStackTrace(); // Выводим стек ошибок, если что-то пошло не так
        } finally {
            try {
                if (connection != null) connection.close(); // Закрываем соединение с базой данных
            } catch (Exception e) {
                e.printStackTrace(); // Выводим стек ошибок, если не удалось закрыть соединение
            }
        }
    }

    // Метод для вставки новой записи в таблицу на основе полей объекта
    public static void insertIntoTable(Object cl) {
        Class<?> clClass = cl.getClass(); // Получаем объект Class для переданного класса
        // Проверяем, есть ли у класса аннотация @Table. Если нет, выбрасываем исключение.
        if (!clClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Класс не содержит аннотации @Table"); // Класс не имеет аннотации @Table
        }
        String tableName = clClass.getAnnotation(Table.class).title(); // Получаем имя таблицы из аннотации @Table

        Field[] fields = clClass.getDeclaredFields(); // Получаем все поля, объявленные в классе
        StringBuilder query = new StringBuilder("INSERT OR IGNORE INTO " + tableName + " ("); // Начинаем строить SQL-запрос для INSERT

        // Проходим по каждому полю и добавляем его в список колонок SQL-запроса
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) { // Проверяем, есть ли у поля аннотация @Column
                query.append(field.getName()).append(","); // Добавляем имя поля в список колонок
            }
        }
        query.deleteCharAt(query.length() - 1).append(") VALUES ("); // Убираем последнюю запятую и открываем скобки для значений

        // Проходим по каждому полю, получаем его значение и добавляем его в часть VALUES SQL-запроса
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) { // Проверяем, есть ли у поля аннотация @Column
                field.setAccessible(true); // Делаем поле доступным для чтения, даже если оно private
                try {
                    query.append("'").append(field.get(cl)).append("',"); // Добавляем значение поля в запрос, оборачивая его в одиночные кавычки
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace(); // Выводим стек ошибок, если произошла ошибка при доступе к значению поля
                }
            }
        }
        query.deleteCharAt(query.length() - 1).append(")"); // Убираем последнюю запятую и закрываем скобки

        Connection connection = null;
        try {
            // Устанавливаем соединение с базой данных SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:9.db");
            Statement statement = connection.createStatement(); // Создаем объект Statement для выполнения SQL-запросов
            statement.execute(query.toString()); // Выполняем SQL-запрос на вставку данных
        }
        catch (Exception e) {
            e.printStackTrace(); // Выводим стек ошибок, если что-то пошло не так
        }
        finally {
            try {
                if (connection != null) connection.close(); // Закрываем соединение с базой данных
            }
            catch (Exception e) {
                e.printStackTrace(); // Выводим стек ошибок, если не удалось закрыть соединение
            }
        }
    }
}

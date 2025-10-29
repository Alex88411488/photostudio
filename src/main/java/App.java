

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        PhotostudioService service = new PhotostudioService();
        service.initSampleData();

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== Photostudio ===");
            System.out.println("1. Показать всех фотографов");
            System.out.println("2. Показать всех клиентов");
            System.out.println("3. Показать все фотосессии");
            System.out.println("4. Поиск фотосессий по имени клиента");
            System.out.println("5. Фильтрация фотографов по стажу (минимум лет)");
            System.out.println("6. Добавить клиента");
            System.out.println("7. Добавить фотографа");
            System.out.println("8. Добавить фотосессию");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> service.getAllPhotographers().forEach(System.out::println);
                case "2" -> service.getAllClients().forEach(System.out::println);
                case "3" -> service.getAllSessions().forEach(System.out::println);
                case "4" -> {
                    System.out.print("Введите имя клиента (или часть): ");
                    String q = sc.nextLine();
                    List<PhotoSession> res = service.findSessionsByClientName(q);
                    if (res.isEmpty()) System.out.println("Ничего не найдено.");
                    else res.forEach(System.out::println);
                }
                case "5" -> {
                    System.out.print("Минимум лет опыта: ");
                    int min = Integer.parseInt(sc.nextLine());
                    List<Photographer> res = service.filterPhotographersByExperience(min);
                    if (res.isEmpty()) System.out.println("Нет подходящих фотографов.");
                    else res.forEach(System.out::println);
                }
                case "6" -> {
                    System.out.print("Имя клиента: ");
                    String name = sc.nextLine();
                    System.out.print("Телефон: ");
                    String phone = sc.nextLine();
                    Client c = service.addClient(name, phone);
                    System.out.println("Добавлено: " + c);
                }
                case "7" -> {
                    System.out.print("Имя фотографа: ");
                    String name = sc.nextLine();
                    System.out.print("Стаж (лет): ");
                    int y = Integer.parseInt(sc.nextLine());
                    Photographer p = service.addPhotographer(name, y);
                    System.out.println("Добавлено: " + p);
                }
                case "8" -> {
                    System.out.print("ID клиента: ");
                    int cid = Integer.parseInt(sc.nextLine());
                    System.out.print("ID фотографа: ");
                    int pid = Integer.parseInt(sc.nextLine());
                    System.out.print("Дата (YYYY-MM-DD): ");
                    LocalDate d = LocalDate.parse(sc.nextLine());
                    System.out.print("Описание: ");
                    String desc = sc.nextLine();
                    // get by id (simple)
                    Client client = service.getAllClients().stream().filter(c -> c.getId() == cid).findFirst().orElse(null);
                    Photographer photo = service.getAllPhotographers().stream().filter(p -> p.getId() == pid).findFirst().orElse(null);
                    if (client == null || photo == null) {
                        System.out.println("Неверный ID клиента или фотографа.");
                    } else {
                        PhotoSession s = service.addSession(client, photo, d, desc);
                        System.out.println("Добавлено: " + s);
                    }
                }
                case "0" -> running = false;
                default -> System.out.println("Неверный выбор.");
            }
        }
        System.out.println("Выход.");
    }
}

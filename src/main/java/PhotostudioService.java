

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PhotostudioService {
    private final Map<Integer, Photographer> photographers = new HashMap<>();
    private final Map<Integer, Client> clients = new HashMap<>();
    private final Map<Integer, PhotoSession> sessions = new HashMap<>();

    private int nextPhotographerId = 1;
    private int nextClientId = 1;
    private int nextSessionId = 1;

    // Create sample data to demonstrate
    public void initSampleData() {
        Photographer p1 = addPhotographer("Ivan Petrov", 5);
        Photographer p2 = addPhotographer("Anna Smirnova", 10);
        Photographer p3 = addPhotographer("Sergey Ivanov", 2);

        Client c1 = addClient("Olga Kuznetsova", "+7-900-111-22-33");
        Client c2 = addClient("Dmitry Sokolov", "+7-900-222-33-44");

        addSession(c1, p1, LocalDate.of(2025, 5, 10), "Wedding portraits");
        addSession(c2, p2, LocalDate.of(2025, 6, 1), "Family shoot");
        addSession(c1, p3, LocalDate.of(2025, 7, 20), "Graduation photo");
    }

    // CRUD-ish helpers
    public Photographer addPhotographer(String name, int years) {
        Photographer p = new Photographer(nextPhotographerId++, name, years);
        photographers.put(p.getId(), p);
        return p;
    }

    public Client addClient(String name, String phone) {
        Client c = new Client(nextClientId++, name, phone);
        clients.put(c.getId(), c);
        return c;
    }

    public PhotoSession addSession(Client client, Photographer photographer, LocalDate date, String desc) {
        PhotoSession s = new PhotoSession(nextSessionId++, client, photographer, date, desc);
        sessions.put(s.getId(), s);
        return s;
    }

    public List<PhotoSession> findSessionsByClientName(String clientName) {
        String lower = clientName.trim().toLowerCase();
        return sessions.values().stream()
                .filter(s -> s.getClient().getName().toLowerCase().contains(lower))
                .sorted(Comparator.comparing(PhotoSession::getDate))
                .collect(Collectors.toList());
    }

    public List<Photographer> filterPhotographersByExperience(int minYears) {
        return photographers.values().stream()
                .filter(p -> p.getYearsOfExperience() >= minYears)
                .sorted(Comparator.comparing(Photographer::getYearsOfExperience).reversed())
                .collect(Collectors.toList());
    }

    // Get all (for UI)
    public Collection<Photographer> getAllPhotographers() { return photographers.values(); }
    public Collection<Client> getAllClients() { return clients.values(); }
    public Collection<PhotoSession> getAllSessions() { return sessions.values(); }
}

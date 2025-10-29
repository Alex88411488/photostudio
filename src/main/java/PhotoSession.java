

import java.time.LocalDate;

public class PhotoSession {
    private final int id;
    private final Client client;
    private final Photographer photographer;
    private final LocalDate date;
    private final String description;

    public PhotoSession(int id, Client client, Photographer photographer, LocalDate date, String description) {
        this.id = id;
        this.client = client;
        this.photographer = photographer;
        this.date = date;
        this.description = description;
    }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public Photographer getPhotographer() { return photographer; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("PhotoSession{id=%d, client=%s, photographer=%s, date=%s, desc='%s'}",
                id, client.getName(), photographer.getName(), date, description);
    }
}



public class Photographer {
    private final int id;
    private String name;
    private int yearsOfExperience;

    public Photographer(int id, String name, int yearsOfExperience) {
        this.id = id;
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getYearsOfExperience() { return yearsOfExperience; }

    public void setName(String name) { this.name = name; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    @Override
    public String toString() {
        return String.format("Photographer{id=%d, name='%s', years=%d}", id, name, yearsOfExperience);
    }
}

public class Player {

    // member variables
    protected String name;
    protected int score;

    // member variables
    public Player() {
        name = "Player";
        score = 0;
    }

    public Player (String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Player (Player other) {
        this.name = other.name;
        this.score = other.score;
    }

    /**
     * getters and setters
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player createClone() {
        return new Player(this.name, this.score);
    }

    /**
     * equals override
     */
    @Override
    public boolean equals(Object o) {
        Player p;
        p = (Player) o;
        return this.name.equals(p.name);
    }
}

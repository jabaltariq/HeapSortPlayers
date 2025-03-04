public interface PriorityQueue {
    void add(Player a);
    Player getHighestScorePlayer();
    void clear();
    int getSize();
    boolean isEmpty();
}

package ballblast.model.data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

/**
 * Keeps track of users score.
 */
public class Leaderboard implements Serializable {

    // To serialize the object into a bytes stream univocally.
    private static final long serialVersionUID = 1104832498325739739L;
    private static final int MAX_SCORES = 10;
    private ImmutableList<Record> recordList;
    private static final Comparator<Record> COMPARATOR = (o1, o2) -> {
        return o2.getScore() - o1.getScore();
    };
    // Don't serialize this field.
    private transient Optional<Integer> lastIndex;

    /**
     * Creates the Leaderboard.
     */
    public Leaderboard() {
        this.recordList = ImmutableList.of();
        this.lastIndex = Optional.empty();
    }

    /**
     * Adds the {@link Record} if is greater than other on top 10 records.
     * @param name
     *          the name of the user who submitted the record.
     * @param score
     *          the score reached at the end of the game session.
     * @param time
     *          the time of the last game session.
     */
    public void addRecord(final String name, final int score, final int time) {
        if (this.isRecord(score) && this.recordList.size() < MAX_SCORES) {
            Record rec = new Record(name, time, score);
            this.recordList = ImmutableList.<Record>builder()
                                           .addAll(recordList)
                                           .add(rec)
                                           .build();
            this.recordList.stream().sorted(COMPARATOR).limit(MAX_SCORES).collect(ImmutableList.toImmutableList());
            this.lastIndex = Optional.of(recordList.indexOf(rec));
        }
    }

    /**
     * Returns an optional for the index of the last {@link Record} added.
     * @return
     *          The index of the last record, empty if the last record is lower than the top 10.
     */
    public Optional<Integer> getLastIndex() {
        return this.lastIndex;
    }

    /**
     * Returns a stream of {@link Record}s.
     * @return
     *          a strea of records.
     */
    public Stream<Record> getRecords() {
        return this.recordList.stream();
    }

    /**
     * Verify if a record is an high score.
     * @param score
     *          the score to verify.
     * @return
     *          true if the score is the high score.
     */
    public boolean isRecord(final int score) {
        return score > this.recordList.stream().max(COMPARATOR).get().getScore();
    }

    /**
     * Getter for the highscore.
     * @return
     *          the score of the record with the highest score.
     */
    public Integer getHighScore() {
        //Collections.sort(recordList, (a, b) -> b.compareTo(a));
        return Integer.valueOf(this.recordList.stream().min(COMPARATOR).get().getScore());
    }

}

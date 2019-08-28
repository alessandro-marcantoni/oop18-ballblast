package ballblast.model.data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

/**
 * Keeps track of users score.
 */
public class Leaderboard implements Serializable {

    // To serialize the object into a bytes stream univocally.
    private static final long serialVersionUID = 1104832498325739739L;
    private static final int MAX_SCORES = 10;
    private ImmutableList<RecordData> recordList;
    private static final Comparator<RecordData> COMPARATOR = (o1, o2) -> {
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
     * Adds the {@link RecordData} if is greater than other on top 10 records.
     * @param name
     *          the name of the user who submitted the record.
     * @param score
     *          the score reached at the end of the game session.
     */
    public void addRecord(final String name, final int score) {
        if (this.isRecord(score) || this.recordList.size() <= MAX_SCORES) {
            final RecordData rec = new RecordData(name, score);
            this.recordList = ImmutableList.<RecordData>builder()
                                           .addAll(recordList)
                                           .add(rec)
                                           .build();
            this.recordList.stream().sorted(COMPARATOR).limit(MAX_SCORES).collect(ImmutableList.toImmutableList());
            this.lastIndex = Optional.of(recordList.indexOf(rec));
        }
    }

    /**
     * Returns an optional for the index of the last {@link RecordData} added.
     * @return
     *          The index of the last record, empty if the last record is lower than the top 10.
     */
    public Optional<Integer> getLastIndex() {
        return this.lastIndex;
    }

    /**
     * Returns a stream of {@link RecordData}s.
     * @return
     *          a strea of records.
     */
    public Stream<RecordData> getRecords() {
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
        return score > this.getRecords().max(COMPARATOR).get().getScore();
    }

    /**
     * Getter for the highscore.
     * @return
     *          the score of the record with the highest score.
     */
    public Integer getHighScore() {
        return Integer.valueOf(this.getRecords().min(COMPARATOR).get().getScore());
    }

    /**
     * Getter for the leaderboard.
     * @return
     *          the map of the leaderboard.
     */
    public Map<String, Integer> getLeaderboard() {
        this.recordList.stream().sorted(COMPARATOR).limit(MAX_SCORES).collect(ImmutableList.toImmutableList());
        return this.getRecords().collect(Collectors.toMap(RecordData::getUserName, RecordData::getScore));
    }

}

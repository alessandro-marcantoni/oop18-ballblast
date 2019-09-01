package ballblast.model.data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Keeps track of users {@link RecordData}.
 */
public class Leaderboard {

    private static final int MAX_SCORES = 10;
    private static final Comparator<RecordData> COMPARATOR = (o1, o2) -> {
        return o2.getScore() - o1.getScore();
    };

    private List<RecordData> recordList;

    /**
     * Empty constructor to serialize the object in a xml file.
     */
//    public Leaderboard() {
//    }

    /**
     * Adds a {@link RecordData} if is greater than other on top 10 records.
     * @param name
     *          the name of the user who submitted the record.
     * @param score
     *          the score reached at the end of the game session.
     */
    public void addRecord(final String name, final int score) {
        if (this.recordList.size() < MAX_SCORES || this.isRecord(score)) {
            final RecordData rec = new RecordData();
            rec.setName(name);
            rec.setScore(score);
            this.recordList.add(rec);
            if (this.recordList.size() > MAX_SCORES) {
                this.recordList.remove(this.recordList.stream().max(COMPARATOR).get());
            }
            this.recordList.sort(COMPARATOR);
        }
    }

    private Stream<RecordData> getRecords() {
        return this.recordList.stream();
    }

    /**
     * Checks if a record can be saved in the leaderboard.
     * @param score
     *          the score to verify.
     * @return
     *          true if the score is an high score.
     */
    public boolean isRecord(final int score) {
        return score > this.getRecords().max(COMPARATOR).get().getScore();
    }

    /**
     * Getter for the highscore.
     * @return
     *          an {@link Optional} {@link RecordData} with the highest score, empty if the record list has no records saved.
     */
    public Optional<RecordData> getHighScore() {
        if (this.recordList.isEmpty()) {
            return Optional.empty();
        } else {
            return this.getRecords().min(COMPARATOR);
        }
    }

    /**
     * Getter for the leaderboard under a map format.
     * @return
     *          the map of the {@link Leaderboard}.
     */
    public Map<Integer, String> getLeaderboard() {
        final Map<Integer, String> map = this.getRecords().collect(Collectors.toMap(RecordData::getScore, RecordData::getName));
        final Map<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {

            @Override
            public int compare(final Integer o1, final Integer o2) {
                return o2.compareTo(o1);
            }

        });
        treeMap.putAll(map);
        return treeMap;
    }

    /**
     * Getter of the record list.
     * @return
     *          the {@link List} of {@link RecordData}.
     */
    public List<RecordData> getRecordList() {
        return recordList;
    }

    /**
     * Setter for the record list.
     * @param records
     *          the {@link List} of {@link RecordData} to set.
     */
    public void setRecordList(final List<RecordData> records) {
        this.recordList = records;
    }

}

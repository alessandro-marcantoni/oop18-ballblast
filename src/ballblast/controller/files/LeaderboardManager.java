package ballblast.controller.files;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import com.google.common.collect.Lists;

import ballblast.controller.DirectoryManager;
import ballblast.model.data.Leaderboard;

/**
 * The manager for the I/O of the leaderboard data.
 */
public class LeaderboardManager {

    /**
     * Loads the survival leaderboard.
     * @return
     *          the survival leaderboard, if present.
     */
    public final Optional<Leaderboard> loadSurvivalLeaderboard() {
        return this.load(DirectoryManager.SURVIVAL_FILE);
    }

    /**
     * Saves leaderboard data in the xml file.
     * @param lb
     *          the leaderboard to save data.
     * @return
     *          true if save operation done successfully, false otherwise.
     */
    public final boolean saveSurvivalLeaderboard(final Leaderboard lb) {
        return this.save(lb, DirectoryManager.SURVIVAL_FILE);
    }

    private boolean save(final Leaderboard lb, final String filePath) {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath));
                XMLEncoder enc = new XMLEncoder(fos)) {
            enc.writeObject(lb);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Optional<Leaderboard> load(final String path) {
        if (!Files.exists(Paths.get(path))) {
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setRecordList(Lists.newArrayList());
            return Optional.of(leaderboard);
        }
        try (FileInputStream fis = new FileInputStream(new File(path));
                XMLDecoder dec = new XMLDecoder(fis)) {
            return Optional.of((Leaderboard) dec.readObject());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

package console;

import core.ActionFileImpl;
import core.FileStatistic;
import core.StoreLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AppFileResearch {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppFileResearch.class);

    public static void main(String[] args) {

        ActionFileImpl actionFile = new ActionFileImpl();
        List<StoreLine> storeLineArrayList = new ArrayList<StoreLine>();
        List<FileStatistic> fileStatistics = new ArrayList<>();

        if (args.length == 1) {
            actionFile.readTxtFile(args[0], storeLineArrayList);
            actionFile.getStatisticLine(storeLineArrayList, fileStatistics);
            actionFile.getStatisticFile(storeLineArrayList, fileStatistics);
            actionFile.loadStoreLineToDb(storeLineArrayList);
            actionFile.loadFileStatisticToDb(fileStatistics);
        } else {
            LOGGER.info("You should fill a file name in a command line!");
            System.exit(0);
        }

    }
}

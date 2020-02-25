package core;

import java.util.List;

public interface ActionFile {
    void readTxtFile(String fileName, List<StoreLine> storeLineList);
    void getStatisticLine(List<StoreLine> storeLineList, List<FileStatistic> fileStatisticList);
    void getStatisticFile(List<StoreLine> storeLineList, List<FileStatistic> fileStatisticList);
    void loadToStoreLine(String lineName, List<StoreLine> storeLineList);
    FileStatistic loadToFileStatistic(String maxWord, String minWord, int lengthLine, Long avrWord, List<String> duplicateName);
    void loadStoreLineToDb(List<StoreLine> storeLineList);
    void loadFileStatisticToDb(List<FileStatistic> fileStatisticList);
}

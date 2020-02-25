package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConnectionJdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ActionFileImpl implements ActionFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionFileImpl.class);
    private static final String INSERT_LINES = "insert into lines_file(line_name) values (?)";
    private static final String INSERT_STATISTIC = "insert into files(longest_word, shortest_word,duplicate, lentgh_line, length_average_word) values (?,?,?,?,?)";

    @Override
    public void readTxtFile(String fileName, List<StoreLine> storeLineList) {
        LOGGER.info("Read file");
        try {
            if (!Files.isRegularFile(Paths.get(fileName))) {
                throw new Exception("File is not exist!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try (BufferedReader reader= new BufferedReader(new FileReader(fileName));){
            while (reader.ready()) {
                String lineFile = reader.readLine();
                loadToStoreLine(lineFile, storeLineList);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void loadStoreLineToDb(List<StoreLine> storeLineList){
        LOGGER.info("Load store line to db");
        try (Connection conn = ConnectionJdbc.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_LINES)) {
            for (StoreLine stringVar : storeLineList) {
                preparedStatement.setString(1, stringVar.getLineFile());
                preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | IOException ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public void loadFileStatisticToDb(List<FileStatistic> fileStatisticList){
        LOGGER.info("Load file statistic to db");
        try (Connection conn = ConnectionJdbc.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_STATISTIC)) {
            for (FileStatistic stringVar : fileStatisticList) {
                preparedStatement.setString(1, stringVar.getLongestWord());
                preparedStatement.setString(2, stringVar.getShortestWord());
                preparedStatement.setString(3, stringVar.getDuplicationOfWords().toString().substring(1,stringVar.getDuplicationOfWords().toString().length()-1));
                preparedStatement.setInt(4, stringVar.getLineLength());
                preparedStatement.setLong(5, stringVar.getAverageWordLength());
                preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void getStatisticLine(List<StoreLine> storeLineList, List<FileStatistic> fileStatisticList) {
        LOGGER.info("Statistic of line");
        for (StoreLine lineList: storeLineList) {
            String lineFile = lineList.getLineFile();
            String[] wordLine = lineFile.split(" ");
            Optional<String> minWord = Arrays.stream(wordLine)
                    .min(Comparator.comparing(String::length));
            Optional<String> maxWord = Arrays.stream(wordLine)
                    .max(Comparator.comparing(String::length));
            Long kolWords = Arrays.stream(wordLine)
                    .count();
            Integer lengthWords = Arrays.stream(wordLine)
                    .mapToInt(String::length)
                    .sum();
            Set<String> duplicatedRemovedSet = new HashSet<>();
            Set<String> duplicate = Arrays.stream(wordLine)
                    .filter(n -> !duplicatedRemovedSet.add(n))
                    .collect(Collectors.toSet());
            Long avrWord = lengthWords / kolWords;
            List<String> duplicateName = new ArrayList<>(duplicate);
            int lengthLine = lineFile.length();
            FileStatistic  fileStatistic = loadToFileStatistic(maxWord.get(), minWord.get(), lengthLine, avrWord, duplicateName);
            fileStatisticList.add(fileStatistic);
        }
    }

    @Override
    public void getStatisticFile(List<StoreLine> storeLineList, List<FileStatistic> fileStatisticList){
        LOGGER.info("Statistic of file");
        Optional<String> minWord = storeLineList.stream()
                .flatMap(line -> line.getStringList().stream())
                .min(Comparator.comparing(String::length));
        Optional<String> maxWord = storeLineList.stream()
                .flatMap(line -> line.getStringList().stream())
                .max(Comparator.comparing(String::length));
        Long kolWords = storeLineList.stream()
                .flatMap(line -> line.getStringList().stream())
                .count();
        Integer lengthWords = storeLineList.stream()
                .flatMap(line -> line.getStringList().stream())
                .mapToInt(String::length)
                .sum();
        Set<String> duplicatedRemovedSet = new HashSet<>();
        Set<String> duplicate = storeLineList.stream()
                .flatMap(line -> line.getStringList().stream())
                .filter(line -> !duplicatedRemovedSet.add(line))
                .collect(Collectors.toSet());
        Long avrWord = lengthWords / kolWords;
        List<String> duplicateName = new ArrayList<>(duplicate);
        Integer lengthLine = storeLineList.stream()
                .map(StoreLine::getLineFile)
                .mapToInt(String::length)
                .sum();
        FileStatistic  fileStatistic = loadToFileStatistic(maxWord.get(), minWord.get(), lengthLine, avrWord, duplicateName);
        fileStatisticList.add(fileStatistic);
    }

    @Override
    public FileStatistic loadToFileStatistic(String maxWord, String minWord, int lengthLine, Long avrWord, List<String> duplicateName){
        FileStatistic fileStatistic = new FileStatistic();
        fileStatistic.setLongestWord(maxWord);
        fileStatistic.setAverageWordLength(avrWord);
        fileStatistic.setShortestWord(minWord);
        fileStatistic.setLineLength(lengthLine);
        fileStatistic.setDuplicationOfWords(duplicateName);
        return fileStatistic;
    }

    @Override
    public void loadToStoreLine(String lineFile, List<StoreLine> storeLineList) {
        LOGGER.info("Store line");
        StoreLine storeLine = new StoreLine(lineFile);
        storeLineList.add(storeLine);
    }
}

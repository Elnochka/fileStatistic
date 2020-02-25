package core;

import java.util.List;
import java.util.Objects;

public class FileStatistic {
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private Long averageWordLength;
    private List<String> duplicationOfWords;

    public FileStatistic() {
    }

    public FileStatistic(String longestWord, String shortestWord, int lineLength, Long averageWordLength, List<String> duplicationOfWords) {
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.lineLength = lineLength;
        this.averageWordLength = averageWordLength;
        this.duplicationOfWords = duplicationOfWords;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public Long getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(Long averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public List<String> getDuplicationOfWords() {
        return duplicationOfWords;
    }

    public void setDuplicationOfWords(List<String> duplicationOfWords) {
        this.duplicationOfWords = duplicationOfWords;
    }

    @Override
    public String toString() {
        return "FileStatistic{" +
                "longestWord='" + longestWord + '\'' +
                ", shortestWord='" + shortestWord + '\'' +
                ", lineLength=" + lineLength +
                ", averageWordLength=" + averageWordLength +
                ", duplicationOfWords=" + duplicationOfWords +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStatistic that = (FileStatistic) o;
        return lineLength == that.lineLength &&
                Objects.equals(longestWord, that.longestWord) &&
                Objects.equals(shortestWord, that.shortestWord) &&
                Objects.equals(averageWordLength, that.averageWordLength) &&
                Objects.equals(duplicationOfWords, that.duplicationOfWords);
    }

    @Override
    public int hashCode() {

        return Objects.hash(longestWord, shortestWord, lineLength, averageWordLength, duplicationOfWords);
    }
}

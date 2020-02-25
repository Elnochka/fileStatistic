package core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StoreLine {
    private String lineFile;
    private List<String> stringList;

    public StoreLine() {
    }

    public StoreLine(String lineFile) {
        this.lineFile = lineFile;
        stringList = Arrays.asList(lineFile.split(" "));

    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public String getLineFile() {
        return lineFile;
    }

    public void setLineFile(String lineFile) {
        this.lineFile = lineFile;
        stringList = Arrays.asList(lineFile.split(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreLine storeLine = (StoreLine) o;
        return Objects.equals(lineFile, storeLine.lineFile) &&
                Objects.equals(stringList, storeLine.stringList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lineFile, stringList);
    }

    @Override
    public String toString() {
        return "StoreLine{" +
                "lineFile='" + lineFile + '\'' +
                ", stringList=" + stringList +
                '}';
    }
}

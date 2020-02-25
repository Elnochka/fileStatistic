package core;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileStatisticTest {
    @Test
    public void testCreateFileStatistic() {
        //GIVEN
        List<String> duplicateList = new ArrayList<>();
        duplicateList.add("mirror");
        FileStatistic fileStatistic = new FileStatistic();
        fileStatistic.setAverageWordLength(20L);
        fileStatistic.setDuplicationOfWords(duplicateList);
        fileStatistic.setLineLength(23);
        fileStatistic.setLongestWord("hello");
        fileStatistic.setShortestWord("oh");
        //WHEN
        //THEN
        Assert.assertNotNull(fileStatistic);
        Assert.assertEquals(fileStatistic, new FileStatistic("hello", "oh", 23, 20L, duplicateList));
    }
}

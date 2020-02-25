package core;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ActionFileImplTest {

    ActionFileImpl actionFile = Mockito.spy(ActionFileImpl.class);
    List<StoreLine> storeLines = new ArrayList<>();
    List<FileStatistic> fileStatistics = new ArrayList<>();

    @Test
    public void testLoadToStoreLine() {
        //GIVEN
        String stringFromFile = "aaaa bbbb cc dddddd aaaa cc";
        String stringFromFileMore = "aaaaa bbbb ccc ddddddd daaaa cc";
        StoreLine storeLine = new StoreLine(stringFromFile);
        StoreLine storeLineMore = new StoreLine(stringFromFileMore);
        //WHEN
        storeLines.add(storeLine);
        storeLines.add(storeLineMore);
        //THEN
        Assert.assertNotNull(storeLines);
        Assert.assertEquals(storeLines.get(0).getLineFile(), stringFromFile);
        Assert.assertEquals(storeLines.get(1).getLineFile(), stringFromFileMore);
    }

    @Test
    public void testGetStatisticLine(){
        //GIVEN
        String stringFromFile = "aaaa bbbb cc dddddd aaaa cc";
        StoreLine storeLine = new StoreLine(stringFromFile);
        storeLines.add(storeLine);
        List<String> duplicates = new ArrayList<>();
        duplicates.add("cc");
        duplicates.add("aaaa");
        //WHEN
        actionFile.getStatisticLine(storeLines, fileStatistics);
        //THEN
        Assert.assertNotNull(fileStatistics);
        Assert.assertEquals(fileStatistics.get(0).getLongestWord(), "dddddd");
        Assert.assertEquals(fileStatistics.get(0).getShortestWord(), "cc");
        Assert.assertEquals(fileStatistics.get(0).getLineLength(), 27);
        Assert.assertEquals(fileStatistics.get(0).getAverageWordLength().longValue(), 3L);
        Assert.assertEquals(fileStatistics.get(0).getDuplicationOfWords(), duplicates);
        Mockito.verify(actionFile, Mockito.times(1)).getStatisticLine(storeLines, fileStatistics);
    }

    @Test
    public void testGetStatisticFile(){
        //GIVEN
        String stringFromFile = "aaaa bbbb cc dddddd aaaa cc";
        String stringFromFileMore = "aaaaa bbbb ccc ddddddd daaaa cc";
        StoreLine storeLine = new StoreLine(stringFromFile);
        StoreLine storeLineMore = new StoreLine(stringFromFileMore);
        storeLines.add(storeLine);
        storeLines.add(storeLineMore);
        List<String> duplicates = new ArrayList<>();
        duplicates.add("cc");
        duplicates.add("aaaa");
        duplicates.add("bbbb");
        //WHEN
        actionFile.getStatisticFile(storeLines, fileStatistics);
        //THEN
        Assert.assertNotNull(fileStatistics);
        Assert.assertEquals(fileStatistics.get(0).getLongestWord(), "ddddddd");
        Assert.assertEquals(fileStatistics.get(0).getShortestWord(), "cc");
        Assert.assertEquals(fileStatistics.get(0).getLineLength(), 58);
        Assert.assertEquals(fileStatistics.get(0).getAverageWordLength().longValue(), 4L);
        Assert.assertEquals(fileStatistics.get(0).getDuplicationOfWords(), duplicates);
        Mockito.verify(actionFile, Mockito.times(1)).getStatisticFile(storeLines, fileStatistics);
    }

}

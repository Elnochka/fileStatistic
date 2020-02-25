package core;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class StoreLineTest {
    @Test
    public void testCreateStoreLine() {
        //GIVEN
        StoreLine storeLine = new StoreLine();
        storeLine.setLineFile("store line");
        //WHEN
        //THEN
        Assert.assertNotNull(storeLine);
        Assert.assertEquals(storeLine, new StoreLine("store line"));
    }
}

package dhbw.online.bookly;

import dhbw.online.bookly.dto.FriendshipBook;
import org.junit.Assert;
import org.junit.Test;

public class FriendshipBookTest {

    @Test
    public void testPages() {
        FriendshipBook friendshipBook = new FriendshipBook();
        Assert.assertNotNull(friendshipBook.getPages());
        friendshipBook.setPages(null);
        Assert.assertNotNull(friendshipBook.getPages());
    }
}

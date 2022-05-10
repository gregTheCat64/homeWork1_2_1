package hw2_2

import org.junit.Assert.*

class WallServiceTest {

    @org.junit.Test
    fun addChecking() {
        val comments = Comments()
        val copyright = Copyright()
        val likes = Likes()
        val reposts = Reposts()
        val views = Views()

        val placeHolder = PlaceHolder()
        val donut = Donut(placeHolder = placeHolder)

        val post = Post(comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)
        WallService.add(post)

        val result = post.id

        assertTrue(result != 0)
    }

    @org.junit.Test
    fun addAttachment(){
        val attachment = PhotoAttachment()
        WallService.addAttachment(attachment)

        val result = attachment.id
        println(attachment.id)
        assertTrue(result !=0)
    }

    @org.junit.Test
    fun updateExistingId() {
        val comments = Comments()
        val copyright = Copyright()
        val likes = Likes()
        val reposts = Reposts()
        val views = Views()

        val placeHolder = PlaceHolder()
        val donut = Donut(placeHolder = placeHolder)

        val post = Post(comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)
        val post2 =Post(isFavourite = true, comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)

        //меняем избранное предыдущего поста на Фолс:
        val postUpdated =Post(id = 2, isFavourite = false, comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)


        WallService.add(post)
        WallService.add(post2)

        val result =WallService.update(postUpdated)

        assertEquals(true, result)


    }
    @org.junit.Test
    fun updateNotExistingId() {
        val comments = Comments()
        val copyright = Copyright()
        val likes = Likes()
        val reposts = Reposts()
        val views = Views()

        val placeHolder = PlaceHolder()
        val donut = Donut(placeHolder = placeHolder)

        val post = Post(comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)
        val post2 =Post(isFavourite = true, comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)

        //меняем избранное предыдущего поста на Фолс:
        val postUpdated =Post(id = 0, isFavourite = false, comments = comments, copyright = copyright, likes = likes, reposts = reposts, views = views)


        WallService.add(post)
        WallService.add(post2)

        val result =WallService.update(postUpdated)

        assertEquals(false, result)


    }
}
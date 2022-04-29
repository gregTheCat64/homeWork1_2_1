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

        assertEquals(1,post.id)
    }

    @org.junit.Test
    fun update() {
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
}
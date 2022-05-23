package hw2_2

import org.junit.Assert.*
import org.junit.Test

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

    @org.junit.Test
    fun createRightComment(){
        val comment = Comment(text = "какой то комментарий")
        val  post = Post()
        WallService.add(post)
        WallService.createComment(comment, post)

        val result = post.comments.count
        println("комментариев в посте: $result")
        assertTrue(result!=0)
    }


    @Test(expected = WallService.PostNotFoundException::class)
    fun shouldThrow() {
        // здесь код с вызовом функции, которая должна выкинуть PostNotFoundException
        val comment = Comment(text = "какой то комментарий")
        val  post = Post(id = 0)
        WallService.createComment(comment, post)
    }

    @org.junit.Test
    fun createRightReport(){
        val comment = Comment(text = "какой то комментарий")
        val  post = Post()
        WallService.add(post)
        WallService.createComment(comment, post)

        val  result =  WallService.createReport(1, 5)
        println("Жалоба добавлена успешно")
        assertEquals(result, true)
    }

    @org.junit.Test
    fun createReportWithWrongCommentId(){
        val comment = Comment(text = "какой то комментарий")
        val  post = Post()
        WallService.add(post)

       // WallService.createComment(comment, post)
        //комментарий создан но не добавлен
        //создаем репорт на несуществующий комментарий

        val  result =  WallService.createReport(3, 5)
        assertEquals(result, false)
    }

    @org.junit.Test
    fun createReportWithWrongReason(){
        val comment = Comment(text = "какой то комментарий")
        val  post = Post()
        WallService.add(post)
        //  WallService.createComment(comment, post)


        val  result =  WallService.createReport(1, 9)
        assertEquals(result, false)
    }
}






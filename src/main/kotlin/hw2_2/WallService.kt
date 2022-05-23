package hw2_2

object WallService {
    private var ownerId: Int = 0
    var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<Comment>()
    private var attachments: Array<Attachment> = emptyArray()

    private var id: Int = 0
    private var comId = 0
    private var idAttachment = 0

    fun addAttachment(attachment: Attachment): Attachment {
        idAttachment++
        attachment.id = idAttachment
        println("Присвоен айди аттачмента: ${attachment.id}")
        attachments += attachment
        return attachments.last()
    }

    fun add(post: Post): Post {
        id++
        post.id = id
        println("присвоен айди: " + post.id)
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, oldPost) in posts.withIndex()) {
            if (post.id == oldPost.id) {
                posts[index] = post.copy(id = oldPost.id, date = oldPost.date)

                //Проверяю, есть ли изменения:
                println(posts[index])
                //возвращаю тру:
                return true
            }
        }
        return false
    }

    class PostNotFoundException(message: String) : RuntimeException(message)

    fun createComment(comment: Comment, postToComment: Post): Comment {
        for (post: Post in posts) {
            if (postToComment.id == post.id) {
                comId++
                comment.id = comId
                comments += comment
                post.comments.count++

                return comments.last()
            }
        }
        throw PostNotFoundException("нет поста с id ${postToComment.id}")
    }



    fun createReport(commentId: Int, reason: Int): Boolean {
        for (comment: Comment in comments) {
            if (commentId == comment.id && reason in 0..8) {
                reports += comment
                return true
            }
            else println("Комментарий не найден либо неверный код жалобы")
        }
       return false
    }


}
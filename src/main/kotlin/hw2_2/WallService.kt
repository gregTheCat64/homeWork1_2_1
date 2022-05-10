package hw2_2

object WallService {
    private var posts = emptyArray<Post>()
    private var id: Int = 0
    private var attachments:Array<Attachment> = emptyArray()
    private var idAttachment = 0

    fun addAttachment(attachment: Attachment): Attachment{
        idAttachment++
        attachment.id = idAttachment
        println("Присвоен айди аттачмента: ${attachment.id}")
        attachments += attachment
        return attachments.last()
    }

    fun add(post: Post): Post {
        id++
        post.id = id
        println("присвоен айди: "+ post.id)
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



}
data class ModelIncident(
    override val id: Int,
    override val title: String,
    override val desc: String,
    override val active: Boolean,
    override val date: String,
    override val imgURL: String?,
    override val language: String,
    override val slug: String,
    val tags: String?) : ModelInterface{

    override fun toString(): String {
        return "\t ID: $id \n" +
                "\t Title: $title \n" +
                "\t Description: $desc \n" +
                "\t Active: $active \n" +
                "\t Date: $date \n" +
                "\t Img Url: $imgURL \n" +
                "\t Language: $language \n" +
                "\t Slug: $slug \n" +
                "\t Tags: $tags \n \n"
    }

}
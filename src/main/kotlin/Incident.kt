data class Incident(
    override val totalCount: Int,
    override val count: Int,
    val models: List<ModelIncident>,
    override val page: Int,
    override val pageCount: Int) : ApiInterface {

    override fun toString(): String {
        return "Total Count of Incidents: $totalCount \n" +
                "Total Count of Incidents in This Page: $count \n" +
                "Models: \n $models \n" +
                "You are in page: $page \n" +
                "Total Page Count: $pageCount \n"
    }

}
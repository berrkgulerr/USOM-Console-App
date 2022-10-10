import com.google.gson.Gson
import java.lang.NumberFormatException
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.exitProcess

fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

fun formData(data: Map<String, String>): HttpRequest.BodyPublisher? {
    val res = data.map {(k, v) -> "${(k.utf8())}=${v.utf8()}"}
        .joinToString("&")
    return HttpRequest.BodyPublishers.ofString(res)
}

fun mainMenu(client: HttpClient, gson: Gson){
    while (true){
        println("Choose what you want to do:")
        println("1- Get Incidents")
        println("2- Get Announcements")
        println("3- Create Email")
        println("4- Exit")
        when (
           try {
               readLine()?.toInt()
           } catch (_: NumberFormatException){
               println("Please give a valid number!")
               mainMenu(client, gson)
           }
        ) {
            1 -> incidentRequest(client, gson, 1)
            2 -> announcementRequest(client, gson)
            3 -> createEmail(client, gson)
            4 -> exitProcess(1)
            else ->{
                println("Please give a valid number!")
                mainMenu(client, gson)
            }
        }
    }
}

fun incidentRequest(client:HttpClient, gson: Gson, pageNum: Int){
    val requestIncident = HttpRequest.newBuilder()
        .uri(URI.create("https://www.usom.gov.tr/api/incident/index?page=$pageNum"))
        .build()
    val responseIncident = client.send(requestIncident, HttpResponse.BodyHandlers.ofString())
    val incident: Incident = gson.fromJson(responseIncident.body(), Incident::class.java)
    println(incident)
    incident.changePage(client, gson)
}

fun announcementRequest(client:HttpClient, gson: Gson){
    val requestAnnouncement= HttpRequest.newBuilder()
        .uri(URI.create("https://www.usom.gov.tr/api/announcement/index"))
        .build()
    val responseAnnouncement = client.send(requestAnnouncement, HttpResponse.BodyHandlers.ofString())
    val announcement: Announcement = gson.fromJson(responseAnnouncement.body(), Announcement::class.java)
    println(announcement)
    announcement.changePage(client, gson)
}

fun createEmail(client: HttpClient, gson: Gson){
    println("Please write your e-mail")
    val email = readln()
    val values = mapOf("emailaddr" to email)
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.usom.gov.tr/api/emails/create"))
        .POST(formData(values))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    if (response.statusCode() == 200){
        println("You have created your email successfully")
        println("Your e-mail: $email")
    }
    else {
        println("Please give a valid e-mail!")
        createEmail(client,gson)
    }
}

fun main() {
    val gson = Gson()
    val client = HttpClient.newBuilder().build()
    mainMenu(client, gson)
}
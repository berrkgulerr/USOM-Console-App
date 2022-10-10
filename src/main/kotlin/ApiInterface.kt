import com.google.gson.Gson
import java.lang.NumberFormatException
import java.net.http.HttpClient

interface ApiInterface {
    val totalCount: Int
    val count: Int
    val page: Int
    val pageCount: Int

    fun changePage(client: HttpClient, gson: Gson){
        if (pageCount > 1){
            if (page > 0) println("To see the previous page press 1")
            if (page < pageCount-1) println("To see the next page press 2")
            println("To return to main menu press 0")
            when(try {
                readLine()?.toInt()
            } catch (_: NumberFormatException){
                println("Please give a valid number!")
                changePage(client, gson)
            }){
                1->incidentRequest(client,gson,page)
                2->incidentRequest(client,gson,page+2)
                0->mainMenu(client, gson)
                else->{
                    println("Give valid number!")
                    changePage(client,gson)
                }
            }
        }
        println("To return to main menu press 0")
        when(try {
            readLine()?.toInt()
        } catch (_: NumberFormatException){
            println("Please give a valid number!")
            changePage(client,gson)
        }){
            0->mainMenu(client, gson)
            else->changePage(client, gson)
        }
    }
}
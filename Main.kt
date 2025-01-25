import spark.ModelAndView
import spark.Request
import spark.Spark
import spark.template.handlebars.HandlebarsTemplateEngine
import java.sql.Connection
import java.sql.DriverManager

val connection: Connection
    get() = DriverManager.getConnection("jdbc:mysql://localhost/gamedb", "root", "root")

fun main() {

    Spark.staticFiles.location("/static")

    fun get(path: String, route: SecureTemplateViewRoute) = Spark.get(path, route, HandlebarsTemplateEngine())
    fun post(path: String, route: SecureTemplateViewRoute) = Spark.post(path, route, HandlebarsTemplateEngine())


    get("/", ::loginPage)
    post("/", ::tryLogin)
    post("/saveGame", ::saveGame)
    get("/logout", ::logoutAndRestart)
}


@Suppress("UNUSED_PARAMETER")
fun loginPage(rq: Request): ModelAndView {
    val map = mapOf("hasError" to false)
    return ModelAndView(map, "Login.hbs")
}

fun tryLogin(rq: Request): ModelAndView {
    val username = rq.queryParams("username")
    val password = rq.queryParams("password")

    val pInfo = PlayerInfo.findPlayerInfo(username)
    if (pInfo != null && pInfo.userPw == password) {
        val session = rq.session()
        session.attribute("username", pInfo.username)
        val scores = Score.orderedTopScores(pInfo)
        val map = mapOf(
            "name" to pInfo.fullName,
            "scores" to scores
        )
        return ModelAndView(map, "Game.hbs")
    }

    // user not found or wrong password
    val map = mapOf("hasError" to true)
    return ModelAndView(map, "Login.hbs")
}

fun saveGame(rq: Request): ModelAndView {
    val appleScore = rq.queryParams("apples")
    val timeScore = rq.queryParams("times")

    val username = rq.session().attribute<String>("username")
    val pInfo = PlayerInfo.findPlayerInfo(username) ?: return logoutAndRestart(rq)  // Unexpected status!

    Score.saveScore(appleScore, timeScore, pInfo)
    val scores = Score.orderedTopScores(pInfo)
    val map = mapOf(
        "name" to pInfo.fullName,
        "scores" to scores
    )
    return ModelAndView(map, "Game.hbs")
}

fun logoutAndRestart(rq: Request): ModelAndView {
    rq.session().invalidate()
    val map = mapOf("hasError" to false)
    return ModelAndView(map, "Login.hbs")
}

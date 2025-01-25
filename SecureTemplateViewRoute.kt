import spark.ModelAndView
import spark.Request
import spark.Response
import spark.TemplateViewRoute

fun interface SecureTemplateViewRoute : TemplateViewRoute {

    fun secureHandle(request: Request): ModelAndView

    override fun handle(request: Request, response: Response) =
        try {
            secureHandle(request)
        } catch (e: Exception) {
            ModelAndView(HashMap<String, Any>(), "Error.hbs")
        }

}

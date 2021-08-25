package hubtwork.study.springmvc.web.frontcontroller

class ModelView(var viewName: String) {
    var model: MutableMap<String, Any> = hashMapOf()
}

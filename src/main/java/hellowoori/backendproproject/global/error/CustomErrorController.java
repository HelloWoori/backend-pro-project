package hellowoori.backendproproject.global.error;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.BAD_REQUEST) {
            return "redirect:/error/400";
        } else if (status == HttpStatus.FORBIDDEN) {
            return "redirect:/error/403";
        } else if (status == HttpStatus.NOT_FOUND) {
            return "redirect:/error/404";
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            return "redirect:/error/500";
        } else {
            return "redirect:/error/default";
        }
    }

    @RequestMapping("/error/400")
    public String handleBadRequest() {
        return "error/400";
    }

    @RequestMapping("/error/403")
    public String handleForbidden() {
        return "error/403";
    }

    @RequestMapping("/error/404")
    public String handleNotFound() {
        return "error/404";
    }

    @RequestMapping("/error/500")
    public String handleInternalServerError() {
        return "error/500";
    }

    @RequestMapping("/error/default")
    public String handleDefault() {
        return "error/default";
    }

    public String getErrorPath() {
        return "/error";
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}

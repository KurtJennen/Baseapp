package be.luxuryoverdosis.framework.web.action.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParamQueryExcelController {

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public @ResponseBody
    String excel(String excel, String extension, HttpServletRequest request) throws IOException {
        String filename="";
        if (extension.equals("csv") || extension.equals("xml")) {
            filename = "pqGrid." + extension;
            HttpSession ses = request.getSession(true);
            ses.setAttribute("excel", excel);
        } 
        return filename;
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void excel(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (filename.equals("pqGrid.csv") || filename.equals("pqGrid.xml")) {
            HttpSession ses = request.getSession(true);
            String excel = (String) ses.getAttribute("excel");
                        
            byte[] bytes = excel.getBytes(Charset.forName("UTF-8"));
                    
            response.setContentType("text/plain");
            
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + filename);
            response.setContentLength(bytes.length);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            
            out.flush();
            out.close();
        }
    }
}

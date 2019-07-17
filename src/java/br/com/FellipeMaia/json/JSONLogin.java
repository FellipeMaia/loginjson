/*

MIT License

Copyright (c) 2019 Fellipe Azevedo Porfirio Maia

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/
package br.com.FellipeMaia.json;

import br.com.FellipeMaia.dao.DaoLogin;
import br.com.FellipeMaia.model.Login;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import br.com.FellipeMaia.factory.FactoryMd5;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FMaia
 * https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5
 */

@WebServlet(name = "login", urlPatterns = {"/login"})
public class JSONLogin extends HttpServlet {
    
    JsonObject job;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(401, "metodo GET indisponivel!");
    }
    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        System.out.println(request.getContextPath()+request.getServletPath());
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json");
        
        JsonParser parser = new JsonParser();
        String jsonString = request.getReader().lines().collect(Collectors.joining());
        JsonElement jsonElement = parser.parse(jsonString);
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String login = rootObject.get("login").getAsString();
        String pass = rootObject.get("psw").getAsString();
        
        pass = FactoryMd5.criptografar(pass);
        DaoLogin dao;
        try {
            dao = DaoLogin.getInstance();
            Login objlogin = dao.islogin(login, pass);
            if(objlogin != null){
                request.getSession().setAttribute("time", Calendar.getInstance());
                String idSession =  FactoryMd5.criptografar(jsonString+Calendar.getInstance().toString());
                request.getSession().setAttribute("idSession",idSession);
                request.getSession().setAttribute("login",objlogin);
                
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("idSession", idSession);
                JsonElement jelem = new Gson().toJsonTree(objlogin.getListPermissao());
                jsonObject.add("permisoes", jelem.getAsJsonArray());
                
                response.getWriter().write(jsonObject.toString());
            }else{
                response.setStatus(403);
                response.sendError(403,"Login invalido");
            }
        } catch (ParseException ex) {
            response.setStatus(500);
            response.sendError(500,"Erro Fatal!!");
            Logger.getLogger(JSONLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
}

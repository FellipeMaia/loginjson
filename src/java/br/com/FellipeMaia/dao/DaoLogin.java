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
package br.com.FellipeMaia.dao;

import br.com.FellipeMaia.model.Login;
import br.com.FellipeMaia.model.Usuario;
import br.com.FellipeMaia.factory.FactoryMd5;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author felli
 */
public class DaoLogin {
    
    private List<Login> listLogin = new ArrayList<>();
    private static DaoLogin daoLogin = null;

    private DaoLogin() throws ParseException {
        DaoUsuario dao = DaoUsuario.getInstance();
        Usuario usuario = dao.getUsuario(new Usuario(Long.parseLong("01028802"), "", new Date()));
        Login login1 = new Login(usuario, "fmaia", FactoryMd5.criptografar("123"));
        login1.addListPermissao("404");
        usuario = dao.getUsuario(new Usuario(Long.parseLong("01028803"), "", new Date()));
        Login login2 = new Login(usuario, "vrocha", FactoryMd5.criptografar("123456"));
        login2.addListPermissao("register");
        this.listLogin.add(login1);
        this.listLogin.add(login2);
    }
    
    public static DaoLogin getInstance() throws ParseException{
        if(daoLogin == null){
            daoLogin = new DaoLogin();
        }
        return daoLogin;
    }
    
    public List<Login> getAllListLogin(){
        return listLogin;
    }
    
    public Login getLogin(Login login){
        return listLogin.get(listLogin.indexOf(login));
    }
    
    public boolean islogin(Login login){
        if(login != null){
            Login log = this.getLogin(login);
            if(log.getSenha().equals(login.getSenha())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    public Login islogin(String login, String senha){
        if(login != null && senha != null){
            Login log = this.getLogin(new Login(null, login, senha));
            if(log.getSenha().equals(senha) && log.getLogin().toUpperCase().equals(login.toUpperCase())){
                return log;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}

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
package br.com.FellipeMaia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author FMaia
 */
public class Login {
    
    private Usuario usuario;
    private String login;
    private String senha;
    private List<String> listPermissao = new ArrayList<>();
    private List<String> listSenhaOld = new ArrayList<>();
    private boolean ativo;

    public Login(Usuario usuario, String login, String senha) {
        this.usuario = usuario;
        this.login = login;
        this.senha = senha;
        this.ativo = true;
        this.addListPermissao("index");
        this.addListPermissao("dashboard");
    }
    
    public Login(Usuario usuario, String login, String senha, boolean ativo) {
        this.usuario = usuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.addListPermissao("index");
        this.addListPermissao("dashboard");
    }

    public Login(Usuario usuario, String login, String senha, List<String> listPermissao, List<String> listSenhaOld, boolean ativo) {
        this.usuario = usuario;
        this.login = login;
        this.senha = senha;
        this.listPermissao = listPermissao;
        this.listSenhaOld = listSenhaOld;
        this.ativo = ativo;
        this.addListPermissao("index");
        this.addListPermissao("dashboard");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getListPermissao() {
        return listPermissao;
    }

    public void setListPermissao(List<String> listPermissao) {
        this.listPermissao = listPermissao;
    }
    
    public void addListPermissao(String Permissão){
        this.listPermissao.add(Permissão);
    }
    
    public void removeListPermissao(String Permissão){
        this.listPermissao.remove(Permissão);
    }

    public List<String> getListSenhaOld() {
        return listSenhaOld;
    }

    public void setListSenhaOld(List<String> listSenhaOld) {
        this.listSenhaOld = listSenhaOld;
    }

    public void addListSenhaOld(String senha){
        this.listSenhaOld.add(senha);
    }
    
    public void removeListSenhaOld(String senha){
        this.listSenhaOld.remove(senha);
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public boolean isEqual(Login login){
        if(login.login != this.login && this.usuario.isEqual(login.usuario.getId()))
            return false;
        return true;
    }
    
    public boolean isAutentico(Login login){
        if(login.login != this.login && this.senha != this.senha)
            return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Login other = (Login) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }
    
    
}

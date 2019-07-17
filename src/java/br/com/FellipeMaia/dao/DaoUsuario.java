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

import br.com.FellipeMaia.model.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author felli
 */
public class DaoUsuario {
    
    private List<Usuario> listUsuario = new ArrayList<>();
    private static DaoUsuario daoUsuario = null;

    private DaoUsuario() throws ParseException {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/YYYY");
        Usuario usuario1 = new Usuario(Long.parseLong("01028802"), "Fellipe Maia", sfd.parse("15/07/1989"));
        Usuario usuario2 = new Usuario(Long.parseLong("01028803"), "Veronica Rocha", sfd.parse("15/08/1995"));
        Usuario usuario3 = new Usuario(Long.parseLong("01028804"), "Fatima Azevedo", sfd.parse("21/05/1959"));
        listUsuario.add(usuario1);
        listUsuario.add(usuario2);
        listUsuario.add(usuario3);
    }
    
    public static DaoUsuario getInstance() throws ParseException{
        if(daoUsuario == null){
            daoUsuario = new DaoUsuario();
        }
        return daoUsuario;
    }
    
    public List<Usuario> getListAllUsuarios(){
        return listUsuario;
    }
    
    public Usuario getUsuario(Usuario usuario){
        return listUsuario.get(listUsuario.indexOf(usuario));
    }
    
}

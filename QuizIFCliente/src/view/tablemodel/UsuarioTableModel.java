
//João Felipe Staub - 19/01/2022 ::Criação
//João Felipe Staub - 19/01/2022 :: getUsuario

package view.tablemodel;

import ModelDominio.Usuario;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import util.Metodos;

/**
 *
 * @author felip
 */
public class UsuarioTableModel extends AbstractTableModel{
    
    private final ArrayList<Usuario> listaUsuario;
    private final int[] arrayTamanhos = {150, 300, 75};
    
    public UsuarioTableModel(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public Usuario getUsuario(int x){
        return this.listaUsuario.get(x);
    }

    @Override
    public int getRowCount() {
        return listaUsuario.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int column) {
        return switch(column){
            case 0 -> "Nome";
            case 1 -> "Email";
            case 2 -> "Permissão";
            default -> "";
        };
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario u = listaUsuario.get(rowIndex);
        return switch(columnIndex){
            case 0 -> u.getNomeUsuario();
            case 1 -> u.getEmail();
            case 2 -> Metodos.pedaco(u.getClass().toString(), ".", 2);
            default -> "";
        };
    }
    
    public void AtualizaColunas(JTable tabela){
      Metodos.ajustaColunas(tabela, arrayTamanhos);
    }
}


    //João Felipe Staub - 18/01/2022

package view.tablemodel;

import ModelDominio.Jogo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class RankingTableModel extends AbstractTableModel{
    
    private final ArrayList<Jogo> listaJogos;
    private final int numCols;
    
    public RankingTableModel(ArrayList<Jogo> listaJogos){
        this.listaJogos = listaJogos;
        this.numCols = 5;
    }

    @Override
    public int getRowCount() {
        return listaJogos.size();
    }

    @Override
    public int getColumnCount() {
        return numCols;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jogo j = listaJogos.get(rowIndex);
        return switch(columnIndex){
            case 0 -> rowIndex+1;
            case 1 -> j.getJogador();
            case 2 -> j.getNumPerguntas();
            case 3 -> j.getNumAcertos();
            case 4 -> j.getPontuacao();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch(column){
            case 0 -> "Colocação";
            case 1 -> "Apelido";
            case 2 -> "Nº Perguntas";
            case 3 -> "Nº Acertos";
            case 4 -> "Média Acertos";
            default -> "";
        };
    }
}

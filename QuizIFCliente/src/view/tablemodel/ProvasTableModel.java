
//Mateus Roberto Algayer - 30/12/2021

package view.tablemodel;

import ModelDominio.Prova;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProvasTableModel extends AbstractTableModel{

  private final ArrayList<Prova> listaProvas;

  public ProvasTableModel(ArrayList<Prova> listaProvas) {
    this.listaProvas = listaProvas;
  }
  
  @Override
  public int getRowCount() {
    return listaProvas.size();
  }

  @Override
  public int getColumnCount() {
    return 4;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Prova p = listaProvas.get(rowIndex);
    
    return switch(columnIndex){
      case 0 -> p.getNomeProva();
      case 1 -> p.getAreaGeral().getNome();
      case 2 -> p.getDificuldade();
      case 3 -> p.getPontuacao();
      default -> "";
    };
  }
  
  @Override
  public String getColumnName(int column) {
    return switch(column){
      case 0 -> "Prova";
      case 1 -> "Área";
      case 2 -> "Dificuldade";
      case 3 -> "Pontuação";
      default -> "Noname";
    };
  }
  
  public Prova getProva(int row){
    return listaProvas.get(row);
  }
}
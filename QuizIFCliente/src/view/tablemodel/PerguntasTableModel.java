
//Mateus Roberto Algayer - 11/01/2022

package view.tablemodel;

import ModelDominio.Pergunta;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import util.Metodos;

public class PerguntasTableModel extends AbstractTableModel{

  private final ArrayList<Pergunta> listaPergunta;
  private final int[] arrayTamanhos = {0, 25, 25};
  
  public PerguntasTableModel(ArrayList<Pergunta> listaPergunta) {
    this.listaPergunta = listaPergunta;
  }
  
  @Override
  public int getRowCount() {
    return listaPergunta.size();
  }

  @Override
  public int getColumnCount() {
    return 3;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Pergunta p = listaPergunta.get(rowIndex);
    
    return switch(columnIndex){
      case 0 -> p.getPergunta();
      case 1 -> p.getArea().getNome();
      case 2 -> p.getDificuldadeLiteral();
      default -> "";
    };
  }
  
  @Override
  public String getColumnName(int column) {
    return switch(column){
      case 0 -> "Pergunta";
      case 1 -> "Área";
      case 2 -> "Dificuldade";
      default -> "Noname";
    };
  }
  
  public Pergunta getPergunta(int row){
    return listaPergunta.get(row);
  }
  
  public void removePergunta(int row){
    listaPergunta.remove(row);
  }
  
  public void adicionaPergunta(Pergunta p){
    listaPergunta.add(p);
    
  }
  
  public void ordenaLista(){
    listaPergunta.sort(new Comparator<Pergunta>(){
      @Override
      public int compare(Pergunta p1, Pergunta p2) {
        return p1.getPergunta().compareTo(p2.getPergunta());
      }
    
    });
  }
  
  public void AtualizaColunas(JTable tabela){
    Metodos.ajustaColunas(tabela, arrayTamanhos);
  }
}

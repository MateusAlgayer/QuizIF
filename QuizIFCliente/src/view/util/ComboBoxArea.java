
//Mateus Roberto Algayer - 10/01/2022

package view.util;

import ModelDominio.Area;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboBoxArea{
 
  private final String nome;
  private final int codigo;

  public ComboBoxArea(String nome, int codigo) {
    this.nome = nome;
    this.codigo = codigo;
  }

  @Override
  public String toString() {
    return nome;
  }
  
  public int getCodigo(){
    return codigo;
  }
  
  public static int getSelectedIndex(JComboBox combo){
    Object obj = combo.getSelectedItem();
    if(obj == null)
      return -1;
    else
      return ((ComboBoxArea) obj).getCodigo();
    
  }
  
  public static void preencheComboboxArea(int pCodigo, JComboBox combo, ArrayList<Area> lista, Boolean addNenhum){
    Vector vLista = new Vector();
    int x = 0;
    int selecionarCodigo = -1;
    
    if(addNenhum)
      vLista.add(new ComboBoxArea("Nenhum", 0));
    
    for (Area c: lista) {
      
      ComboBoxArea cmb = new ComboBoxArea(c.getNome(),c.getCodArea());
      
      vLista.add(cmb);
      
      if(cmb.getCodigo() == pCodigo)
        selecionarCodigo = x;
      x++;
    }
    
    DefaultComboBoxModel modeloCliente = new DefaultComboBoxModel(vLista);
    
    combo.setModel(modeloCliente);
    
    if(selecionarCodigo >= 0)
      combo.setSelectedIndex(selecionarCodigo);
  }
}


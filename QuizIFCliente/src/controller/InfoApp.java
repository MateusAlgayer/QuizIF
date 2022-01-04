
//Mateus Roberto Algayer - 28/12/2021 :: Criação

package controller;

import ModelDominio.Usuario;

public class InfoApp {
  
  private static Usuario GUsuLogado;
  
  private static String GCodConfirmacao;

  public static Usuario getGUsuLogado() {
    return GUsuLogado;
  }

  public static void setGUsuLogado(Usuario GUsuLogado) {
    InfoApp.GUsuLogado = GUsuLogado;
  }

  public static String getGCodConfirmacao() {
    return GCodConfirmacao;
  }

  public static void setGCodConfirmacao(String GCodConfirmacao) {
    InfoApp.GCodConfirmacao = GCodConfirmacao;
  }
  
  
}

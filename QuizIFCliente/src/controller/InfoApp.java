
//Mateus Roberto Algayer - 28/12/2021 :: Criação

package controller;

import ModelDominio.Usuario;

public class InfoApp {
  
  private static Usuario GUsuLogado;
  
  private static String GCodConfirmacao;
  
  private static String GSenhaCripto;
  
  private static String GEmailUsu;

  public static String getGEmailUsu() {
    return GEmailUsu;
  }

  public static void setGEmailUsu(String GEmailUsu) {
    InfoApp.GEmailUsu = GEmailUsu;
  }

  public static String getGSenhaCripto() {
    return GSenhaCripto;
  }

  public static void setGSenhaCripto(String GSenhaCripto) {
    InfoApp.GSenhaCripto = GSenhaCripto;
  }

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

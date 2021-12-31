
//Staub - 28/12/2021 :: Criação
//Staub - 30/12/2021 :: Edit getSalt

package util;

import java.security.MessageDigest;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CriptoHash {

    public void CriptoSenha(String pSenha) throws NoSuchAlgorithmException, NoSuchProviderException{
        
        String wSenhaToHash = pSenha;
        String wSal = getSalt();
        String wSecurePass = getPassDigest(wSenhaToHash, wSal);
        
    }
    
    public static String getSalt(){
    
        try {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return toHexString(salt);
        
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            Metodos.GravaLogErro("ERR", 0, "Erro no Sal");
            return "";
        }
        
    }
    
    private static String toHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte i : bytes){
            sb.append(String.format("%02x", i));
        }
        return sb.toString();
    }
    
    private static String getPassDigest(String pSenhaToHash, String pSal){
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pSal.getBytes());
            
            byte[] bytes = md.digest(pSenhaToHash.getBytes());
            return toHexString(bytes);
            
        } catch (NoSuchAlgorithmException e) {
            Metodos.GravaLogErro("ERR", 0, e.toString());
            return "";
        }
    }
    
}

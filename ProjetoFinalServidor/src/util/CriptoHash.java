
//Staub - 28/12/2021 :: Criação

package util;

import java.security.MessageDigest;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CriptoHash {

    public void CriptoSenha(String pSenha) throws NoSuchAlgorithmException, NoSuchProviderException{
        
        String passToHash = pSenha;
        String salt = getSalt();
        String securePass = getPassDigest(passToHash, salt);
        
    }
    
    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException{
        
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return toHexString(salt);
    }
    
    private static String toHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte i : bytes){
            sb.append(String.format("%02x", i));
        }
        return sb.toString();
    }
    
    private static String getPassDigest(String passwordToHash, String salt){
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            
            byte[] bytes = md.digest(passwordToHash.getBytes());
            return toHexString(bytes);
            
        } catch (Exception e) {
            Metodos.GravaLogErro("ERR", 0, e.toString());
            return "";
        }
    }
    
}

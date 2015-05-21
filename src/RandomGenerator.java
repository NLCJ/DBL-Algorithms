
import java.security.SecureRandom;

/**
 *
 * @author Diederik
 */
public class RandomGenerator {
    SecureRandom random = new SecureRandom();
    
    public int randomInt(int max){
        return random.nextInt(max+1);
    }
    
    public double randomDouble(){
        return random.nextDouble();
    }
}

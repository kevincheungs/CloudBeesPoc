import io.rollout.configuration.RoxContainer;
import io.rollout.flags.RoxFlag;
import io.rollout.flags.RoxString;
import io.rollout.flags.RoxInt;
import io.rollout.flags.RoxDouble;
import io.rollout.flags.RoxEnum;

// Create Roxflags in the Flags container class
public class Flags implements RoxContainer {
    // Define the feature flags
    public RoxFlag enableTutorial = new RoxFlag(false);
    public final RoxString titleColors = new RoxString("White", new String[] { "White", "Blue", "Green", "Yellow" });
    public final RoxInt titleSize = new RoxInt(14, new int[] { 14, 18, 24 });
    public final RoxDouble specialNumber = new RoxDouble(3.14, new double[]{ 2.71, 0.577 });
    public final RoxEnum<Color> titleColorsEnum = new RoxEnum<>(Color.WHITE);

    public enum Color {
        WHITE, BLUE, GREEN, YELLOW
    }
}
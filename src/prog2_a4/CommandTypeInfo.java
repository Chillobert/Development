package prog2_a4;

public interface CommandTypeInfo {
    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
}

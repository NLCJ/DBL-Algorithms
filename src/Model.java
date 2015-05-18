/**
 * Placement model
 *
 * @author Stefan Habets
 */
public enum Model {

    TWOPOS("2pos"), FOURPOS("4pos"), ONESLIDER("1slider");

    private String text;

    Model(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Model fromString(String text) {
        if (text != null) {
            for (Model b : Model.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
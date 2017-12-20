package co.planis.jaksiemaszmvp;

public class TextChangedEvent {

    private String text;

    public TextChangedEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

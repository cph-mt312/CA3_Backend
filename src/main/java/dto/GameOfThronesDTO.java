package dto;

public class GameOfThronesDTO {
    
    private String quote;
    private String character;

    public GameOfThronesDTO(String quote, String character) {
        this.quote = quote;
        this.character = character;
    }

    public GameOfThronesDTO() {
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}

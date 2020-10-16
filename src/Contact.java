public class Contact {
    private String name;
    private String number;

    public Contact (String inputName, String inputNumber) {
        this.name = inputName;
        this.number = inputNumber;
    }

    public void setName(String inputName) {
        this.name = inputName;
    }

    public void setNumber(String inputNumber) {
        this.number = inputNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }
}

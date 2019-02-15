package ua.com.sliusar.domain;

/**
 * Class Client
 *
 * @author create by ivanslusar
 * 2/14/19
 * @project MyLuxoftProject
 */
public class Client {
    /**
     * Id client in DB.
     */
    private Double id;
    /**
     * Name client in DB.
     */
    private String name;

    /**
     * Surname client in DB.
     */
    private String surname;

    /**
     * Phone client in DB.
     */
    private String phone;

    /**
     * Email client in DB.
     */
    private String email;

    /**
     * Age client in DB.
     */
    private int age;

    /**
     * Get client`s id.
     *
     * @return Double
     */
    public Double getId() {
        return id;
    }

    /**
     * Set client`s id.
     *
     * @param id Double
     */
    public void setId(Double id) {
        this.id = id;
    }

    /**
     * Get Client`s name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set client`s name.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Client`s surname.
     *
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set Client`s surname.
     *
     * @param surname String
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get Client`s phone.
     *
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set Client`s phone.
     *
     * @param phone String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get Client`s email.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set Client`s email.
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get Client`s age.
     *
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * Set client`s age.
     *
     * @param age int
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Special case of constructor.
     *
     * @param name    String
     * @param surname String
     * @param phone   String
     */
    public Client(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

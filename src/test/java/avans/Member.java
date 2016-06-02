package avans;

public class Member {
  private final String id;
  private final String mail;
  private final String name;

  public Member(String id, String mail, String name) {
    this.id = id;
    this.mail = mail;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getMail() {
    return mail;
  }

  public String getName() {
    return name;
  }
}

package com.zemoga.core.repositories.db.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("portfolio")
public class Portfolio {

  @Id
  private int idportfolio;

  private String description;

  @Column(value="image_url")
  private String imageUrl;

  @Column(value="twitter_user_name")
  private String twitterUserName;

  private String title;

  public Portfolio(int idportfolio, String description, String imageUrl, String twitterUserName,
      String title) {
    this.idportfolio = idportfolio;
    this.description = description;
    this.imageUrl = imageUrl;
    this.twitterUserName = twitterUserName;
    this.title = title;
  }

  public int getIdportfolio() {
    return idportfolio;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getTwitterUserName() {
    return twitterUserName;
  }

  public String getTitle() {
    return title;
  }
}

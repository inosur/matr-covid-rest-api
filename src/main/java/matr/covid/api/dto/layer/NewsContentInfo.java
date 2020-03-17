package matr.covid.api.dto.layer;

import matr.covid.api.dto.ContentInfo;

/**
 *
 * @author osvaldo
 */
public class NewsContentInfo implements ContentInfo {

    private String title;
    private String content;
    private String link;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

package id.ac.itb.informatika.wbd.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "posts")
@NamedQueries({@NamedQuery(name = "Post.findAll", query = "SELECT c FROM Post c"),
                @NamedQuery(name = "Post.findById", query = "SELECT c FROM Post c WHERE c.id = :id"),
                @NamedQuery(name = "Post.findByTitle", query = "SELECT c FROM Post c WHERE c.title = :title")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "post_title")
    private String title;
    
    @Column(name = "post_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "post_content")
    private String content;
    
    @Column(name = "post_featured")
    private Boolean featured;
    
    @Column(name = "post_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    
    public Post() {
    }

    public Post(Long id) {
        this.id = id;
    }

    public Post(Long id, String title, Date date, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.featured = false;
        this.modified = date;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.ac.itb.informatika.wbd.jpa.entities.Post[ id=" + id + " ]";
    }
    
}

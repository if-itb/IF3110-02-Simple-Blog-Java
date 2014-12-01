package id.ac.itb.informatika.wbd.jpa.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
   // @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "post_content")
    private String content;
    
    @Column(name = "post_featured")
    private Boolean featured;

    @Column(name = "post_published")
    private Boolean published;
    
    @Column(name = "post_deleted")
    private Boolean deleted;

    @Column(name = "post_modified")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    
   // @OneToMany(mappedBy = "post") // mappedBy indicates that this side is the 
   // inverse side, and that the mapping is defined by the attribute parentOrder 
   // at the other side of the association.
   // private Set<Comment> comments;
    
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
        this.published = false;
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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

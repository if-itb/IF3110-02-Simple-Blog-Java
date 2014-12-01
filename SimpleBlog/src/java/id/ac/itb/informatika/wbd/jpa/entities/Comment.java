package id.ac.itb.informatika.wbd.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comments")
@NamedQueries({@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
                @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
                @NamedQuery(name = "Comment.findByPost", query = "SELECT c FROM Comment c WHERE c.post = :post")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

 //   @JoinColumn(name = "post_id")
 //   @ManyToOne
 //   private Post post;
    
    @Column(name = "post_id")
    private Long post;
    
    @Column(name = "comment_author")
    private String name;
    
    @Column(name = "comment_author_email")
    private String email;
    
    @Column(name = "comment_author_ip")
    private String ip;
    
    @Column(name = "comment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "comment_content")
    private String content;
    
    @Column(name = "post_featured")
    private Boolean featured;
    
    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    public Comment(Long id, Date date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.featured = false;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
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

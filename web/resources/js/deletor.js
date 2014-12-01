window.onload = function() {
    var a = document.getElementsByClassName("deletebutton");
    for(i in a){
        a[i].onclick = function() {
            if(confirm("Apakah Anda yakin menghapus post ini?")){
                var pid = this.id.substring(3).toString();
                window.location.href = "Soft_Delete_Post.xhtml?pid=" + pid;
            }
            return false;
        };
    };
    
    var hd = document.getElementsByClassName("harddeletebutton");
    for(i in hd){
        hd[i].onclick = function() {
            if(confirm("Anda yakin ingin menghapus pos ini secara permanen?")){
                var pid = this.id.substring(3).toString();
                window.location.href = "Delete_Post.xhtml?pid=" + pid;
            }
            return false;
        };
    };
    
    var rb = document.getElementsByClassName("restorebutton");
    for(i in rb){
        rb[i].onclick = function() {
            if(confirm("Yakin merestore post ini ke unpublished post?")){
                var pid = this.id.substring(3).toString();
                window.location.href = "Restore_Post.xhtml?pid=" + pid;
            }
            return false;
        };
    };
    
    var pb = document.getElementsByClassName("publishbutton");
    for(i in pb){
        pb[i].onclick = function() {
            if(confirm("Yakin mempublish pos ini?")){
                var pid = this.id.substring(3).toString();
                window.location.href = "Publish_Post.xhtml?pid=" + pid;
            }
            return false;
        };
    };
};
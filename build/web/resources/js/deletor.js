window.onload = function() {
    var a = document.getElementsByClassName("deletebutton");
    for(i in a){
        a[i].onclick = function() {
            if(confirm("Anda yakin ingin menghapus pos ini? " + this.id)){
                var pid = this.id.substring(3).toString();
                window.location.href = "Delete_Post.xhtml?pid=" + pid;
            }
            return false;
        };
    };
};
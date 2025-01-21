package midterm2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class PostTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postAuthor = sc.nextLine();
        String postContent = sc.nextLine();

        Post p = new Post(postAuthor, postContent);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String testCase = parts[0];

            if (testCase.equals("addComment")) {
                String author = parts[1];
                String id = parts[2];
                String content = parts[3];
                String replyToId = null;
                if (parts.length == 5) {
                    replyToId = parts[4];
                }
                p.addComment(author, id, content, replyToId);
            } else if (testCase.equals("likes")) { //likes;1;2;3;4;1;1;1;1;1 example
                for (int i = 1; i < parts.length; i++) {
                    p.likeComment(parts[i]);
                }
            } else {
                System.out.println(p);
            }

        }
    }
}
class createIndient{
    static String createIndient(int indient){
        StringBuilder br=new StringBuilder();
        br.append("        ");
        for (int i = 0; i < indient; i++) {
            br.append("    ");
        }
        return br.toString();
    }
}
class Post {
    String username;
    String content;
    Map<String,Comment> commentMapTreeStructure;
    Map<String,Comment> allComents;

    public Post(String username, String content) {
        this.username = username;
        this.content = content;
        commentMapTreeStructure = new LinkedHashMap<>();
        allComents = new HashMap<>();
    }

    void addComment (String username, String commentId, String content, String replyToId){
        Comment new_comment = new Comment(username, commentId, content);
        Comment replied_to = null;
        if(replyToId != null){
            replied_to = allComents.get(replyToId);
        }
        if(replied_to != null){
            replied_to.addReply(new_comment);
        }else{
            commentMapTreeStructure.put(commentId, new_comment);
        }
        allComents.put(commentId, new_comment);

    }
    void likeComment (String commentId){
        Comment comment = allComents.get(commentId);
        if(comment != null){
            comment.likeComment();
        }
    }

    @Override
    public String toString() {
        StringBuilder br=new StringBuilder();
        br.append(String.format("Post: %s\nWritten by: %s\n",content,username));
        br.append("Comments:\n");
        commentMapTreeStructure.values().stream().sorted(Comparator.comparing(Comment::getLikes).reversed()).forEach(v->{
            br.append(v.toStringTextRepresentation(0));
        });
        return br.toString();
    }
}
class Comment {
    String username;
    String commentId;
    String content;
    int likes;
    List<Comment> replies;

    public Comment(String username, String commentId, String content) {
        this.username = username;
        this.commentId = commentId;
        this.content = content;
        replies = new ArrayList<>();
        likes=0;
    }
    void addReply(Comment comment){
        replies.add(comment);
    }
    void likeComment(){
        likes++;
    }

    public String getCommentId() {
        return commentId;
    }

    public int getLikes() {
        return likes+replies.stream().mapToInt(Comment::getLikes).sum();
    }

    public String toStringTextRepresentation(int idient) {
        StringBuilder br=new StringBuilder();
        String ind=createIndient.createIndient(idient);
        br.append(String.format("%sComment: %s\n%sWritten by: %s\n%sLikes: %d\n",ind,content,ind,username,ind,likes));
        replies.stream().sorted(Comparator.comparing(Comment::getLikes).reversed()).forEach(comment ->{br.append(comment.toStringTextRepresentation(idient+1));});
        return br.toString();
    }
}
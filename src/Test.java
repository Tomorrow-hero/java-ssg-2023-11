import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

        public static void main(String[] args) {
            System.out.println("프로그램 시작");

            Scanner sc = new Scanner(System.in);

            int lastArticleId = 0;

            List<Article> articles = new ArrayList<>();

            while (true) {
                System.out.print("명령어)-->");
                String command = sc.nextLine();

                command = command.trim();

                if(command.isEmpty()) {
                    continue;
                }

                if (command.equals("종료")) {
                    break;
                } else if (command.equals("글작성")) {
                    int id = lastArticleId + 1;

                    System.out.print("제목 : ");
                    String title = sc.nextLine();

                    System.out.print("내용 : ");
                    String body = sc.nextLine();

                    String regDate = Util.getNowDateStr();

                    Article article = new Article(id, regDate, title, body);
                    articles.add(article);

                    lastArticleId = id;

                    System.out.printf("%d번 글이 생성되었습니다.%n",id);
                } else if (command.equals("글목록")) {
                    if(articles.isEmpty()) {
                        System.out.println("게시물이 없습니다.");
                        continue;
                    }

                    for (int i = articles.size()-1; i >= 0; i--) {
                        Article article = articles.get(i);

                        System.out.printf("번호:%d 제목:%s%n",article.id, article.title);
                    }
                } else if (command.startsWith("상세페이지")) {
                    String[] commandBits = command.split(" ");
                    int id = Integer.parseInt(commandBits[commandBits.length - 1]);

                    Article foundArticle = null;

                    for (int i = 0; i < articles.size(); i++) {
                        Article article = articles.get(i);

                        if (article.id == id) {
                            foundArticle = article;
                            break;
                        }
                    }

                    if(foundArticle == null) {
                        System.out.printf("%d번 게시물은 존재하지 않습니다.%n", id);
                    }else {
                        System.out.printf("번호 : %d %n", foundArticle.id);
                        System.out.printf("날짜 : %s %n", foundArticle.regDate);
                        System.out.printf("제목 : %s %n", foundArticle.title);
                        System.out.printf("내용 : %s %n", foundArticle.body);
                    }
                }else if (command.startsWith("글수정")) {
                    String[] commandBits = command.split(" ");
                    int id = Integer.parseInt(commandBits[commandBits.length - 1]);

                    Article foundArticle = null;

                    for (int i = 0; i < articles.size(); i++) {
                        Article article = articles.get(i);

                        if (article.id == id) {
                            foundArticle = article;
                            break;
                        }
                    }

                    if(foundArticle == null) {
                        System.out.printf("%d번 게시물은 존재하지 않습니다.%n", id);
                    }else {
                        System.out.print("제목 : ");
                        String title = sc.nextLine();

                        System.out.print("내용 : ");
                        String body = sc.nextLine();

                        foundArticle.title = title;
                        foundArticle.body = body;

                        System.out.printf("%d번 게시물이 수정되었습니다.%n", id);
                    }
                } else if (command.startsWith("글삭제")) {
                    String[] commandBits = command.split(" ");
                    int id = Integer.parseInt(commandBits[commandBits.length - 1]);

                    int foundIndex = -1;

                    for (int i = 0; i < articles.size(); i++) {
                        Article article = articles.get(i);

                        if (article.id == id) {
                            foundIndex = i;
                            break;
                        }
                    }

                    if(foundIndex == -1) {
                        System.out.printf("%d번 게시물은 존재하지 않습니다.%n", id);
                    }else {
                        articles.remove(foundIndex);
                        System.out.printf("%d번 게시물이 삭제되었습니다.%n", id);
                    }
                } else {
                    System.out.printf("%s(은)는 존재히지 않는 명령어 입니다.%n", command);
                }
            }

            sc.close();

            System.out.println("프로그램 종료");

        }
    }



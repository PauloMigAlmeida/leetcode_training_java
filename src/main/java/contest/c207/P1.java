package contest.c207;

public class P1 {
    public String reorderSpaces(String text) {
        String curated = text.trim().replaceAll("\\s+"," ");
        String[] words = curated.split(" ");

        int count = 0;
        for(int i=0; i < text.length(); i++){
            if(text.charAt(i) == ' ')
                count++;
        }

        if((words.length - 1) != 0){
            int between = count / (words.length - 1);
            int after = count % (words.length - 1);

            StringBuilder sb = new StringBuilder();
            for(int i=0; i < words.length; i++){
                String w = words[i];
                sb.append(w);
                if(words.length -1 != i){
                    for(int j =0; j < between ; j++)
                        sb.append(' ');
                }
            }
            for(int i =0; i < after ; i++)
                sb.append(' ');

            return sb.toString();
        }else if(count > 0){
            StringBuilder sb = new StringBuilder();

            for(int i=0; i < words.length; i++){
                String w = words[i];
                sb.append(w);
            }
            for(int i =0; i < count ; i++)
                sb.append(' ');
            return sb.toString();
        }
        else
            return text;
    }

    public static void main(String[] args) {
        var instance = new P1();

        System.out.println(instance.reorderSpaces("  hello"));
//        System.out.println(instance.reorderSpaces("this   is   a   sentence   "));
//        System.out.println(instance.reorderSpaces("  this   is  a sentence "));
//        System.out.println(instance.reorderSpaces("  walks  udp package   into  bar a"));
//        System.out.println(instance.reorderSpaces("a"));
    }
}
